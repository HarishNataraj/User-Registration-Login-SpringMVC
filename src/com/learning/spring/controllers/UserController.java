package com.learning.spring.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.learning.spring.models.User;
import com.learning.spring.services.UserService;

@Controller
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
			model.addAttribute("name", session.getAttribute("user"));
			return "welcome";
		}
		return "login";
	}

	@PostMapping("/login")
	public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password,
			Model model, HttpSession session) {
		
		boolean status = userService.authenticateUser(email, password);
		if(status) {
			session.setAttribute("user", email);
			model.addAttribute("name", session.getAttribute("user"));
			return "welcome";
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
