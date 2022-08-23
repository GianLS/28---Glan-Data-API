package br.com.glandata.api.dto;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

public class CategoriaDto extends RepresentationModel<CategoriaDto> {
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
