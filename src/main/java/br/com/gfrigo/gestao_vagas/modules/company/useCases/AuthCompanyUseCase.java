package br.com.gfrigo.gestao_vagas.modules.company.useCases;

import java.time.Duration;
import java.time.Instant;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.gfrigo.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.gfrigo.gestao_vagas.modules.company.repositories.CompanyRepository;

@Service
public class AuthCompanyUseCase {
  
  // Retirar valor de application.properties -> secret key
  @Value("${security.token.secret}")
  private String secretKey;

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public String execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException{
    var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername())
      .orElseThrow(() -> {
        throw new UsernameNotFoundException("Usuário/Senha incorretos");
      });

      // Verificar se as senhas são iguais
      var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

      // Senhas não são iguais -> Erro de autenticação
      if(!passwordMatches){
        throw new AuthenticationException();
      }
      // Se for igual -> Gerar Token JWT
      Algorithm algorithm = Algorithm.HMAC256(secretKey);
      var token = JWT.create().withIssuer("javagas")
        .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
        .withSubject(company.getId().toString())
        .sign(algorithm);

      return token;
  }
}
