package com.dahabMasr.GoldInventory.database.seeder;

import com.dahabMasr.GoldInventory.model.Entity.Admin;
import com.dahabMasr.GoldInventory.model.Entity.Customer;
import com.dahabMasr.GoldInventory.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminSeeder implements CommandLineRunner {

  @Autowired
  AdminRepository adminRepository;

  @Autowired
  PasswordEncoder passwordEncoder;
    public  void run(String... args){
        if(adminRepository.count() == 0){
            Admin admin = new Admin();
            admin.setUsername("mostfa_sadoon");
            admin.setRole("ADMIN");
            admin.setPassword(passwordEncoder.encode("123456"));
            adminRepository.save(admin);
            System.out.println("✅  Admin seeded successfully!");
        }
    }

}
