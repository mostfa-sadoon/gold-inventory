package com.dahabMasr.GoldInventory.config;


import com.dahabMasr.GoldInventory.security.*;
import com.dahabMasr.GoldInventory.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.CachingUserDetailsService;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

  @Autowired
    CustomCustomerDetailsService customerDetailsService;
  @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;
  @Autowired
    JwtAuthEntryPoint jwtAuthEntryPoint;
  @Autowired
   AdminDetailsService adminDetailsService;

  @Autowired
  CustomUserDetailsService customUserDetailsService;

  @Bean
  public PasswordEncoder passwordEncoder(){
      return new BCryptPasswordEncoder();
  }


    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        System.out.println("Authenticating...");
        return config.getAuthenticationManager();
    }


    @Bean
    @Order(1)
    public SecurityFilterChain apiSecurity(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/api/**") // this isolates the API chain
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login").permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthEntryPoint))
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    @Order(2)
    public  SecurityFilterChain adminSecurity(HttpSecurity http) throws  Exception{
       http.
               securityMatcher("/admin/**")
               .authorizeHttpRequests(auth -> auth
                       .requestMatchers("/admin/login", "/admin/css/**", "/admin/js/**").permitAll()
                       .requestMatchers("/admin/**").hasRole("ADMIN")
                       .anyRequest()
               )
               .formLogin(form -> form
                       .loginPage("/admin/login")
                       .defaultSuccessUrl("/admin/transactions", true)
                       .failureUrl("/admin/login?error=true")
                       .permitAll()
               )
               .logout(logout -> logout
                       .logoutUrl("/admin/logout")
                       .logoutSuccessUrl("/admin/login?logout")
                       .permitAll()
               );
        return http.build();
    }
}
