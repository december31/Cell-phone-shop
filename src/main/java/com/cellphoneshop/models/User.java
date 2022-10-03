package com.cellphoneshop.models;

import javax.persistence.*;

@Entity
@Table(name = "ACCOUNT")
public class User {

	@Id
	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@ManyToOne(fetch = FetchType.EAGER)
	private Role role;

	public User() {
	}

	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return
				"email: " + email + "\n" +
				"password: " + password + "\n" +
				"role: " + role.toString();
	}
}
