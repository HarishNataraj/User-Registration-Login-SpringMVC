package com.learning.spring.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.learning.spring.models.Category;
import com.learning.spring.services.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	private CategoryService categoryService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping("/")
	public String getCategoryPage(HttpSession session, Model model,HttpServletRequest request) {
		if(session.getAttribute("userId") != null ) {
			int user_id = (int) session.getAttribute("userId");
			model.addAttribute("category", new Category());
			model.addAttribute("categoryList", categoryService.getAllCategories(user_id));
			model.addAttribute("message", request.getParameter("message"));
			return "category";
		}
		return "login";
	}
	
	@PostMapping("/addCategory")
	public String addNewCategory(@ModelAttribute("category") Category category, HttpSession session, Model model) {
		int user_id = (int) session.getAttribute("userId");
		if(categoryService.addCategory(user_id, category.getCategoryName())) {
			model.addAttribute("message", "Category added successfully");
		} else {
			model.addAttribute("message", "Cannot add category");
		}
		return "redirect:/category/";
	}
	
	@GetMapping(value = "/deleteCategory/{categoryId}")
	public String deleteCategory(@PathVariable("categoryId") int categoryId, Model model) {
		
		if(categoryService.deleteCategory(categoryId)) {
			model.addAttribute("message", "Category deleted");
		} else {
			model.addAttribute("message", "Failed to delete Category");
		}
		
		return "redirect:/category/";
		
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}