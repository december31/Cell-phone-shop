package com.cellphoneshop.securityConfig;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

//@Component
public class MyJdbcUsersDetailsManager extends JdbcUserDetailsManager {

	public MyJdbcUsersDetailsManager(DataSource dataSource) {
		super(dataSource);
		String usersQuery = "select username, password, 'true' as enable from [user] where email = ?";
		String authoritiesQuery = "" +
				"SELECT [user].username, ROLE.name " +
				"FROM [user] INNER JOIN ROLE " +
				"ON [user].ROLE_ID = ROLE.ID " +
				"where [user].email = ?";
		this.setUsersByUsernameQuery(usersQuery);
		this.setAuthoritiesByUsernameQuery(authoritiesQuery);
	}
}
