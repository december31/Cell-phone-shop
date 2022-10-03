package com.cellphoneshop.services;

import com.cellphoneshop.dao.RoleRepository;
import com.cellphoneshop.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServices {
	private final RoleRepository roleRepository;

	@Autowired
	public RoleServices(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public List<Role> getAllRoles() {
		return roleRepository.findAll();
	}

	public boolean deleteRole(Role role) {
		if(role == null) return false;
		else {
			roleRepository.delete(role);
			return true;
		}
	}

	public boolean deleteRole(Long roleId) {
		Optional<Role> optRole = roleRepository.findById(roleId);
		Role role = null;
		if(optRole.isPresent()) {
			role = optRole.get();
		}
		return deleteRole(role);
	}

	public Role findById(Long id) {
		Optional<Role> optRole = roleRepository.findById(id);
		return optRole.orElse(null);
	}

	public Role findByName(String name) {
		List<Role> roles = roleRepository.findByName(name);
		if(roles.size() > 0) {
			return roles.get(0);
		}
		return null;
	}

	public void addRole(Role role) {
		roleRepository.save(role);
	}

}
