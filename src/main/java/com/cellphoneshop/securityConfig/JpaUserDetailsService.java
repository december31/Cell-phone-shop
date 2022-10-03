package com.cellphoneshop.securityConfig;

import com.cellphoneshop.dao.RoleRepository;
import com.cellphoneshop.dao.UserRepository;
import com.cellphoneshop.models.Role;
import com.cellphoneshop.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class JpaUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user == null) return null;
		Role role = roleRepository.findById(user.getRole().getId()).orElse(null);
		if(role == null) return null;
		return org.springframework.security.core.userdetails.User
				.withUsername(user.getUsername())
				.password(user.getPassword())
				.authorities(role.getName())
				.build();
	}
}
