package br.com.gfrigo.gestao_vagas.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // Construtor com argumentos
public class ErrorMessageDTO {

  private String message;
  private String field;
  
}
