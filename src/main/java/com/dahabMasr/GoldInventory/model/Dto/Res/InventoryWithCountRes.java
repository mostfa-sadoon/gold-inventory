package com.dahabMasr.GoldInventory.model.Dto.Res;


import com.dahabMasr.GoldInventory.model.Entity.Inventory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryWithCountRes {

    private Long id;
    private String name;
    private Float weight;
    @JsonIgnore
    public  Integer reversed;
    private Inventory.Type type;
    // this refer to quantity that user will buy it
    private Integer quantity;
    @JsonIgnore
    private Integer stock_quantity;
}
