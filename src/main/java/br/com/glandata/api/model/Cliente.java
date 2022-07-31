package br.com.glandata.api.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "clientes")
public class Cliente {
	public Cliente() {
	}

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Getter
	@Setter
	@Column(length = 50)
	@Size(min = 3, max = 50, message = "O nome do cliente deve ter entre 3 e 50 caracteres")
	@NotBlank(message = "O nome do cliente não pode ser vazio")
	private String nome;

	@Getter
	@Setter
	@CPF(message = "Informe um CPF válido")
	@NotBlank(message = "O cpf do cliente não pode ser vazio")
	private String cpf;

	@Getter
	@Setter
	@Email(message = "Digite um e-mail válido")
	/* @Pattern(regexp = "^(.+)@(.+)$", message = "E-mail inválido") */
	private String email;

	@Getter
	@Setter
	private LocalDate dataNascimento;
}
