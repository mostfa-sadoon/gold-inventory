package com.dahabMasr.GoldInventory.model.Dto;


import com.dahabMasr.GoldInventory.model.Entity.Customer;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.NotFound;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionReq {
     @NotNull()
     @Min(value = 1, message ="you should but positive value")
     private  Double amount;
     @NotNull()
     private  String type;
     @NotNull()
     private  Long customer;
}
