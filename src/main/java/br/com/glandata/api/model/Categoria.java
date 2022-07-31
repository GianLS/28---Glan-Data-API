package br.com.glandata.api.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "categorias")
public class Categoria {

	public Categoria() {
	}

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Getter
	@Setter
	@NotBlank
	@Size(max = 20)
	private String nome;

	@Getter
	@Setter
	@Size(max = 75)
	private String descricao;
	
	@Getter
    @OneToMany(mappedBy = "categoria")
	private List<Produto> produtos;
}
