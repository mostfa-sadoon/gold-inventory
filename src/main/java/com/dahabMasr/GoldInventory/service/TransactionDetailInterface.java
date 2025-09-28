package com.dahabMasr.GoldInventory.service;

import com.dahabMasr.GoldInventory.model.Entity.TransactionDetail;

import java.util.List;

public interface TransactionDetailInterface {
   public TransactionDetail save(TransactionDetail entity);
   public List<TransactionDetail> findAll(Long Transaction_id);
}
