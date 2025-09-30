package com.dahabMasr.GoldInventory.model.Dto.Res;


import com.dahabMasr.GoldInventory.model.Entity.Inventory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class InventoryRes {

   private Long   Id;
   private String Name;
   private Inventory.Type Type;
   private Float   weight;
   private Integer quantity;
}
