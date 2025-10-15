package com.dahabMasr.GoldInventory.security;

import com.dahabMasr.GoldInventory.model.Entity.Admin;
import com.dahabMasr.GoldInventory.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminDetailsService implements UserDetailsService {

  @Autowired
  AdminRepository adminRepository;


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Admin admin = adminRepository.findByUsername(username)
              .orElseThrow(() -> new UsernameNotFoundException("Admin not found"));

      return new User(
              admin.getUsername(),
              admin.getPassword(),
              List.of(new SimpleGrantedAuthority("ROLE_" + admin.getRole()))
      );
  }


}
