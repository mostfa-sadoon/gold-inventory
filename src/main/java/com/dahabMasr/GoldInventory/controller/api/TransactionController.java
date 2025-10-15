package com.dahabMasr.GoldInventory.controller.api;


import com.dahabMasr.GoldInventory.model.Dto.Res.InventoryWithCountRes;
import com.dahabMasr.GoldInventory.model.Dto.Res.TransactionRes;
import com.dahabMasr.GoldInventory.model.Dto.TransactionReq;
import com.dahabMasr.GoldInventory.model.Dto.TransactionUpdateReq;
import com.dahabMasr.GoldInventory.model.Entity.Transaction;
import com.dahabMasr.GoldInventory.model.Mapper.Imp.TransactionMapper;
import com.dahabMasr.GoldInventory.service.imp.InventoryService;
import com.dahabMasr.GoldInventory.service.imp.TransactionDetailIService;
import com.dahabMasr.GoldInventory.service.imp.TransactionService;
import com.dahabMasr.GoldInventory.utility.ApiResponse;
import groovy.util.logging.Slf4j;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@lombok.extern.slf4j.Slf4j
@RestController
@Slf4j
@RequestMapping("/api")
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
    public  ResponseEntity<ApiResponse<Result>> create(@Valid @RequestBody TransactionReq dto){
        log.info("new request comming with this mony : {}  type : {}",dto.getAmount(),dto.getType());
        Result result = TransactionService.create(dto);
        ApiResponse<Result> response = new ApiResponse<Result>(
                "Transaction updated successfuly",
                result
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PostMapping("update")
    public ResponseEntity<ApiResponse<Transaction>> update(@RequestBody TransactionUpdateReq dto){
        Transaction trans = TransactionService.updateStatus(dto.getId(), dto.getStatus());
        ApiResponse<Transaction> response = new ApiResponse<Transaction>(
                "Transaction updated successfuly",
                trans
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("transaction-detailes")
    public  ResponseEntity<ApiResponse<TransactionRes>> getDetailes(@RequestParam() Long id){
        System.out.println(id);
        Optional<Transaction> trans = TransactionService.find(id);
        TransactionRes transaction = TransactionMapper.toResponse(trans.get());
        ApiResponse<TransactionRes> response = new ApiResponse<TransactionRes>(
                "get Transaction success",
                transaction
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

   @Data
   public  static class Result{
       public List<InventoryWithCountRes> inventories;
       public double amount;
       public double remaining;
       public Long transaction_id;
       public String status;
       public float price;
   }

}
