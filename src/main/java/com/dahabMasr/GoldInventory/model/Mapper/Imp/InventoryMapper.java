package com.dahabMasr.GoldInventory.model.Mapper.Imp;

import com.dahabMasr.GoldInventory.model.Dto.InventoryReq;
import com.dahabMasr.GoldInventory.model.Entity.Inventory;
import com.dahabMasr.GoldInventory.model.Mapper.InventoryMapperInterface;
import org.springframework.stereotype.Service;

@Service
public class InventoryMapper implements InventoryMapperInterface {

    @Override
   public Inventory toEntity(InventoryReq dto){
        return  Inventory.builder()
                .type(dto.getType())
                .name(dto.getName())
                .quantity(dto.getQuantity())
                .weight(dto.getWeight())
                .build();
   }
}
