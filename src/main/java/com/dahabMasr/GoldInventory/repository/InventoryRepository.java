package com.dahabMasr.GoldInventory.repository;

import com.dahabMasr.GoldInventory.model.Entity.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InventoryRepository extends JpaRepository<Inventory , Long>, JpaSpecificationExecutor<Inventory> {
   public Page<Inventory> findAllByOrderByIdDesc(Pageable page);
}
