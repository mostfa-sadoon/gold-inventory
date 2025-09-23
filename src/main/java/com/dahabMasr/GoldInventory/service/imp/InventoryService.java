package com.dahabMasr.GoldInventory.service.imp;

import com.dahabMasr.GoldInventory.model.Dto.InventoryReq;
import com.dahabMasr.GoldInventory.model.Entity.Inventory;
import com.dahabMasr.GoldInventory.repository.InventoryRepository;
import com.dahabMasr.GoldInventory.service.InventoryServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService implements InventoryServiceInterface {

    @Autowired
    InventoryRepository InventoryRepository;

   public void save(Inventory entity) {
       InventoryRepository.save(entity);
   }
}
