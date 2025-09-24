package com.dahabMasr.GoldInventory.repository;

import com.dahabMasr.GoldInventory.model.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
