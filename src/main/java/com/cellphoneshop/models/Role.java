package com.cellphoneshop.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Role {
	@Id
	@GeneratedValue
	private Long id;

	private String name;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Permission> permissions;

	public Role() {
	}

	public Role(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return String.format("role: {id: %d, name: %s}", id, name);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
}