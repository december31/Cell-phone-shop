package com.cellphoneshop.controllers;

import com.cellphoneshop.Const.ResponseTextConst;
import com.cellphoneshop.Const.RoleConst;
import com.cellphoneshop.jwt.Encoder;
import com.cellphoneshop.jwt.Jwt;
import com.cellphoneshop.models.Role;
import com.cellphoneshop.models.User;
import com.cellphoneshop.services.RoleServices;
import com.cellphoneshop.services.UserServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.JSqlParserUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping(path = "user")
public class UserController {
	@Autowired
	private UserServices userServices;

	@Autowired
	private RoleServices roleServices;

	@GetMapping
	public List<User> getUsers() {
		return userServices.getAllUsers();
	}

	@PostMapping(path = "sign-in")
	public String userSignIn(HttpServletRequest request) throws JsonProcessingException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		if (email == null) return ResponseTextConst.FAILED;
		else {
			User user = new User(email, password);

			user = userServices.checkUser(user, false);
			if (user != null) {
				return Jwt.createJWT(user, 100000000);
			} else {
				return ResponseTextConst.FAILED;
			}
		}
	}

	@GetMapping(path = "auto-sign-in")
	public String userAutoSignIn(HttpServletRequest request) throws JsonProcessingException {
		String token = request.getParameter("token");
		Claims claims = null;
		User user = null;
		try {
			if (token == null || token.equals("")) {
				return ResponseTextConst.FAILED;
			}
			claims = Jwt.decodeJWT(token);

			String userString = claims.get("user", String.class);

			user = new ObjectMapper().readValue(userString, User.class);

			user = userServices.checkUser(user, true);

			if (user == null) {
				return ResponseTextConst.FAILED;
			}
		} catch (ExpiredJwtException e) {
			System.out.println("token is expired");
			return ResponseTextConst.FAILED;
		}
		return user.getRole().getName() + ":" + user.getEmail();
	}

	@PostMapping(path = "sign-up")
	public String userSignUp(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		if (email == null || email.equals("")) return ResponseTextConst.FAILED;
		else {

			User user = new User(email, password);
			Role role = roleServices.findByName(RoleConst.USER);

			if (null == role) return ResponseTextConst.FAILED;

			user.setRole(role);
			if (userServices.saveUser(user)) {
				return Jwt.createJWT(user, 100000);
			} else {
				return ResponseTextConst.FAILED;
			}
		}
	}

	@PostMapping("save-users")
	public String saveAllUsers(HttpServletRequest request) throws JsonProcessingException {
		User[] users = new ObjectMapper().readValue(request.getParameter("users"), User[].class);
		User[] deletedUsers = new ObjectMapper().readValue(request.getParameter("usersDeleted"), User[].class);

		System.out.println(Arrays.toString(users));

		User savedUser = null;
		for (User user : users) {
			savedUser = userServices.findByEmail(user.getEmail());
			if (null == savedUser) userServices.saveUser(user);
			else {
				if (user.getPassword().equals("")) user.setPassword(savedUser.getPassword());
				else user.setPassword(Encoder.encode(user.getPassword()));
				userServices.updateUser(user);
			}
		}
		for (User user : deletedUsers) {
			savedUser = userServices.findByEmail(user.getEmail());
			if (savedUser != null) userServices.deleteUser(user);
		}
		return ResponseTextConst.SUCCESS;
	}

}
