package com.dahabMasr.GoldInventory.exception;

public class InventoryNotFoundException extends RuntimeException{
     public InventoryNotFoundException(String type){
         super("No inventories found for type: " + type);
     }
}
