package br.com.ekan.teste.service;

import br.com.ekan.teste.domain.BeneficiarioAtualizarDTO;
import br.com.ekan.teste.domain.BeneficiarioDTO;
import br.com.ekan.teste.domain.DocumentoDTO;
import br.com.ekan.teste.entity.Beneficiario;
import br.com.ekan.teste.entity.Documento;
import br.com.ekan.teste.exception.ValidacaoException;
import br.com.ekan.teste.mapper.BeneficiarioMapper;
import br.com.ekan.teste.mapper.DocumentoMapper;
import br.com.ekan.teste.repository.BeneficiarioRepository;
import br.com.ekan.teste.repository.DocumentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BeneficiarioService {

	@Autowired
	private BeneficiarioRepository beneficiarioRepository;
	@Autowired
	private DocumentoRepository documentoRepository;

	public List<BeneficiarioDTO> buscarTodos() {
		return BeneficiarioMapper.INSTANCE.convert(beneficiarioRepository.findAll());
	}

	public void deletarBeneficiario(Long id) {
		beneficiarioRepository.deleteById(id);
	}

	public BeneficiarioDTO buscarPorId(Long id) {
		Beneficiario beneficiario = beneficiarioRepository.findById(id)
				.orElseThrow(() -> new ValidacaoException("Beneficiário não encontrado"));
		return BeneficiarioMapper.INSTANCE.convert(beneficiario);
	}

	public BeneficiarioDTO criar(BeneficiarioDTO dto) {
		Beneficiario beneficiario = BeneficiarioMapper.INSTANCE.covert(dto);
		beneficiario.setDataInclusao(LocalDateTime.now());
		beneficiario.setDocumentos(null);
		beneficiario = beneficiarioRepository.save(beneficiario);

		for(DocumentoDTO documentoDTO : dto.getDocumentos()) {
			Documento documento = DocumentoMapper.INSTANCE.convert(documentoDTO);
			documento.setDataInclusao(LocalDateTime.now());
			documento.setBeneficiario(Beneficiario.builder().id(beneficiario.getId()).build());
			documentoRepository.save(documento);
		}
		BeneficiarioDTO beneficiarioDTO = BeneficiarioMapper.INSTANCE.convert(beneficiario);
		beneficiarioDTO.setDocumentos(DocumentoMapper.INSTANCE.convert(documentoRepository.findByBeneficiarioId(beneficiario.getId())));
		return beneficiarioDTO;
	}

	@Transactional
	public BeneficiarioDTO alterar(Long id, BeneficiarioAtualizarDTO dto) {
		Beneficiario beneficiario = beneficiarioRepository.findById(id)
				.orElseThrow(() -> new ValidacaoException("Beneficiário não encontrado"));

		beneficiario.setDataAtualizacao(LocalDateTime.now());
		beneficiario.setNome(dto.getNome());
		beneficiario.setTelefone(dto.getTelefone());
		beneficiario.setDataNascimento(dto.getDataNascimento());

		beneficiario = beneficiarioRepository.save(beneficiario);
		BeneficiarioDTO beneficiarioDTO = BeneficiarioMapper.INSTANCE.convert(beneficiario);
		beneficiarioDTO.setDocumentos(DocumentoMapper.INSTANCE.convert(documentoRepository.findByBeneficiarioId(id)));
		return beneficiarioDTO;
	}
}
