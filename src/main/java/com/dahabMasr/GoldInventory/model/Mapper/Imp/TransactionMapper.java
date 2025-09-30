package com.dahabMasr.GoldInventory.model.Mapper.Imp;

import com.dahabMasr.GoldInventory.model.Dto.Res.TransactionRes;
import com.dahabMasr.GoldInventory.model.Dto.TransactionReq;
import com.dahabMasr.GoldInventory.model.Entity.Customer;
import com.dahabMasr.GoldInventory.model.Entity.Transaction;
import com.dahabMasr.GoldInventory.model.Mapper.TransactionMapperInterface;
import com.dahabMasr.GoldInventory.repository.CustomerRepository;
import com.dahabMasr.GoldInventory.service.imp.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionMapper implements TransactionMapperInterface {

    @Autowired
    CustomerService CustomerService;

    public Transaction toEntity(TransactionReq req){
        Customer customer = CustomerService.find(req.getCustomer());

         return  Transaction.builder()
                 .amount(req.getAmount())
                 .customer(customer)
                 .build();
    }

//    public TransactionRes toResponse(Transaction entity){
//        return  TransactionRes.builder()
//    }

}
