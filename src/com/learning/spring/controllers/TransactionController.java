package com.learning.spring.controllers;

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

	private TransactionService transactionService;

	public void setTransactionService(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@GetMapping(value = "/{categoryId}")
	public String getTransactionForm(@PathVariable("categoryId") int id, Model model, HttpSession session) {
		if (session.getAttribute("userId") != null) {
			session.setAttribute("categoryId", id);
			Transaction transaction = new Transaction();
			model.addAttribute("transaction", transaction);
			return "transaction";
		}

		return "login";

	}

	@PostMapping("/addTransaction")
	public String addNewTransaction(@Valid @ModelAttribute("transaction") Transaction transaction,
			BindingResult bindingResult, Model model, HttpSession session) {

		if (bindingResult.hasErrors()) {
			return "transaction";
		} else {
			transaction.setCategoryId((int) session.getAttribute("categoryId"));
			transaction.setUserId((int) session.getAttribute("userId"));
			if (transactionService.addTransaction(transaction)) {
				model.addAttribute("message", "Transaction added successfully");
			} else {
				model.addAttribute("message", "Failed to add transaction");
			}

			return "transaction";
		}
	}

	@GetMapping(value = "/viewTransactions/{categoryId}")
	public String getTransactions(@PathVariable("categoryId") int categoryId, HttpSession session, Model model) {

		model.addAttribute("transactionList",
				transactionService.getTransactions(categoryId, (int) session.getAttribute("userId")));
		model.addAttribute("expense",
				transactionService.calculateExpense(categoryId, (int) session.getAttribute("userId")));
		return "transactionList";
	}

	@GetMapping(value = "/deleteTransaction/{transactionId}")
	public String deleteTransaction(@PathVariable("transactionId") int transactionId, HttpSession session,
			RedirectAttributes redirectAttributes) {
		int categoryId = (int) session.getAttribute("categoryId");
		if (transactionService.deleteTransaction(transactionId)) {
			redirectAttributes.addFlashAttribute("message", "Transaction deleted successfully");
		} else {
			redirectAttributes.addFlashAttribute("message", "Transaction delete failed");
		}
		return "redirect:/transactions/viewTransactions/" + categoryId;
	}

}
