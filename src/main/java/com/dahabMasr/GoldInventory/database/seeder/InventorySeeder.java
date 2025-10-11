package com.dahabMasr.GoldInventory.database.seeder;

import com.dahabMasr.GoldInventory.model.Entity.Inventory;
import com.dahabMasr.GoldInventory.model.enums.inventory.Type;
import com.dahabMasr.GoldInventory.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InventorySeeder implements CommandLineRunner {

  @Autowired
  InventoryRepository inventoryRepository;

    public  void run(String... args){
        if(inventoryRepository.count() == 0 ){
            List<Inventory> inventories = List.of(
                    createInventory("first gold", Inventory.Type.GOLD, 100, 5f),
                    createInventory("second gold", Inventory.Type.GOLD, 100, 4f),
                    createInventory("third gold", Inventory.Type.GOLD, 100, 3f),
                    createInventory("fourth gold", Inventory.Type.GOLD, 100, 2f),
                    createInventory("fifth gold", Inventory.Type.GOLD, 100, 1f),

                    createInventory("first silver", Inventory.Type.SILVER, 100, 5f),
                    createInventory("second silver", Inventory.Type.SILVER, 100, 4f),
                    createInventory("third silver", Inventory.Type.SILVER, 100, 3f),
                    createInventory("fourth silver", Inventory.Type.SILVER, 100, 2f),
                    createInventory("fifth silver", Inventory.Type.SILVER, 100, 1f)
            );
            inventoryRepository.saveAll(inventories);
            System.out.println("✅ Inventory seeded successfully!");
        }
    }

    private Inventory createInventory(String name, Inventory.Type type, int quantity, float weight) {
        Inventory inventory = new Inventory();
        inventory.setName(name);
        inventory.setType(type);
        inventory.setQuantity(quantity);
        inventory.setWeight(weight);
        return inventory;
    }

}
