package br.com.ekan.teste.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name ="documento")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class Documento{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "tipo_documento")
    private String tipoDocumento;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "data_inclusao")
    private LocalDateTime dataInclusao;
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "beneficiario_id")
    private Beneficiario beneficiario;
}
