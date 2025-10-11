package com.dahabMasr.GoldInventory.database.seeder;

import com.dahabMasr.GoldInventory.model.Entity.Customer;
import com.dahabMasr.GoldInventory.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CustomerSeeder implements CommandLineRunner {

    @Autowired
    CustomerRepository CustomerRepository;
    public  void run(String... args){
        if(CustomerRepository.count() == 0){
            Customer customer = new Customer();
            customer.setEmail("mostfa_sadoon@gmail.com");
            customer.setName("mostfa");
            CustomerRepository.save(customer);
            System.out.println("✅  customer seeded successfully!");
        }
    }

}
