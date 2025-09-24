package com.dahabMasr.GoldInventory.model.Dto;


import com.dahabMasr.GoldInventory.model.Entity.Inventory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryWithCountRes {

    private Long id;
    private String name;
    private Double amount;
    private Integer reserved;
    private Float weight;
    private Inventory.Type type;
    private Integer count;
}
