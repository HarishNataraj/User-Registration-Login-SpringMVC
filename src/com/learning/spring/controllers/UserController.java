package com.learning.spring.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.learning.spring.models.User;
import com.learning.spring.services.UserService;

@Controller
@RequestMapping("/")
public class UserController {

	private User user;
	private UserService userService;

	public void setUser(User user) {
		this.user = user;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/")
	public String getRegisterPage(Model model) {
		model.addAttribute("user", user);
		return "register";
	}
	

	@PostMapping("/register")
	public String registerUser(@ModelAttribute("user") User user, Model model) {
		
		boolean status = userService.saveUser(user);
		if(status) {
			model.addAttribute("message", "Registration successfull");
		} else {
			model.addAttribute("message", "Registration failed");
		}
		
		return "register";
	}

	@GetMapping("/login")
	public String getLoginPage(HttpSession session, Model model) {
		if(session.getAttribute("user") != null) {
			model.addAttribute("name", session.getAttribute("userId"));
			return "redirect:/category/";
		}
		return "login";
	}

	@PostMapping("/login")
	public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password,
			Model model, HttpSession session) {
		
		int user_id = userService.authenticateUser(email, password);
		if(user_id > 0) {
			session.setAttribute("userId", user_id);
			model.addAttribute("name", session.getAttribute("userId"));
			return "redirect:/category/";
		} else {
			model.addAttribute("message", "Login failed");
			return "login";
		}
	}
	
	@GetMapping("/logout")
	public String logoutUser(HttpSession session) {
		
		session.invalidate();
		return "login";
	}
	
}
