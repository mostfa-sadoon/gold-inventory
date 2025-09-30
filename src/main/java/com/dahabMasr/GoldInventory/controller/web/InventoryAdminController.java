package com.dahabMasr.GoldInventory.controller.web;


import com.dahabMasr.GoldInventory.model.Entity.Inventory;
import com.dahabMasr.GoldInventory.service.imp.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("admin")
public class InventoryAdminController {

    @Autowired
    InventoryService InventoryService;

    @GetMapping("inventories")
    public  String inventorirs(@RequestParam(defaultValue = "0") int page , @RequestParam(defaultValue = "20") int size , Model model){
        Page<Inventory> inventorypage = InventoryService.findPaginated(page, size);
        model.addAttribute("inventories", inventorypage.getContent()); // actual data
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", inventorypage.getTotalPages());
        return "admin/inventory";
    }
}
