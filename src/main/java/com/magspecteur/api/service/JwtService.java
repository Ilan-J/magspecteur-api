package com.magspecteur.api.service;

import com.magspecteur.api.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class JwtService {

	@Autowired
	private JwtDecoder jwtDecoder;

	@Autowired
	private JwtEncoder jwtEncoder;

	@Value("${jwt.issuer}")
	private String jwtIssuer;

	public String generateAccessToken(User user) {
		Instant now = Instant.now();
		long expiry = 300L; // 5min

		List<String> authorities = new ArrayList<>();
		user.getRoles().forEach(role -> authorities.add(role.getName()));

		JwtClaimsSet claims = JwtClaimsSet.builder()
				.issuer(jwtIssuer)
				.issuedAt(now)
				.expiresAt(now.plusSeconds(expiry))
				.subject(String.format("%s,%s", user.getId(), user.getUsername()))
				.claim("roles", authorities)
				.build();

		return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
	}

	public String generateRefreshToken(User user) {
		Instant now = Instant.now();
		long expiry = 3600L; // 1h

		JwtClaimsSet claims = JwtClaimsSet.builder()
				.issuer(jwtIssuer)
				.issuedAt(now)
				.expiresAt(now.plusSeconds(expiry))
				.subject(String.format("%s,%s", user.getId(), user.getUsername()))
				.build();

		return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
	}

	public Boolean validate(String token) {
		Instant expiresAt = null;
		try {
			expiresAt = jwtDecoder.decode(token).getExpiresAt();
		} catch (JwtException e) {
			System.out.printf("Invalid JWT: %s%n", token);
		}
		return expiresAt != null && expiresAt.isAfter(Instant.now());
	}

	public String getUsername(String token) {
		String subject = jwtDecoder.decode(token).getSubject();

		return subject.split(",")[1];
	}
}
