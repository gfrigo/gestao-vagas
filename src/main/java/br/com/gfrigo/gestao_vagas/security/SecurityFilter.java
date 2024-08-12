package br.com.gfrigo.gestao_vagas.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.gfrigo.gestao_vagas.providers.JWTProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Camada para verificação do token JWT e autenticação antes da Controller
@Component
public class SecurityFilter extends OncePerRequestFilter {

  @Autowired
  private JWTProvider jwtProvider;

  @Override
  protected void doFilterInternal(HttpServletRequest request, 
  HttpServletResponse response, 
  FilterChain filterChain) // Recebe request, response e filterChain (configuração)
  throws ServletException, IOException {

    // O Spring mantém, durante todo o fluxo de requisição, o controle da  autenticação pelo contexto
    SecurityContextHolder.getContext().setAuthentication(null); // Evita resquícios de outras autenticações anteriores
    // Obtem o Bearer com o Token JWT que fica dentro do headder "Authorization"
    String header = request.getHeader("Authorization");

    if(header != null){
      var subjectToken = this.jwtProvider.validadeToken(header);
      if(subjectToken.isEmpty()){
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return;
      }
      // Em vez de passar na rota o company_id passar o subjectToken em Auth do APIDog
      request.setAttribute("company_id", subjectToken);
      UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(subjectToken, null, Collections.emptyList());
      SecurityContextHolder.getContext().setAuthentication(auth);
    }
    // Retorno da excessão como request e response e não mais uma excessão padrão
    filterChain.doFilter(request, response);
  }
}
