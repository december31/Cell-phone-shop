package com.cellphoneshop.services;

import com.cellphoneshop.dao.PermissionRepository;
import com.cellphoneshop.models.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServices {
	private final PermissionRepository permissionRepository;

	@Autowired
	public PermissionServices(PermissionRepository permissionRepository) {
		this.permissionRepository = permissionRepository;
	}

	public List<Permission> getAllPermission() {
		return permissionRepository.findAll();
	}

	public void addNewPermission(Permission permission) {
		permissionRepository.save(permission);
	}

}
