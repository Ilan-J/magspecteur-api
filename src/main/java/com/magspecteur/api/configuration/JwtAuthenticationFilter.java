package com.magspecteur.api.configuration;

import com.magspecteur.api.service.JwtService;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private JwtService jwtService;

	@Override
	protected void doFilterInternal(HttpServletRequest req, @Nonnull HttpServletResponse res, @Nonnull FilterChain chain) throws IOException, ServletException {
		String header = req.getHeader(HttpHeaders.AUTHORIZATION);
		String username = null;
		String token = null;

		if (header != null && header.startsWith("Bearer ")) {
			token = header.replace("Bearer ","");

			if (jwtService.validate(token)) {
				username = jwtService.getUsername(token);
			} else {
				logger.warn("The token has expired");
			}
		} else {
			logger.warn("Couldn't find bearer string, header will be ignored");
		}
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
					userDetails.getUsername(),
					userDetails.getPassword(),
					userDetails.getAuthorities()
			);

			// UsernamePasswordAuthenticationToken authentication = jwtService.getAuthenticationToken(token, SecurityContextHolder.getContext().getAuthentication(), userDetails);
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
			logger.info("authenticated user " + username + ", setting security context");
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		chain.doFilter(req, res);
	}
}
