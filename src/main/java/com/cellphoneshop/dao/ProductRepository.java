package com.cellphoneshop.dao;

import com.cellphoneshop.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	@Query("select p from Product p where p.price > ?1 and p < ?2")
	Product findByPriceInRange(Double from, Double to);

//	@Override
//	@Query("select p.id, p.imageUrl, p.name, p.price, p.description, p.ratting, p.category.name from Product p")

	List<Product> findAllByOrderByIdDesc();
}
