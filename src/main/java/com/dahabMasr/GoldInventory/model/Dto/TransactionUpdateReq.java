package com.dahabMasr.GoldInventory.model.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionUpdateReq {
     private Long id;
     private String status;
}
