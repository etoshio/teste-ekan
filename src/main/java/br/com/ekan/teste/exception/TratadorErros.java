package br.com.ekan.teste.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class TratadorErros {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> tratarErro404() {
		return ResponseEntity.notFound().build();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> tratarErro400(MethodArgumentNotValidException e) {
		return ResponseEntity.badRequest().body(e.getFieldErrors().stream().map(ErroValidacaoDTO::new));
	}
	
	@ExceptionHandler(ValidacaoException.class)
	public ResponseEntity<?> tratarValidacao(ValidacaoException e) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("erro",  e.getMessage());
		return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(DateTimeParseException.class)
	public ResponseEntity<?> tratarValidacao(DateTimeParseException e) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("erro",  e.getMessage());
		return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<?> tratarValidacao(BadCredentialsException e) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("erro",  e.getMessage());
		return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
	}

	private record ErroValidacaoDTO(String campo, String mensagem) {
		
		public ErroValidacaoDTO(FieldError erro) {
			this(erro.getField(), erro.getDefaultMessage());
		}
	}
}
