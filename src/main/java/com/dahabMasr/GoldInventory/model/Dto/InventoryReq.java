package com.dahabMasr.GoldInventory.model.Dto;

import com.dahabMasr.GoldInventory.model.Entity.Inventory;
import lombok.Data;

@Data
public class InventoryReq {

    private  Long id;
    private  String name;
    private  Double amount;
    private  Integer reserved;
    private  Float weight;
    private Inventory.Type type;
}
