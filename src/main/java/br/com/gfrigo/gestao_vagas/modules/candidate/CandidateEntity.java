package br.com.gfrigo.gestao_vagas.modules.candidate;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity(name = "candidate") // Tabela SQL -> Atribui automaticamente os atributos como colunas
@Data // Lombok -> Criação de Getters e Setters
public class CandidateEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String name;

  @NotBlank()
  @Pattern(regexp = "\\S+", message = "O nome de usuário não deve conter espaços.")
  private String username;

  @Email(message = "Digite um e-mail válido.")
  private String email;

  @Length(min = 10, max = 100, message = "A senha deve conter entre 10 e 100 caracteres.")
  private String password;

  private String description;
  private String curriculum;

  // Data de criação do candidato
  @CreationTimestamp
  private LocalDateTime createdAt;

}
