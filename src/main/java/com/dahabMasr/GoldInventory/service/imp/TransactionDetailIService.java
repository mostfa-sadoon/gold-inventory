package com.dahabMasr.GoldInventory.service.imp;

import com.dahabMasr.GoldInventory.model.Entity.TransactionDetail;
import com.dahabMasr.GoldInventory.repository.TransactionDetailRepository;
import com.dahabMasr.GoldInventory.service.TransactionDetailInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionDetailIService implements TransactionDetailInterface {

    @Autowired
    TransactionDetailRepository TransactionDetailRepository;

  public TransactionDetail save(TransactionDetail entity){
       return  TransactionDetailRepository.save(entity);
  }
}
