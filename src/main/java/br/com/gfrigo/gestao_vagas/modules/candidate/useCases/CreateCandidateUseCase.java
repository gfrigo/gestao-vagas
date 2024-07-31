package br.com.gfrigo.gestao_vagas.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gfrigo.gestao_vagas.exceptions.UserFoundException;
import br.com.gfrigo.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.gfrigo.gestao_vagas.modules.candidate.CandidateRepository;

@Service
public class CreateCandidateUseCase {
  
  @Autowired // Autogerencia a instancia da classe CandidateRepository
  private CandidateRepository candidateRepository;

  public CandidateEntity execute(CandidateEntity candidateEntity){
    
    this.candidateRepository
    .findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
    .ifPresent((user) -> {
      throw new UserFoundException();
    });
    
    // Retorna CandidateEntity salvo no BD com todos os dados definidos em um JSON
    return this.candidateRepository.save(candidateEntity);
  }

}
