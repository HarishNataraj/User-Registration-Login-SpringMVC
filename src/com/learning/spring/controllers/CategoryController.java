package com.learning.spring.controllers;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.learning.spring.models.Category;
import com.learning.spring.services.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {

	private CategoryService categoryService;
	private static final Logger logger = Logger.getLogger(CategoryController.class.getName()); 

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping("/")
	public String getCategoryPage(HttpSession session, Model model, HttpServletRequest request) {
		try {
			if (session.getAttribute("userId") != null) {
				String user_id = (String) session.getAttribute("userId");
				model.addAttribute("category", new Category());
				model.addAttribute("categoryList", categoryService.getAllCategories(user_id));
				return "category";
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return "login";
	}

	@PostMapping("/addCategory")
	public String addNewCategory(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult,
			HttpSession session, RedirectAttributes redirectAttributes) {
		try {
			if (bindingResult.hasErrors()) {
				return "redirect:/category/";
			} else {
				String user_id = (String) session.getAttribute("userId");
				category.setCategory_id(getHash(user_id+""+category.getCategoryName()));
				if (categoryService.addCategory(user_id, category)) {
					redirectAttributes.addFlashAttribute("message", "Category added successfully");
				} else {
					redirectAttributes.addFlashAttribute("message", "Cannot add the category");
				}
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return "redirect:/category/";
	}

	@GetMapping(value = "/deleteCategory/{categoryId}")
	public String deleteCategory(@PathVariable("categoryId") String categoryId, RedirectAttributes redirectAttributes) {
		try {
			if (categoryService.deleteCategory(categoryId)) {
				redirectAttributes.addFlashAttribute("message", "Category deleted");
			} else {
				redirectAttributes.addFlashAttribute("message", "Failed to delete Category");
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return "redirect:/category/";

	}
	
	private String getHash(String categoryId) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		byte[] messageDigest = md.digest(categoryId.getBytes()); 
		BigInteger no = new BigInteger(1, messageDigest);
		String hashtext = no.toString(16);
		return hashtext;
	}

}
