package br.com.ekan.teste.controller;

import br.com.ekan.teste.domain.DocumentoDTO;
import br.com.ekan.teste.service.DocumentoService;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/documento")
@SecurityRequirement(name = "bearerAuth")
public class DocumentoController {

	@Autowired
	private DocumentoService documentoService;
	
	@GetMapping("{beneficarioId}")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Sucesso na execução.",
				content = { @Content(mediaType = "application/json",
						array = @ArraySchema(schema = @Schema(implementation = DocumentoDTO.class))) }),
			@ApiResponse(responseCode = "403", description = "Não Autorizado", content = @Content),
			@ApiResponse(responseCode = "500", description = "Foi gerada uma exceção", content = @Content), })
	public ResponseEntity<List<DocumentoDTO>> buscarDocumentos(@PathVariable final Long beneficarioId) {
		return ResponseEntity.ok(documentoService.buscarPorBeneficiarioId(beneficarioId));
	}
}
