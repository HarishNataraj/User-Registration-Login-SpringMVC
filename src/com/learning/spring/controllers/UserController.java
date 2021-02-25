package com.learning.spring.controllers;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
	
	private static final Logger logger = Logger.getLogger(UserController.class.getName()); 

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
		model.addAttribute("user", new User());
		return "register";
	}

	@PostMapping("/register")
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
		
		try {
			if (bindingResult.hasErrors()) {
				return "register";
			} else {
				user.setUserId(getHash(user.getEmail()));
				boolean status = userService.saveUser(user);
				if (status) {
					model.addAttribute("message", "Registration successfull");
				} else {
					model.addAttribute("message", "Registration failed");
				}
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return "register";
	}

	private String getHash(String email) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		byte[] messageDigest = md.digest(email.getBytes()); 
		BigInteger no = new BigInteger(1, messageDigest);
		String hashtext = no.toString(16);
		return hashtext;
	}

	@GetMapping("/login")
	public String getLoginPage(HttpSession session, Model model) {
		try {
			if (session.getAttribute("user") != null) {
				model.addAttribute("name", session.getAttribute("userId"));
				return "redirect:/category/";
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return "login";
	}

	@PostMapping("/login")
	public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model,
			HttpSession session) {
		
		try {
			String user_id = userService.authenticateUser(email, password);
			if (user_id != null) {
				session.setAttribute("userId", user_id);
				model.addAttribute("name", session.getAttribute("userId"));
				return "redirect:/category/";
			} else {
				model.addAttribute("message", "Login failed");
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return "login";

	}

	@GetMapping("/logout")
	public String logoutUser(HttpSession session) {

		session.invalidate();
		return "login";
	}

}
