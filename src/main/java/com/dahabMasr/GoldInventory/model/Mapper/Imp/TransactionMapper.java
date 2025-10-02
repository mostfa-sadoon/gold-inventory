package com.dahabMasr.GoldInventory.model.Mapper.Imp;

import com.dahabMasr.GoldInventory.model.Dto.Res.InventoryRes;
import com.dahabMasr.GoldInventory.model.Dto.Res.TransactionDetailRes;
import com.dahabMasr.GoldInventory.model.Dto.Res.TransactionRes;
import com.dahabMasr.GoldInventory.model.Dto.TransactionReq;
import com.dahabMasr.GoldInventory.model.Entity.Customer;
import com.dahabMasr.GoldInventory.model.Entity.Transaction;
import com.dahabMasr.GoldInventory.model.Entity.TransactionDetail;
import com.dahabMasr.GoldInventory.model.Mapper.TransactionMapperInterface;
import com.dahabMasr.GoldInventory.repository.CustomerRepository;
import com.dahabMasr.GoldInventory.service.imp.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

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

    public TransactionRes toResponse(Transaction entity) {
                TransactionRes res = TransactionRes.builder()
                .id(entity.getId())
                .status(entity.getStatus())
                .amount(entity.getAmount())
                .build();
                res.setTransactionDetailRes(
                        entity.getDetailes().stream().map((TransactionDetail detail) -> {
                            TransactionDetailRes d = new TransactionDetailRes();
                            d.setId(detail.getId());
                            d.setQuantity(detail.getQuantity());
                            d.setWeight(detail.getWeight());
                            // map inventory
                            InventoryRes inv = new InventoryRes();
                            inv.setName(detail.getInventory().getName());
                            inv.setQuantity(detail.getInventory().getQuantity());
                            inv.setId(detail.getInventory().getId());
                            d.setInventoryRes(inv);
                            return d;
                        }).collect(Collectors.toList())
                );
       return res;
    }
}
