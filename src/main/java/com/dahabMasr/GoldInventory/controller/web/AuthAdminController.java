package com.dahabMasr.GoldInventory.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AuthAdminController {

  @GetMapping("/login")
  public  String loginPage(){
      return  "admin/login";
  }

}
