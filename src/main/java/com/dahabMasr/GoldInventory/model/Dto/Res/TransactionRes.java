package com.dahabMasr.GoldInventory.model.Dto.Res;


import com.dahabMasr.GoldInventory.model.Entity.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRes {
  private  Long id;
  private  Double amount;
  private  Transaction.Status status;
  private  TransactionDetailRes TransactionDetailRes;
}
