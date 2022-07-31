package br.com.glandata.api.dto;

import lombok.Getter;
import lombok.Setter;

public class CategoriaDto {
	@Getter
	@Setter
	private Long id;
	
	@Getter
	@Setter
	private String nome;

	@Getter
	@Setter
	private String descricao;
}
