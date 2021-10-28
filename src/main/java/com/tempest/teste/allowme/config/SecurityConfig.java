package com.tempest.teste.allowme.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
 
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
     
   @Value("${app.usuario:}")
   private String usuario;
     
   @Value("${app.senha:}")
   private String senha;
     
   @Override
   protected void configure(HttpSecurity http) throws Exception {
      http
      .authorizeRequests()
      .antMatchers("/allowMe/**")
      .authenticated()
      .and()
      .httpBasic();
      http.csrf().disable();
      http.headers().frameOptions().sameOrigin();
   }
 
   @Autowired
   public void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth
      .inMemoryAuthentication()
      .withUser(usuario)
      .password("{noop}"+senha)
      .roles("USER");
   }
     
}