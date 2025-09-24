package com.dahabMasr.GoldInventory.service.imp;

import com.dahabMasr.GoldInventory.model.Dto.InventoryReq;
import com.dahabMasr.GoldInventory.model.Entity.Inventory;
import com.dahabMasr.GoldInventory.repository.InventoryRepository;
import com.dahabMasr.GoldInventory.service.InventoryServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService implements InventoryServiceInterface {

    @Autowired
    InventoryRepository InventoryRepository;

   public Inventory save(Inventory entity) {
      return   InventoryRepository.save(entity);
   }

    public List<Inventory> getInventoriesByTypeOrderDesc(String type) {
        return InventoryRepository.findAll(
                (root, query, cb) -> cb.equal(root.get("type"), type),
                Sort.by(Sort.Direction.DESC, "weight")
        );
    }
}
