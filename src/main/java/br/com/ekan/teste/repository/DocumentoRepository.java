package br.com.ekan.teste.repository;

import br.com.ekan.teste.entity.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {
    List<Documento> findByBeneficiarioId(Long id);
}
