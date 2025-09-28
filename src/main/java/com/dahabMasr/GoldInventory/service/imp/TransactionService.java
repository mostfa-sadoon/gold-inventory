package com.dahabMasr.GoldInventory.service.imp;

import com.dahabMasr.GoldInventory.controller.api.TransactionController;
import com.dahabMasr.GoldInventory.model.Dto.InventoryWithCountRes;
import com.dahabMasr.GoldInventory.model.Dto.PriceRes;
import com.dahabMasr.GoldInventory.model.Entity.Inventory;
import com.dahabMasr.GoldInventory.model.Entity.Transaction;
import com.dahabMasr.GoldInventory.repository.TransactionRepository;
import com.dahabMasr.GoldInventory.service.TransactionServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService implements TransactionServiceInterface {

    @Autowired
    TransactionRepository TransactionRepository;
    @Autowired
    InventoryService InventoryService;

    public Transaction save(Transaction entity){
        return  TransactionRepository.save(entity);
    }

    public Optional<Transaction> find(Long id){
         return TransactionRepository.findById(id);
    }

    // this function responsible for algorithm that calculate  quantity of pieces that user will take
    @Override
    public TransactionController.Result calculate(String type, Double amount) {
        TransactionController.Result result = new TransactionController.Result();
        PriceRes price  = new PriceRes();
        List<Inventory> inventories = InventoryService.getInventoriesByTypeOrderDesc(type);
        double remaining = amount;
        List<InventoryWithCountRes> resultList = new ArrayList<>();

        for (Inventory inv : inventories) {
            float priceValue = type.equals("GOLD") ? price.getGoldBaying() : price.getSelverBaying();
            double piecePrice = inv.getWeight() * priceValue;

            int count = 0;
            while (remaining >= piecePrice) {
                count++;
                remaining -= piecePrice;
            }
            if (count > 0) {
                resultList.add(new InventoryWithCountRes(
                        inv.getId(),
                        inv.getName(),
                        inv.getWeight(),
                        inv.getReserved(),
                        inv.getType(),
                        count,
                        inv.getQuantity()
                ));
            }
        }

        result.remaining = remaining;
        result.setInventories(resultList);
        result.amount = amount - remaining;
        return result;
    }
}
