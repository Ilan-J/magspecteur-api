package com.magspecteur.api.controller;

import com.magspecteur.api.configuration.CustomUserDetailsService;
import com.magspecteur.api.domain.LoginDTO;
import com.magspecteur.api.domain.RegistrationDTO;
import com.magspecteur.api.domain.User;
import com.magspecteur.api.service.JwtService;
import com.magspecteur.api.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody LoginDTO request) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				request.username(),
				request.password()
		);
		Authentication authentication = authenticationManager.authenticate(token);

		org.springframework.security.core.userdetails.User userDetails =
				(org.springframework.security.core.userdetails.User)
						authentication.getPrincipal();
		User user = userService.getByUsername(userDetails.getUsername());

		String accessToken = jwtService.generateAccessToken(user);
		String refreshToken = jwtService.generateRefreshToken(user);

		Map<String, String> tokens = new HashMap<>();
		tokens.put("access_token", accessToken);
		tokens.put("refresh_token", refreshToken);

		return ResponseEntity.ok().body(tokens);
	}

	@PostMapping("/refresh")
	public ResponseEntity<Map<String, String>> refresh(HttpServletRequest request) {
		String authorisationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

		if (authorisationHeader != null && authorisationHeader.startsWith("Bearer ")) {
			String refreshToken = authorisationHeader.substring("Bearer ".length());

			if (jwtService.validate(refreshToken)) {
				org.springframework.security.core.userdetails.User userDetails =
						(org.springframework.security.core.userdetails.User)
								customUserDetailsService.loadUserByUsername(jwtService.getUsername(refreshToken));
				User user = userService.getByUsername(userDetails.getUsername());

				String accessToken = jwtService.generateAccessToken(user);

				Map<String, String> tokens = new HashMap<>();
				tokens.put("access_token", accessToken);

				return ResponseEntity.ok().body(tokens);
			}
		}
		return ResponseEntity.badRequest().body(null);
	}

	@PostMapping("/register")
	public User register(@RequestBody RegistrationDTO request) {
		return userService.create(request);
	}
}
