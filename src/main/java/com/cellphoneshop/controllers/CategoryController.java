package com.cellphoneshop.controllers;

import com.cellphoneshop.Const.PermissionConst;
import com.cellphoneshop.Const.ResponseTextConst;
import com.cellphoneshop.models.Category;
import com.cellphoneshop.services.CategoryServices;
import com.cellphoneshop.services.UserServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(path = "category")
public class CategoryController {

	private final CategoryServices categoryServices;
	private final UserServices userServices;

	@Autowired
	public CategoryController(CategoryServices categoryServices, UserServices userServices) {
		this.categoryServices = categoryServices;
		this.userServices = userServices;
	}

	@GetMapping
	public List<Category> getAllCategory() {
		return categoryServices.getAllCategory();
	}

	@PostMapping("new-category")
	public String addNewCategory(HttpServletRequest request) {
			String name = request.getParameter("name");
			Long id = Long.parseLong(request.getParameter("id"));
			categoryServices.addNewCategory(new Category(id, name));
			return ResponseTextConst.SUCCESS;
	}

	@PostMapping("save-categories")
	public String saveAllCategories(HttpServletRequest request) throws JsonProcessingException {
		Category[] categories = new ObjectMapper().readValue(request.getParameter("data"), Category[].class);
		for(Category category : categories) {
			categoryServices.updateCategory(category);
		}
		return ResponseTextConst.SUCCESS;
	}

}
