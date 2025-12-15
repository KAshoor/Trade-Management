package org.com.orderservice.controller;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.com.orderservice.dto.AuthenticationRequest;
import org.com.orderservice.entities.User;
import org.com.orderservice.repositories.UserRepository;
import org.com.orderservice.utils.JwtUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class UserAuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtUtil jwtUtil;


	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization ";

	@ResponseBody
	@PostMapping(value = "/authenticate", consumes = "application/json", produces = "application/json")
	public void createAuthenticationToken(@RequestBody AuthenticationRequest authenticationReq,
			HttpServletResponse response) throws IOException, JSONException {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationReq.getEmail(),
					authenticationReq.getPassword()));
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Incorrect username or Password");
		} catch (DisabledException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "User is not created");
			return;
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationReq.getEmail());
		Optional<User> optionalUser = this.userRepository.findByEmail(userDetails.getUsername());
		final String jwtToken = jwtUtil.generateToken(userDetails.getUsername());

		if (optionalUser.isPresent()) {
			response.getWriter().write(new JSONObject().put("userId", optionalUser.get().getId())
					.put("role", optionalUser.get().getUserRole()).toString());
		}
		response.setHeader("Access-Control-Expose-Headers", "Authorization");
		response.setHeader("Access-Control-Allow-Headers",
				"Authorization,X-Pingother,Origin,X-Requested-With,Content-Type,Accept,X-Custome-header");
		response.setHeader(HEADER_STRING, TOKEN_PREFIX + jwtToken);

	}
}
