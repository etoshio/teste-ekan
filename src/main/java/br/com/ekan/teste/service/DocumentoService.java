package br.com.ekan.teste.service;

import br.com.ekan.teste.domain.DocumentoDTO;
import br.com.ekan.teste.entity.Documento;
import br.com.ekan.teste.mapper.DocumentoMapper;
import br.com.ekan.teste.repository.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentoService {

	@Autowired
	private DocumentoRepository documentoRepository;

	public List<DocumentoDTO> buscarPorBeneficiarioId(Long id) {
		List<Documento> documentos = documentoRepository.findByBeneficiarioId(id);
		return DocumentoMapper.INSTANCE.convert(documentos);
	}
}
