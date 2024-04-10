package br.com.ekan.teste.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BeneficiarioDTO {
    private Long id;
    private String nome;
    private String telefone;
    private LocalDate dataNascimento;
    private List<DocumentoDTO> documentos;
}
