package br.com.ekan.teste.controller;

import br.com.ekan.teste.domain.BeneficiarioAtualizarDTO;
import br.com.ekan.teste.domain.BeneficiarioDTO;
import br.com.ekan.teste.service.BeneficiarioService;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beneficiario")
@SecurityRequirement(name = "bearerAuth")
public class BeneficiarioController {

	@Autowired
	private BeneficiarioService beneficiarioService;
	
	@GetMapping
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso na execução.",
				content = { @Content(mediaType = "application/json",
				array = @ArraySchema(schema = @Schema(implementation = BeneficiarioDTO.class))) }),
			@ApiResponse(responseCode = "401", description = "Não Autorizado", content = @Content),
			@ApiResponse(responseCode = "500", description = "Foi gerada uma exceção", content = @Content), })
	public ResponseEntity<?> buscarTodosBeneficiarios() {
		return ResponseEntity.ok(beneficiarioService.buscarTodos());
	}

	@DeleteMapping("{id}")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Registro excluído.", content = @Content),
			@ApiResponse(responseCode = "401", description = "Não Autorizado", content = @Content),
			@ApiResponse(responseCode = "500", description = "Foi gerada uma exceção", content = @Content) })
	public ResponseEntity deletarBeneficiario(@PathVariable final Long id) {
		beneficiarioService.deletarBeneficiario(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("{id}")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Sucesso na execução.",
			content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BeneficiarioDTO.class)) }),
			@ApiResponse(responseCode = "401", description = "Não Autorizado", content = @Content),
			@ApiResponse(responseCode = "500", description = "Foi gerada uma exceção", content = @Content), })
	public ResponseEntity<BeneficiarioDTO> buscarBeneficiario(@PathVariable final Long id) {
		return ResponseEntity.ok(beneficiarioService.buscarPorId(id));
	}

	@PostMapping
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Criado com sucesso.",
			content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BeneficiarioDTO.class)) }),
			@ApiResponse(responseCode = "401", description = "Não Autorizado", content = @Content),
			@ApiResponse(responseCode = "500", description = "Foi gerada uma exceção", content = @Content), })
	public ResponseEntity<BeneficiarioDTO> criar(@RequestBody final BeneficiarioDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(beneficiarioService.criar(dto));
	}

	@PutMapping("{id}")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Sucesso na execução.",
			content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BeneficiarioDTO.class))}),
			@ApiResponse(responseCode = "401", description = "Não Autorizado", content = @Content),
			@ApiResponse(responseCode = "500", description = "Foi gerada uma exceção", content = @Content), })
	public ResponseEntity<BeneficiarioDTO> atualizar(@PathVariable final Long id, @RequestBody final BeneficiarioAtualizarDTO dto) {
		return ResponseEntity.ok().body(beneficiarioService.alterar(id,dto));
	}
}
