package com.cellphoneshop.models;

import javax.persistence.*;

@Entity
@Table(name = "`user`")
public class User {

	@Id @GeneratedValue
	private long id;

	private String email;

	private String username;

	private String password;

	@ManyToOne(fetch = FetchType.EAGER)
	private Role role;

	public User() {
	}

	public User(String username, String email, String password) {
		this.email = email;
		this.password = password;
		this.username = username;
	}

	public User(String username, String password) {
		this.username = username;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
