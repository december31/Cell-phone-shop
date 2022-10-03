package com.cellphoneshop.controllers;

import com.cellphoneshop.Const.ResponseTextConst;
import com.cellphoneshop.Const.RoleConst;
import com.cellphoneshop.jwt.Jwt;
import com.cellphoneshop.models.Category;
import com.cellphoneshop.models.Product;
import com.cellphoneshop.models.User;
import com.cellphoneshop.services.CategoryServices;
import com.cellphoneshop.services.ProductServices;
import com.cellphoneshop.services.UserServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping(path = "product")
public class ProductController {
	private final ProductServices productServices;
	private final CategoryServices categoryServices;

	@Autowired
	public ProductController(ProductServices productServices, CategoryServices categoryServices) {
		this.productServices = productServices;
		this.categoryServices = categoryServices;
	}

	@ResponseBody
	@GetMapping
	public List<Product> products() {
		return productServices.getAllProducts();
	}

	@ResponseBody
	@PostMapping(path = "new-product")
	public String newProduct(HttpServletRequest request) throws IOException, ServletException {
//		request.setCharacterEncoding("UTF-8");

//		check user's permission
//		? why cookies is null
//		System.out.println(Arrays.toString(request.getCookies()));
//		User user = userServices.getUserFromCookie(request);
//		if(user == null) return "sign in is required";
//		String userPermissionName = userServices.getPermission(user).getName();
//		if(userPermissionName.equals(PermissionConst.USER_PERMISSION)) {
//			return ResponseTextConst.NO_PERMISSION;
//		}

		String token = request.getParameter("token");
		if (token == null) return ResponseTextConst.NO_PERMISSION;

		String userString = Jwt.decodeJWT(token).get("user", String.class);
		User user = new ObjectMapper().readValue(userString, User.class);

		System.out.println(user);

		if (!(user.getRole().getName().equals(RoleConst.ADMIN) || user.getRole().getName().equals(RoleConst.EDITOR)))
			return ResponseTextConst.NO_PERMISSION;

		// get category id
		long categoryId;
		try {
			categoryId = Long.parseLong(request.getParameter("category-id"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return ResponseTextConst.FAILED;
		}

		Category category = categoryServices.findById(categoryId);
		if(category == null) return ResponseTextConst.FAILED;

		String name = request.getParameter("name");
		Double price = Double.parseDouble(request.getParameter("price"));
		String description = request.getParameter("description");

		// get image
		Part imagePart = request.getPart("image");
		String realPath = request.getServletContext().getRealPath("asset/image/device");
		String fileName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();

		Path path = Paths.get(realPath);
		if (!Files.exists(path)) {
			Files.createDirectories(path);
		}

		// store image on server
		imagePart.write(realPath + "/" + fileName);
		String imageURL = "asset/image/device/" + fileName;

		Product product = new Product(name, imageURL, price, description, 4.5);
		product.setCategory(category);

		productServices.saveProduct(product);

		return ResponseTextConst.SUCCESS;
	}

	@ResponseBody
	@PostMapping(path = "delete-product")
	public void deleteProduct(HttpServletRequest request) {
		Long id = Long.valueOf(request.getHeader("id"));
		productServices.deleteProduct(id);
	}
}
