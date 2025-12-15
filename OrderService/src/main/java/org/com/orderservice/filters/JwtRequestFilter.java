package org.com.orderservice.filters;
import java.io.IOException;

import org.com.orderservice.services.UserDetailedServiceImpl;
import org.com.orderservice.utils.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	private final UserDetailedServiceImpl userDetailedServiceImpl;
	private final JwtUtil jwtUtil;

	public JwtRequestFilter(UserDetailedServiceImpl userDetailedServiceImpl, JwtUtil jwtUtil) {
		this.userDetailedServiceImpl = userDetailedServiceImpl;
		this.jwtUtil = jwtUtil;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization");
		String token = null;
		String userName = null;
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
			userName = jwtUtil.extractUsername(token);
		}
		if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userDetailedServiceImpl.loadUserByUsername(userName);

			if (jwtUtil.validateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}

		response.addHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains");
		response.addHeader("Content-Security-Policy", "default-src 'self'");
		response.addHeader("Referrer-Policy", "same-origin");
		response.addHeader("X-Content-Type-Options", "nosniff");
		response.addHeader("X-Permitted-Cross-Domain-Policies", "none");

		filterChain.doFilter(request, response);

	}
}
