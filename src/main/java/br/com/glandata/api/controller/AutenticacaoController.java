package br.com.glandata.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.glandata.api.dto.TokenDto;
import br.com.glandata.api.model.LoginForm;
import br.com.glandata.api.security.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Realiza a autenticação para acesso aos endpoints")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private TokenService tokenService;

	@PostMapping("")
	@Operation(summary = "Recebe usuário e senha")
	public ResponseEntity<TokenDto> autenticar(@RequestBody LoginForm form) {
		UsernamePasswordAuthenticationToken dadosLogin = form.converter();

		try {
			Authentication authentication = authManager.authenticate(dadosLogin);

			String token = tokenService.gerarToken(authentication);

			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
