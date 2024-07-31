package br.com.gfrigo.gestao_vagas.modules.company.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gfrigo.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.gfrigo.gestao_vagas.modules.company.useCases.CreateCompanyUseCases;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/company")
public class CompanyController {
  
  @Autowired
  private CreateCompanyUseCases createCompanyUseCases;

  @PostMapping("/")
  public ResponseEntity<Object> create(@Valid @RequestBody CompanyEntity companyEntity){
    try{
      var result = this.createCompanyUseCases.execute(companyEntity);
      return ResponseEntity.ok().body(result);
    }catch(Exception e){
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
