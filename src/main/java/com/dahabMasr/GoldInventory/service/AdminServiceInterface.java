package com.dahabMasr.GoldInventory.service;

import com.dahabMasr.GoldInventory.model.Entity.Admin;

import java.util.Optional;

public interface AdminServiceInterface {
    public Optional<Admin> findByUsername(String username);
}
