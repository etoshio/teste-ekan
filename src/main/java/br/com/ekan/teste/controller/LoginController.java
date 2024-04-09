package br.com.ekan.teste.controller;

import br.com.ekan.teste.domain.LoginDTO;
import br.com.ekan.teste.domain.TokenJwtDTO;
import br.com.ekan.teste.entity.Usuario;
import br.com.ekan.teste.service.TokenService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class LoginController {

	@Autowired private AuthenticationManager authenticationManager;
	@Autowired private TokenService tokenService;
	
	@PostMapping
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Sucesso na execução.",
			content = { @Content(mediaType = "application/json", schema = @Schema(implementation = TokenJwtDTO.class)) }),
			@ApiResponse(responseCode = "403", description = "Não Autorizado", content = @Content),
			@ApiResponse(responseCode = "500", description = "Foi gerada uma exceção", content = @Content), })
	public ResponseEntity<TokenJwtDTO> efetuarLogin(@RequestBody @Valid LoginDTO dto) {
		
		Authentication authenticationToken = new UsernamePasswordAuthenticationToken(dto.login(), dto.senha());
		Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
		
		Usuario usuario = (Usuario)authentication.getPrincipal();
		
		String tokenJWT = tokenService.gerarToken(usuario);
		
		return ResponseEntity.ok(new TokenJwtDTO(tokenJWT));
	}
	
}
