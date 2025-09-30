package com.dahabMasr.GoldInventory.model.Dto.Res;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDetailRes {
    private  Long id;
    private  Integer quantity;
    private  Double weight;
    private  InventoryRes inventoryRes;
}
