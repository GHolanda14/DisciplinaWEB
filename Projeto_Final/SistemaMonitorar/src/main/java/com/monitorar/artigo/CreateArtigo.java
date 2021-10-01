package com.monitorar.artigo;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

import com.monitorar.autor.Autor;


public class CreateArtigo {
	@NotBlank(message = "O titulo não pode ser vazio")
	private String titulo;
	
	@NotBlank(message = "O subtitulo não pode ser vazio")
	private String subtitulo;
	
	@NotBlank(message = "O status não pode ser vazio")
	private String status;
	
	@NotBlank(message = "A categoria não pode ser vazia")
	private String categoria;
	
	@NotBlank(message = "O autor não pode ser vazio")
	@Email(message = "Email inválido")
	private String emailAutor;
	
	@PastOrPresent
	private LocalDate dataCadastro;
	
	@Deprecated
	public CreateArtigo(){
		
	}

	public CreateArtigo(@NotBlank(message = "O titulo não pode ser vazio") String titulo,
			@NotBlank(message = "O subtitulo não pode ser vazio") String subtitulo,
			@NotBlank(message = "O status não pode ser vazio") String status,
			@NotBlank(message = "A categoria não pode ser vazia") String categoria,
			@NotBlank(message = "O autor não pode ser vazio") String emailAutor) {
		this.titulo = titulo;
		this.subtitulo = subtitulo;
		this.status = status;
		this.categoria = categoria;
		this.emailAutor = emailAutor;
		this.dataCadastro = LocalDate.now();
	}
	
	public Artigo toModel(Autor autor) {
		return new Artigo(this.titulo,this.subtitulo,this.status,this.categoria,autor);
	}

	public String getEmailAutor() {
		return emailAutor;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public String getSubtitulo() {
		return subtitulo;
	}

	public String getStatus() {
		return status;
	}

	public String getCategoria() {
		return categoria;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}
	
}
