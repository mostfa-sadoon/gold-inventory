package com.dahabMasr.GoldInventory.service;

import com.dahabMasr.GoldInventory.model.Dto.InventoryReq;
import com.dahabMasr.GoldInventory.model.Entity.Inventory;

import java.util.List;
import java.util.Optional;

public interface InventoryServiceInterface {
    public  Inventory save(Inventory entity);
    public  List<Inventory> getInventoriesByTypeOrderDesc(String type);
    public  Inventory update(Inventory entity);
    public  Optional<Inventory> find(Long id);
}
