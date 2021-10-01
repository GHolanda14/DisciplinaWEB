package com.monitorar.autor;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CreateAutor {
	@NotBlank(message = "O nome não pode ser vazio")
	String nome;

	@NotBlank(message = "O email não pode ser vazio")
	@Email(message = "E-mail inválido")
	@Column(unique = true)
	private String email;
			
	@NotBlank(message = "A sigla do curso não pode ser vazia")
	@Size(max = 2)
	private String siglaCurso;
	
	@Column(unique = true)
	@Size(max=6,min=6,message = "A matricula tem 6 digitos")
	@NotBlank(message = "A matricula não pode ser vazia")
	private String matricula;
	
	@Deprecated
	public CreateAutor() {
	}
	
	public Autor toModel() {
		return new Autor(this.nome, this.email,	this.siglaCurso, this.matricula);
	}
	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getSiglaCurso() {
		return siglaCurso;
	}

	public String getMatricula() {
		return matricula;
	}	
	
	
}
