package br.com.ekan.teste.repository;

import br.com.ekan.teste.entity.Beneficiario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficiarioRepository extends JpaRepository<Beneficiario, Long> {

}
