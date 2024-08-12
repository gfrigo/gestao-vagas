package br.com.gfrigo.gestao_vagas.modules.company.controlers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gfrigo.gestao_vagas.modules.company.dto.CreateJobDTO;
import br.com.gfrigo.gestao_vagas.modules.company.entities.JobEntity;
import br.com.gfrigo.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/job")
public class JobController {
  
  @Autowired
  private CreateJobUseCase createJobUseCase;

  @PostMapping("/")
  public JobEntity create(@Valid @RequestBody CreateJobDTO createJobDTO, HttpServletRequest request){
    // O subjectToken é um objeto, então, é necessário converter para UUID para jobEntity
    var companyId = request.getAttribute("company_id");

    // Evita que o usuário envie parâmetros adicionais na requisição, serão ignorados
    // Evita que seja passado um company_id na requisição, também será ignorado
    var jobEntity = JobEntity.builder()
      .benefits(createJobDTO.getBenefits())
      .companyId(UUID.fromString(companyId.toString()))
      .description(createJobDTO.getDescription())
      .level(createJobDTO.getLevel())
      .build();

    return this.createJobUseCase.execute(jobEntity);
  }
}
