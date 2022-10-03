package com.cellphoneshop.dao;

import com.cellphoneshop.models.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
	@Query(value = "select p from Permission p where p.role = ?1")
	Permission findByRoleId(Long id);
}
