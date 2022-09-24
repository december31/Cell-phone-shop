package com.cellphoneshop.securityConfig;

import com.cellphoneshop.Const.ResponseTextConst;
import com.cellphoneshop.jwt.Jwt;
import com.cellphoneshop.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DecodeTokenFilter implements Filter {

	private final AuthenticationProvider authenticationProvider;

	public DecodeTokenFilter(AuthenticationProvider authenticationProvider) {
		this.authenticationProvider = authenticationProvider;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Filter.super.init(filterConfig);
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String token = request.getParameter("token");
		Claims claims = null;
		User user = null;
		if (token == null || token.isEmpty()) {
			filterChain.doFilter(request, response);
			return;
		}
		try {
			claims = Jwt.decodeJWT(token);
			String userString = claims.get("user", String.class);
			user = new ObjectMapper().readValue(userString, User.class);
			Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
			authentication = authenticationProvider.authenticate(authentication);
			if(authentication.isAuthenticated()) onSuccessAuthentication(authentication);
			filterChain.doFilter(request, response);
		} catch (ExpiredJwtException | NullPointerException e) {
			System.out.println("token is expired");
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void destroy() {
		Filter.super.destroy();
	}

	private void onSuccessAuthentication(Authentication authentication) {
		SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
		securityContext.setAuthentication(authentication);
		SecurityContextHolder.setContext(securityContext);
	}
}
