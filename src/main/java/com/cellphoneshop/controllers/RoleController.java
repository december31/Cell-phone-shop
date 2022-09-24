package com.cellphoneshop.controllers;

import com.cellphoneshop.Const.PermissionConst;
import com.cellphoneshop.Const.ResponseTextConst;
import com.cellphoneshop.models.Role;
import com.cellphoneshop.services.RoleServices;
import com.cellphoneshop.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(path = "role")
public class RoleController {

	private final RoleServices roleServices;
	private final UserServices userServices;

	@Autowired
	public RoleController(RoleServices roleServices, UserServices userServices) {
		this.roleServices = roleServices;
		this.userServices = userServices;
	}

	@GetMapping
	public List<Role> getAllRoles() {
		return roleServices.getAllRoles();
	}

	@PostMapping(path = "new")
	public String addNewRole(HttpServletRequest request) {
		Long roleId = Long.parseLong(request.getParameter("id"));
		String roleName = request.getParameter("name");

			Role role = new Role(roleId, roleName);
			roleServices.addRole(role);
			return ResponseTextConst.SUCCESS;
	}

	@PostMapping(path = "delete")
	public String deleteRole(HttpServletRequest request) {
			Long roleId = Long.parseLong(request.getParameter("id"));
			Role role = roleServices.findById(roleId);
			roleServices.deleteRole(role);
			return ResponseTextConst.SUCCESS;
	}

}
