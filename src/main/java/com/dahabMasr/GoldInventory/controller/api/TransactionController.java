package com.dahabMasr.GoldInventory.controller.api;


import com.dahabMasr.GoldInventory.model.Dto.InventoryWithCountRes;
import com.dahabMasr.GoldInventory.model.Dto.PriceRes;
import com.dahabMasr.GoldInventory.model.Dto.TransactionReq;
import com.dahabMasr.GoldInventory.model.Entity.Inventory;
import com.dahabMasr.GoldInventory.model.Entity.Transaction;
import com.dahabMasr.GoldInventory.model.Entity.TransactionDetail;
import com.dahabMasr.GoldInventory.model.Mapper.Imp.TransactionMapper;
import com.dahabMasr.GoldInventory.service.imp.InventoryService;
import com.dahabMasr.GoldInventory.service.imp.TransactionDetailIService;
import com.dahabMasr.GoldInventory.service.imp.TransactionService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TransactionController {

    @Autowired
    InventoryService inventoryService;
    @Autowired
    TransactionService TransactionService;
    @Autowired
    TransactionMapper TransactionMapper;
    @Autowired
    TransactionDetailIService TransactionDetailIService;

    @PostMapping("create")
    public  Result create(@RequestBody TransactionReq dto){
        Result  result = calculate(dto.getType(), dto.getAmount());
        TransactionReq trnas =  new TransactionReq(result.amount , dto.getType(), dto.getCustomer());
        Transaction transaction =  TransactionService.save(TransactionMapper.toEntity(trnas));
        for (InventoryWithCountRes inv : result.inventories){
            TransactionDetail detail = new TransactionDetail();
            Inventory inventory = new Inventory();
            inventory.setId(inv.getId());
            detail.setInventory(inventory);
            detail.setTransaction(transaction);
            detail.setCount(inv.getCount());
            detail.setAmount(inv.getCount()*inv.getWeight());
            TransactionDetailIService.save(detail);
        }

        result.transaction_id = transaction.getId();
        result.status = transaction.getStatus().toString();
        return  result;
    }


   @Data
   public  static class Result{
       public List<InventoryWithCountRes> inventories;
       public double amount;
       public double remaining;
       public Long transaction_id;
       public String status;
   }


   public Result calculate(String type, Double amount){
       Result result = new Result();
       com.dahabMasr.GoldInventory.model.Dto.PriceRes price  = new PriceRes();
       List<Inventory> inventories = inventoryService.getInventoriesByTypeOrderDesc(type);
       double remaining = amount;
       List<InventoryWithCountRes> resultList = new ArrayList<>();
       for (Inventory inv : inventories){

           float pricevalue = type.equals("GOLD") ? price.getGoldBaying() : price.getSelverBaying();
           double piecePrice = inv.getWeight() * pricevalue;


           int count = 0;
           while (remaining >= piecePrice){
               count++;
               remaining -= piecePrice;
           }
           if (count > 0) {
               resultList.add(new InventoryWithCountRes(
                       inv.getId(),
                       inv.getName(),
                       inv.getWeight(),
                       inv.getType(),
                       count
               ));
           }
       }
       result.remaining = remaining;
       result.setInventories(resultList);
       result.amount = amount - remaining;
       return result;
   }

}
