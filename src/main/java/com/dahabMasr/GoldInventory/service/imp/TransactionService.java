package com.dahabMasr.GoldInventory.service.imp;

import com.dahabMasr.GoldInventory.model.Dto.InventoryWithCountRes;
import com.dahabMasr.GoldInventory.model.Entity.Transaction;
import com.dahabMasr.GoldInventory.repository.TransactionRepository;
import com.dahabMasr.GoldInventory.service.TransactionServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService implements TransactionServiceInterface {

    @Autowired
    TransactionRepository TransactionRepository;

    public Transaction save(Transaction entity){
        return  TransactionRepository.save(entity);
    }
}
