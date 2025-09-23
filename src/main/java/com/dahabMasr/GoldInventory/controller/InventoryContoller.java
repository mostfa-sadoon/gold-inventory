package com.dahabMasr.GoldInventory.controller;


import com.dahabMasr.GoldInventory.model.Dto.InventoryReq;
import com.dahabMasr.GoldInventory.model.Mapper.Imp.InventoryMapper;
import com.dahabMasr.GoldInventory.service.imp.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryContoller {

    @Autowired
    InventoryService inventoryService;
    @Autowired
    InventoryMapper InventoryMapper;

    @PostMapping("save")
    public  void save(@RequestBody InventoryReq dto){
        inventoryService.save(InventoryMapper.toEntity(dto));
    }
}
