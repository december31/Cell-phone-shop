package com.cellphoneshop.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Permission implements Serializable {

	@Id
	private Long id;

	@OneToOne
	@MapsId
	private Role role;

	private String name;

	public Permission() {
	}

	public Permission(Role role, String name) {
		this.role = role;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "{\n" +
					"\t" + role + "\n" +
					"\tname: " + name + "\n" +
				"}";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setName(String name) {
		this.name = name;
	}
}
