package com.dahabMasr.GoldInventory.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryContoller {

   @GetMapping("hallo-inventory")
   public  String home(){
         return  "hallo in inventory";
   }

}
