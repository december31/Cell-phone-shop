package com.cellphoneshop.services;

import com.cellphoneshop.dao.ProductRepository;
import com.cellphoneshop.models.Product;
import com.cellphoneshop.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class ProductServices {
	private final ProductRepository productRepository;
	private final UserServices userServices;

	@Autowired
	public ProductServices(ProductRepository productRepository, UserServices userServices) {
		this.productRepository = productRepository;
		this.userServices = userServices;
	}

	public List<Product> getAllProducts() {
		return productRepository.findAllByOrderByIdDesc();
	}

	public void saveProduct(Product product) {
		productRepository.save(product);
	}

	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
}
