package com.dahabMasr.GoldInventory.service;

import com.dahabMasr.GoldInventory.model.Dto.InventoryWithCountRes;
import com.dahabMasr.GoldInventory.model.Entity.Transaction;

import java.util.Optional;

public interface TransactionServiceInterface {

  public  Transaction save(Transaction entity);
  public  Transaction find(Long id);
}
