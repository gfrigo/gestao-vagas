package br.com.gfrigo.gestao_vagas.modules.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // Construtor com os atributos
public class AuthCompanyDTO { 
  // Transferir dados da Controller para UseCase com info necessárias
  private String password;
  private String username;
}
