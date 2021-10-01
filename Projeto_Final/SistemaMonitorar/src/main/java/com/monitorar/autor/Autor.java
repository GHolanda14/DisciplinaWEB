package com.monitorar.autor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Autor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "O nome não pode ser vazio")
	private String nome;

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
	

	public Autor(@NotBlank(message = "O nome não pode ser vazio") String nome,
			@NotBlank(message = "O email não pode ser vazio") @Email(message = "E-mail inválido") String email,
			@NotBlank(message = "A sigla do curso não pode ser vazia") @Size(max = 2) String siglaCurso,
			@Size(max=6,min=6,message = "A matricula tem 6 digitos") @NotBlank(message = "A matricula não pode ser vazia") String matricula) {
		this.nome = nome;
		this.email = email;
		this.siglaCurso = siglaCurso;
		this.matricula = matricula;
	}

	@Deprecated
	public Autor() {}	
	
	public long getId() {
		return id;
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

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSiglaCurso(String siglaCurso) {
		this.siglaCurso = siglaCurso;
	}
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
}
