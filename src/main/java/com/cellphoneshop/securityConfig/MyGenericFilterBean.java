package com.cellphoneshop.securityConfig;

import com.cellphoneshop.jwt.Jwt;
import com.cellphoneshop.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyGenericFilterBean extends GenericFilterBean {

	public MyGenericFilterBean(AuthenticationProvider authenticationProvider) {
		this.authenticationProvider = authenticationProvider;
	}

	private AuthenticationProvider authenticationProvider;

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		String token = request.getParameter("token");
		Claims claims = null;
		User user = null;
		if (token == null || token.isEmpty()) {
			return;
		}
		try {
			claims = Jwt.decodeJWT(token);
			String userString = claims.get("user", String.class);
			user = new ObjectMapper().readValue(userString, User.class);

//			Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
//			authentication = authenticationProvider.authenticate(authentication);

			filterChain.doFilter(request, response);
		} catch (ExpiredJwtException | NullPointerException e) {
			System.out.println("token is expired");
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
