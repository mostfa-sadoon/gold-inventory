package com.dahabMasr.GoldInventory.model.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionReq {
     private  Double amount;
     private  String type;
}
