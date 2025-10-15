package com.dahabMasr.GoldInventory.controller.api;

import com.dahabMasr.GoldInventory.model.Dto.req.LoginRequest;
import com.dahabMasr.GoldInventory.utility.ApiResponse;
import com.dahabMasr.GoldInventory.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

  @PostMapping("/login")
    public ResponseEntity<ApiResponse<Token>> login(@RequestBody LoginRequest dto){
      Authentication auth = authenticationManager.authenticate(
         new UsernamePasswordAuthenticationToken(dto.getEmail(),dto.getPassword())
      );
      String token = jwtUtil.generateToken(dto.getEmail());
      Token jwtTokwn = new Token();
      jwtTokwn.token = token;
      ApiResponse<Token>  response = new ApiResponse<Token>(
              "login success",
              jwtTokwn
      );
      return ResponseEntity.ok(response);
  }
}
class Token{
    public String token;
}
