package com.dahabMasr.GoldInventory.model.Dto.Res;


import com.dahabMasr.GoldInventory.model.Entity.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionRes {
  private  Long id;
  private  Double amount;
  private  Transaction.Status status;
  private  List<TransactionDetailRes> TransactionDetailRes;
}
