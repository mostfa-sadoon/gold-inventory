package com.dahabMasr.GoldInventory.controller.api;


import com.dahabMasr.GoldInventory.model.Dto.InventoryReq;
import com.dahabMasr.GoldInventory.model.Entity.Inventory;
import com.dahabMasr.GoldInventory.model.Mapper.Imp.InventoryMapper;
import com.dahabMasr.GoldInventory.service.imp.InventoryService;
import com.dahabMasr.GoldInventory.utility.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponse<Inventory>> save(@RequestBody InventoryReq dto){
       Inventory invetory =   inventoryService.save(InventoryMapper.toEntity(dto));
       ApiResponse<Inventory> response = new ApiResponse<Inventory>(
               "inventory addess sucessfuly",
               invetory
       );
       return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
