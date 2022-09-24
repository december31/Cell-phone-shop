package com.cellphoneshop.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Permission implements Serializable {

	@Id
	private Long id;

	private String name;

	public Permission() {
	}

	public Permission(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "{\n" +
					"\tname: " + name + "\n" +
				"}";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
}
