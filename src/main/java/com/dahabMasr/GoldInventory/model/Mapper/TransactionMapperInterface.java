package com.dahabMasr.GoldInventory.model.Mapper;

import com.dahabMasr.GoldInventory.model.Dto.Res.TransactionRes;
import com.dahabMasr.GoldInventory.model.Dto.TransactionReq;
import com.dahabMasr.GoldInventory.model.Entity.Transaction;

public interface TransactionMapperInterface {

  public Transaction toEntity(TransactionReq dto);
  public TransactionRes toResponse(Transaction entity);
}
