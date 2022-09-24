package com.cellphoneshop.securityConfig;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		try {
			Authentication authenticationResult = this.attemptAuthentication(req, resp);
			if (authenticationResult == null) {
				return;
			}
			chain.doFilter(request, response);
			this.successfulAuthentication(req, resp, chain, authenticationResult);
		} catch (InternalAuthenticationServiceException var5) {
			this.logger.error("An internal error occurred while trying to authenticate the user.", var5);
			this.unsuccessfulAuthentication(req, resp, var5);
		} catch (AuthenticationException var6) {
			this.unsuccessfulAuthentication(req, resp, var6);
		}

	}
}
