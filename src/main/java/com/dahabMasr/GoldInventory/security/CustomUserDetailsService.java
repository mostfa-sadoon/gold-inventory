package com.dahabMasr.GoldInventory.security;

import com.dahabMasr.GoldInventory.model.Entity.Admin;
import com.dahabMasr.GoldInventory.model.Entity.Customer;
import com.dahabMasr.GoldInventory.repository.AdminRepository;
import com.dahabMasr.GoldInventory.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    AdminRepository adminRepository;

    @Autowired
    CustomerRepository customerRepository;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Admin> admin = adminRepository.findByUsername(email);

        if (admin.isPresent()) {
            return org.springframework.security.core.userdetails.User
                    .withUsername(admin.get().getUsername())
                    .password(admin.get().getPassword())
                    .roles("ADMIN") // 👈 Important
                    .build();
        }
        Customer customer = this.customerRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (customer!=null){
            return org.springframework.security.core.userdetails.User
                    .withUsername(customer.getEmail())
                    .password(customer.getPassword())
                    .build();
        }
        throw new UsernameNotFoundException("User not found ");
    }
}
