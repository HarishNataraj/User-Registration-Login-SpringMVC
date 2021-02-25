package com.learning.spring.controllers;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

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

import com.learning.spring.models.Transaction;
import com.learning.spring.services.TransactionService;

@Controller
@RequestMapping("/transactions")
public class TransactionController {
	private static final Logger logger = Logger.getLogger(TransactionController.class.getName()); 

	private TransactionService transactionService;

	public void setTransactionService(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@GetMapping(value = "/{categoryId}")
	public String getTransactionForm(@PathVariable("categoryId") String id, Model model, HttpSession session) {
		try {
			if (session.getAttribute("userId") != null) {
				session.setAttribute("categoryId", id);
				Transaction transaction = new Transaction();
				model.addAttribute("transaction", transaction);
				return "transaction";
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return "login";

	}

	@PostMapping("/addTransaction")
	public String addNewTransaction(@Valid @ModelAttribute("transaction") Transaction transaction,
			BindingResult bindingResult, Model model, HttpSession session) {
		try {
			if (bindingResult.hasErrors()) {
				return "transaction";
			} else {
				transaction.setCategoryId((String) session.getAttribute("categoryId"));
				transaction.setUserId((String) session.getAttribute("userId"));
				transaction.setTransactionId(getHash(transaction.getUserId()+""+transaction.getCategoryId()+""+transaction.getTransactionDate()));
				if (transactionService.addTransaction(transaction)) {
					model.addAttribute("message", "Transaction added successfully");
				} else {
					model.addAttribute("message", "Failed to add transaction");
				}

			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return "transaction";
	}

	@GetMapping(value = "/viewTransactions/{categoryId}")
	public String getTransactions(@PathVariable("categoryId") String categoryId, HttpSession session, Model model) {
		try {
			model.addAttribute("transactionList",
					transactionService.getTransactions(categoryId, (String) session.getAttribute("userId")));
			model.addAttribute("expense",
					transactionService.calculateExpense(categoryId, (String) session.getAttribute("userId")));
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return "transactionList";
	}

	@GetMapping(value = "/deleteTransaction/{transactionId}")
	public String deleteTransaction(@PathVariable("transactionId") String transactionId, HttpSession session,
			RedirectAttributes redirectAttributes) {
		String categoryId = (String) session.getAttribute("categoryId");
		try {
			if (transactionService.deleteTransaction(transactionId)) {
				redirectAttributes.addFlashAttribute("message", "Transaction deleted successfully");
			} else {
				redirectAttributes.addFlashAttribute("message", "Transaction delete failed");
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
		return "redirect:/transactions/viewTransactions/" + categoryId;
	}
	
	private String getHash(String transactionId) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		byte[] messageDigest = md.digest(transactionId.getBytes()); 
		BigInteger no = new BigInteger(1, messageDigest);
		String hashtext = no.toString(16);
		return hashtext;
	}

}
