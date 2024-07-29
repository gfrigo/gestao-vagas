package br.com.gfrigo.gestao_vagas.modules.candidate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gfrigo.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.gfrigo.gestao_vagas.modules.candidate.CandidateRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {
  
  @Autowired // Autogerencia a instancia da classe CandidateRepository
  private CandidateRepository candidateRepository;

  // Request Body -> Recebe valores e transfere ao candidateEntity
  // @Valid -> Realiza as verificações da Entidade (email, senha e username)
  @PostMapping("/")
  public CandidateEntity create(@Valid @RequestBody CandidateEntity candidateEntity){
    return this.candidateRepository.save(candidateEntity);
  }

}
