package com.dahabMasr.GoldInventory.service.imp;

import com.dahabMasr.GoldInventory.model.Entity.Customer;
import com.dahabMasr.GoldInventory.repository.CustomerRepository;
import com.dahabMasr.GoldInventory.service.CustomerServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements CustomerServiceInterface {

    @Autowired
    CustomerRepository CustomerRepository;

    public Customer find(Long Id){
        return CustomerRepository.findById(Id).get();
    }
}
