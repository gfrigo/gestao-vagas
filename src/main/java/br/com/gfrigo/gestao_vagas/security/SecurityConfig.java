package br.com.gfrigo.gestao_vagas.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration // Classe usada no start do spring para configuração
public class SecurityConfig {
  
  @Autowired
  private SecurityFilter securityFilter;

  @Bean // Reescreve objetos gerenciados pelo Spring
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
    // csrf -> Requisição HTTP na tentativa de se passar por um usuário legítimo
    http.csrf(csrf -> csrf.disable()) // Evita autenticações desnecessárias
      .authorizeHttpRequests(auth -> {
        auth.requestMatchers("/candidate/").permitAll()
          .requestMatchers("/company/").permitAll()
          .requestMatchers("/auth/company").permitAll();
        auth.anyRequest().authenticated();
      })
      // Adiciona o filtro para receber a requisição e tratá-la antes da controller
      .addFilterBefore(securityFilter, BasicAuthenticationFilter.class);
    
      return http.build();
  }

  // Toda vez que for usado o AutoWired de PasswordEncoder será utilziado o BCryptPasswordEncoder para criptografar as senhas
  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }
}
