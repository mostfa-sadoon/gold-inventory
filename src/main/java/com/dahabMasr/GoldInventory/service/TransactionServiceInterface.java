package com.dahabMasr.GoldInventory.service;

import com.dahabMasr.GoldInventory.model.Dto.InventoryWithCountRes;
import com.dahabMasr.GoldInventory.model.Entity.Transaction;

public interface TransactionServiceInterface {

  public Transaction save(Transaction entity);
}
