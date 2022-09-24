package com.cellphoneshop.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	private JpaUserDetailsService jpaUserDetailsService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		UserDetails user = jpaUserDetailsService.loadUserByUsername(username);

		System.out.println(user.getUsername());

		if (password.equals(user.getPassword())) {
			return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
		} else {
			throw new BadCredentialsException("unauthorized");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
}

/*
AbstractAuthenticationToken
AbstractOAuth2TokenAuthenticationToken
AnonymousAuthenticationToken
BearerTokenAuthentication
BearerTokenAuthenticationToken
CasAssertionAuthenticationToken
CasAuthenticationToken
JaasAuthenticationToken
JwtAuthenticationToken
OAuth2AuthenticationToken
OAuth2AuthorizationCodeAuthenticationToken
OAuth2LoginAuthenticationToken
OpenIDAuthenticationToken
PreAuthenticatedAuthenticationToken
RememberMeAuthenticationToken
RunAsUserToken
Saml2Authentication
Saml2AuthenticationToken
TestingAuthenticationToken
UsernamePasswordAuthenticationToken
*/
