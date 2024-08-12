package br.com.gfrigo.gestao_vagas.modules.company.dto;

import lombok.Data;

@Data
// Evita que seja passado um company_id para o JobController
public class CreateJobDTO {
  private String description;
  private String benefits;
  private String level;
}
