package com.cellphoneshop.dao;

import com.cellphoneshop.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	@Transactional
	@Modifying
	@Query("update Category p set p.name = ?2 where p.id = ?1")
	void update(Long id, String name);
}
