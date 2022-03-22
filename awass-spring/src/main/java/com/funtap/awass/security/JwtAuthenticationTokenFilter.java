package com.funtap.awass.security;

import com.funtap.awass.jpaentity.User;
import com.funtap.awass.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JwtAuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {
	private final static String TOKEN_HEADER = "Authorization";
	@Autowired
	private JwtService jwtService;
	@Autowired
	UserRepository userRepository;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String authToken = httpRequest.getHeader(TOKEN_HEADER);
		if (!"".equalsIgnoreCase(authToken) && authToken != null) {
			if (authToken.contains("Bearer "))
				authToken = authToken.replace("Bearer ", "");
		}
		if (jwtService.validateTokenLogin(authToken)) {
			try {
				String username = jwtService.getUsernameFromToken(authToken);
				User user = userRepository.findByUsernameAndEnable(username,1);

				if (user != null) {
					CustomUserSecurity customeUser = new CustomUserSecurity();
					customeUser.setUser(user);
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							customeUser, null, customeUser.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			} catch (Exception ex) {
				HttpServletResponse response1 = (HttpServletResponse) response;
				response1.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response1.getWriter().write("Unauthorized");
			}
		}
		chain.doFilter(request, response);
	}
}
