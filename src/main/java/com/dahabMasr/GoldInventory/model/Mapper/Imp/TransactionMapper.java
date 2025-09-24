package com.dahabMasr.GoldInventory.model.Mapper.Imp;

import com.dahabMasr.GoldInventory.model.Dto.TransactionReq;
import com.dahabMasr.GoldInventory.model.Entity.Transaction;
import com.dahabMasr.GoldInventory.model.Mapper.TransactionMapperInterface;
import org.springframework.stereotype.Service;

@Service
public class TransactionMapper implements TransactionMapperInterface {

    public Transaction toEntity(TransactionReq req){
         return  Transaction.builder()
                 .amount(req.getAmount())
                 .build();
    }

}
