package br.com.glandata.api.dto;

import lombok.Getter;

public class TokenDto {

	public TokenDto(String token, String tipo) {
		this.token = token;
		this.tipo = tipo;
	}

	@Getter
	private String token;

	@Getter
	private String tipo;
}
