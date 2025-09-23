package com.dahabMasr.GoldInventory.model.Mapper;

import com.dahabMasr.GoldInventory.model.Dto.InventoryReq;
import com.dahabMasr.GoldInventory.model.Entity.Inventory;

public interface InventoryMapperInterface {
     Inventory toEntity(InventoryReq dto);
}
