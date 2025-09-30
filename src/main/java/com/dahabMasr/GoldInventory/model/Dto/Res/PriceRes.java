package com.dahabMasr.GoldInventory.model.Dto.Res;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceRes {
    private float goldBaying  = 5000;
    private float goldSelling = 4950;

    private float selverBaying   = 65;
    private float seleverSalling = 60;
}
