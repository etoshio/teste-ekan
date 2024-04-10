package br.com.ekan.teste.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BeneficiarioAtualizarDTO {
    private String nome;
    private String telefone;
    private LocalDate dataNascimento;
}
