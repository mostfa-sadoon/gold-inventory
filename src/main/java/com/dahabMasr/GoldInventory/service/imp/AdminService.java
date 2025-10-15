package com.dahabMasr.GoldInventory.service.imp;

import com.dahabMasr.GoldInventory.model.Entity.Admin;
import com.dahabMasr.GoldInventory.repository.AdminRepository;
import com.dahabMasr.GoldInventory.service.AdminServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService implements AdminServiceInterface {
    @Autowired
    AdminRepository adminRepository;

    public  Optional<Admin> findByUsername(String username){
        return adminRepository.findByUsername(username);
    }
}
