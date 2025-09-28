package com.dahabMasr.GoldInventory.repository;

import com.dahabMasr.GoldInventory.model.Entity.Transaction;
import com.dahabMasr.GoldInventory.model.Entity.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionDetailRepository extends JpaRepository<TransactionDetail,Long> {
    List<TransactionDetail> findAllByTransaction_Id(Long transactionId);
}
