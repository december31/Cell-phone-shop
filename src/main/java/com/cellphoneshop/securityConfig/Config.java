package com.cellphoneshop.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class Config extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationProvider authenticationProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic();
		http.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/user/**").hasAnyAuthority("admin")
				.antMatchers("/new").hasAnyAuthority("admin")
				.antMatchers("/product/new").hasAnyAuthority("admin", "editor");
		http.csrf().disable();
		http.addFilterAfter(new DecodeTokenFilter(authenticationProvider), BasicAuthenticationFilter.class);
//				.antMatchers("new-product").hasAnyAuthority("admin", "editor")
//				.antMatchers("delete-product").hasAnyAuthority("admin", "editor")
//				.anyRequest().authenticated();
//				.and()
//				.formLogin().permitAll()
//				.and()
//				.logout().permitAll()
//				.and()
//				.exceptionHandling().accessDeniedPage("/403");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}

//	@Bean
//	public UserDetailsService userDetailsService(DataSource dataSource) {
//		String usersQuery = "select email, password, 'true' as enable from account where email = ?";
//		String authoritiesQuery = "" +
//				"SELECT ACCOUNT.EMAIL, ROLE.name " +
//				"FROM ACCOUNT INNER JOIN ROLE " +
//				"ON ACCOUNT.ROLE_ID = ROLE.ID " +
//				"where account.email = ?";
//
//		JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
//		userDetailsManager.setUsersByUsernameQuery(usersQuery);
//		userDetailsManager.setAuthoritiesByUsernameQuery(authoritiesQuery);
//		return userDetailsManager;
//	}

}
