package com.cellphoneshop.controllers;

import com.cellphoneshop.Const.PermissionConst;
import com.cellphoneshop.Const.ResponseTextConst;
import com.cellphoneshop.models.Permission;
import com.cellphoneshop.models.Role;
import com.cellphoneshop.services.PermissionServices;
import com.cellphoneshop.services.RoleServices;
import com.cellphoneshop.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "permission")
public class PermissionController {

	private final PermissionServices permissionServices;
	private final RoleServices roleServices;

	@Autowired
	public PermissionController(PermissionServices permissionServices, RoleServices roleServices) {
		this.permissionServices = permissionServices;
		this.roleServices = roleServices;
	}

	@GetMapping
	public List<Permission> getAllPermission() {
		ArrayList<Permission> permissions = (ArrayList<Permission>) permissionServices.getAllPermission();
		permissions.forEach(System.out::println);

		return permissionServices.getAllPermission();
	}

	@PostMapping(path = "new-permission")
	public String addNewPermission(HttpServletRequest request) {
		Long roleId = Long.parseLong(request.getParameter("id"));
		String name = request.getParameter("name");
		Role role = roleServices.findById(roleId);
		permissionServices.addNewPermission(new Permission(role, name));
		return ResponseTextConst.SUCCESS;
	}
}
