package com.dahabMasr.GoldInventory.model.Dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionUpdateReq {
     @NotNull
     private Long id;
     @NotNull
     private String status;
}
