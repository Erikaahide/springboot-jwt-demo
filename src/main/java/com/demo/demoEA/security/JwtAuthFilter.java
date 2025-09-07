package com.demo.demoEA.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.demo.demoEA.service.UserDetailsServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
	private final JwtUtil jwtUtil;
	private final UserDetailsServiceImpl uds;

//constructor to initialize jwtUtil and uds
	public JwtAuthFilter(JwtUtil jwtUtil, UserDetailsServiceImpl uds) {
		super();
		this.jwtUtil = jwtUtil;
		this.uds = uds;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws ServletException, IOException {

		String header = req.getHeader("Authorization");
		if (header != null && header.startsWith("Bearer ")) {
			String token = header.substring(7);

			try {
				String username = jwtUtil.extractUsername(token);

				if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
					UserDetails ud = uds.loadUserByUsername(username);

					// validate signature + time n the user
					if (jwtUtil.isTokenValid(token, ud)) {
						UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(ud, null,
								ud.getAuthorities());
						auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
						SecurityContextHolder.getContext().setAuthentication(auth);
					}
				}
			} catch (Exception e) {
			}
		}

		chain.doFilter(req, res);
	}
}