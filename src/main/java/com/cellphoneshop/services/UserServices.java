package com.cellphoneshop.services;

import com.cellphoneshop.dao.PermissionRepository;
import com.cellphoneshop.dao.UserRepository;
import com.cellphoneshop.jwt.Encoder;
import com.cellphoneshop.models.Permission;
import com.cellphoneshop.models.Role;
import com.cellphoneshop.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices {

	private final UserRepository userRepository;
	private final PermissionRepository permissionRepository;

	@Autowired
	public UserServices(UserRepository userRepository, PermissionRepository permissionRepository) {
		this.userRepository = userRepository;
		this.permissionRepository = permissionRepository;
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public User findByEmailAndPassword(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password);
	}

	public User checkUser(User user) {
		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}

	public User checkUser(User user, boolean isPasswordEncoded) {
		if(!isPasswordEncoded) user.setPassword(Encoder.encode(user.getPassword()));
		return checkUser(user);
	}

	public boolean saveUser(User user) {
		User userSaved = findByEmail(user.getEmail());
		user.setPassword(Encoder.encode(user.getPassword()));
		if (null == userSaved) {
			userRepository.save(user);
			return true;
		}
		return false;
	}

	public void updateUser(User user) {
		userRepository.update(user.getEmail(), user.getPassword(), user.getRole());
	}
	public void deleteUser(User user) {
		userRepository.deleteById(user.getEmail());
	}

	public Permission getPermission(User user) {
		Role role = user.getRole();
		return null;
	}

}
