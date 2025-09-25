package com.dahabMasr.GoldInventory.controller.api;


import com.dahabMasr.GoldInventory.model.Dto.InventoryWithCountRes;
import com.dahabMasr.GoldInventory.model.Dto.PriceRes;
import com.dahabMasr.GoldInventory.model.Dto.TransactionReq;
import com.dahabMasr.GoldInventory.model.Dto.TransactionUpdateReq;
import com.dahabMasr.GoldInventory.model.Entity.Inventory;
import com.dahabMasr.GoldInventory.model.Entity.Transaction;
import com.dahabMasr.GoldInventory.model.Entity.TransactionDetail;
import com.dahabMasr.GoldInventory.model.Mapper.Imp.TransactionMapper;
import com.dahabMasr.GoldInventory.service.imp.InventoryService;
import com.dahabMasr.GoldInventory.service.imp.TransactionDetailIService;
import com.dahabMasr.GoldInventory.service.imp.TransactionService;
import com.dahabMasr.GoldInventory.utility.ApiResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
            detail.setQuantity(inv.getQuantity());
            detail.setWeight(inv.getQuantity()*inv.getWeight());

            TransactionDetail transdetail =  TransactionDetailIService.save(detail);

            if (transdetail.getId() != null) {
                 inventory.setReserved(inv.getReversed() + inv.getQuantity());
                 inventory.setType(inv.getType());
                 inventory.setWeight(inv.getWeight());
                 inventory.setQuantity(inv.getStock_quantity());
                 inventory.setName(inv.getName());
                 inventoryService.update(inventory);
            }

        }


        result.transaction_id = transaction.getId();
        result.status = transaction.getStatus().toString();
        return  result;
    }


    @PostMapping("update")
    public ResponseEntity<ApiResponse<Transaction>> update(@RequestBody TransactionUpdateReq dto){
        Transaction trans = TransactionService.find(dto.getId());
        if(trans.getId()!=null){
            trans.setStatus(Transaction.Status.valueOf(dto.getStatus()));
            TransactionService.save(trans);
            ApiResponse<Transaction> response = new ApiResponse<Transaction>(
                    "inventory addess sucessfuly",
                    trans
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        ApiResponse<Transaction> response = new ApiResponse<>(
                "Transaction not found with id: " + dto.getId(),
                null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }


   @Data
   public  static class Result{
       public List<InventoryWithCountRes> inventories;
       public double amount;
       public double remaining;
       public Long transaction_id;
       public String status;
   }

   // this function responsible for algorithm that calculate  quantity of pieces that user will take
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
                       inv.getReserved(),
                       inv.getType(),
                       count,
                       inv.getQuantity()
               ));
           }
       }
       result.remaining = remaining;
       result.setInventories(resultList);
       result.amount = amount - remaining;
       return result;
   }

}
