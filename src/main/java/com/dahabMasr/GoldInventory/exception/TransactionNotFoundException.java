package com.dahabMasr.GoldInventory.exception;

public class TransactionNotFoundException extends RuntimeException{
    public TransactionNotFoundException(Long id){
        super("id" + id);
    }
}
