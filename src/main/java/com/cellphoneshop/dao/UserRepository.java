package com.cellphoneshop.dao;

import com.cellphoneshop.models.Role;
import com.cellphoneshop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	User findByEmail(String email);
	User findByUsernameAndPassword(String email, String password);

	User findByUsername(String username);

	@Modifying
	@Transactional
	@Query("update User a set a.email = ?1, a.password = ?2, a.role = ?3 where a.email = ?1")
	void update(String email, String password, Role role);

}
