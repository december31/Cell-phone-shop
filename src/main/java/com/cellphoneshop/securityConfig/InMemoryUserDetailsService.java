package com.cellphoneshop.securityConfig;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class InMemoryUserDetailsService implements UserDetailsService {
	private final List<UserDetails> users;

	public InMemoryUserDetailsService(List<UserDetails> users) {
		this.users = users;
	}

	public void addNewUser(UserDetails user) {
		users.add(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		return users.stream()
				.filter(
						u -> u.getUsername().equals(username)
				)
				.findFirst()
				.orElseThrow(
						() -> new UsernameNotFoundException("User not found")
				);
	}
}
