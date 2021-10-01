package com.monitorar.artigo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.monitorar.autor.Autor;


@Entity
public class Artigo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "O titulo não pode ser vazio")
	@Column(unique = true)
	private String titulo;
	
	@NotBlank(message = "O subtitulo não pode ser vazio")
	private String subtitulo;
	
	@NotBlank(message = "O status não pode ser vazio")
	private String status;
	
	@NotBlank(message = "A categoria não pode ser vazia")
	private String categoria;
	
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Autor autor;
	
	@PastOrPresent
	private LocalDate dataCadastro;
	
	@Deprecated
	public Artigo() {
		
	}
	
	public Artigo(@NotBlank(message = "O titulo não pode ser vazio") String titulo,
			@NotBlank(message = "O subtitulo não pode ser vazio") String subtitulo,
			@NotBlank(message = "O status não pode ser vazio") String status,
			@NotBlank(message = "A categoria não pode ser vazia") String categoria,
			Autor autor) {
		this.titulo = titulo;
		this.subtitulo = subtitulo;
		this.status = status;
		this.categoria = categoria;
		this.autor = autor;
		this.dataCadastro = LocalDate.now();
	}

	public long getId() {
		return id;
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

	public Autor getAutor() {
		return autor;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

}
