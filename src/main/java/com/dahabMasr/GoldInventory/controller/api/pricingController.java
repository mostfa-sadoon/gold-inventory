package com.dahabMasr.GoldInventory.controller.api;


import com.dahabMasr.GoldInventory.model.Dto.Res.PriceRes;
import com.dahabMasr.GoldInventory.utility.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class pricingController {
    @GetMapping("get-pricing")
    public ResponseEntity<ApiResponse<PriceRes>> getPricing(){
        PriceRes price = new PriceRes();
        ApiResponse<PriceRes> response = new ApiResponse<PriceRes>("get pricing successfuly",price);
        return ResponseEntity.ok(response);
    }
}