package com.dahabMasr.GoldInventory.controller.web;

import com.dahabMasr.GoldInventory.model.Entity.Transaction;
import com.dahabMasr.GoldInventory.service.imp.TransactionService;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class TransactionAdminController {

    @Autowired
    TransactionService TransactionService;

  @GetMapping("/transactions")
  public  String Transaction(@RequestParam(defaultValue = "0") int page , @RequestParam(defaultValue = "20") int size, Model model){

      Page<Transaction> transactionPage = TransactionService.findPaginated(page, size);

      model.addAttribute("transactions", transactionPage.getContent()); // actual data
      model.addAttribute("currentPage", page);
      model.addAttribute("totalPages", transactionPage.getTotalPages());
      return "admin/transactions";
  }

}
