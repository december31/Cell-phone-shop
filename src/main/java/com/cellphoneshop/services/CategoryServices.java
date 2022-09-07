package com.cellphoneshop.services;

import com.cellphoneshop.dao.CategoryRepository;
import com.cellphoneshop.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServices {

	private final CategoryRepository categoryRepository;

	@Autowired
	public CategoryServices(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public void addNewCategory(Category category) {
		categoryRepository.save(category);
	}

	public List<Category> getAllCategory() {
		return categoryRepository.findAll();
	}

	public void deleteCategory(Long id) {
		Category category = categoryRepository.findById(id).orElse(null);
		if (category != null) {
			categoryRepository.delete(category);
		}
	}

	public void saveCategory(Category category) {
		categoryRepository.save(category);
	}

	public void updateCategory(Category category) {
		if(category.getId() == -1) {saveCategory(category); return;}
		if (category.getName().equals("")) { deleteCategory(category.getId()); return;}

		Category savedCategory = findCategory(category);
		if (savedCategory == null) {
			saveCategory(category);
		} else {
			if (!savedCategory.getName().equals(category.getName())) {
				categoryRepository.update(category.getId(), category.getName());
			}
		}
	}

	public Category findCategory(Category category) {
		return categoryRepository.findById(category.getId()).orElse(null);
	}

	public Category findById(Long id) {
		return categoryRepository.findById(id).orElse(null);
	}

}
