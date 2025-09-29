package com.dahabMasr.GoldInventory.service;

import com.dahabMasr.GoldInventory.controller.api.TransactionController;
import com.dahabMasr.GoldInventory.model.Dto.InventoryWithCountRes;
import com.dahabMasr.GoldInventory.model.Dto.TransactionReq;
import com.dahabMasr.GoldInventory.model.Entity.Transaction;
import jakarta.servlet.http.PushBuilder;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface TransactionServiceInterface {

  public  Transaction save(Transaction entity);
  public  Optional<Transaction> find(Long id);
  public  TransactionController.Result calculate(String type, Double amount);
  public  Transaction updateStatus(Long id , String status);
  public  TransactionController.Result create(TransactionReq dto);
  public Page<Transaction> findPaginated(int page, int size);
}
