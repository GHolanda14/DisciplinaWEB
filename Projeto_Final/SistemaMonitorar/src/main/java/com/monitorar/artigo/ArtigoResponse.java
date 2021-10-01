package com.monitorar.artigo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.monitorar.autor.AutorResponseArtigo;

public class ArtigoResponse {
	private long id;
	private String titulo;	
	private String subtitulo;	
	private String status;
	private String categoria;	
	private AutorResponseArtigo autor;
	private LocalDate dataCadastro;
	
	public ArtigoResponse() {
		
	}
	
	public ArtigoResponse(long id, String titulo, String subtitulo, String status, String categoria, AutorResponseArtigo autor,
			LocalDate dataCadastro) {
		this.id = id;
		this.titulo = titulo;
		this.subtitulo = subtitulo;
		this.status = status;
		this.categoria = categoria;
		this.autor = autor;
		this.dataCadastro = dataCadastro;
	}
	
	public List<ArtigoResponse> toResponse(List<Artigo> artigos){
		if(artigos.isEmpty()) {
			return null;
		}
		
		List<ArtigoResponse> artigosResponse = new ArrayList<>();
		
		for(Artigo artigo : artigos) {
			AutorResponseArtigo autor = new AutorResponseArtigo(artigo.getAutor().getId(),
					artigo.getAutor().getNome(),
					artigo.getAutor().getEmail());
			
			ArtigoResponse artigoResponse = new ArtigoResponse(
					artigo.getId(),
					artigo.getTitulo(),
					artigo.getSubtitulo(),
					artigo.getStatus(),
					artigo.getCategoria(),
					autor,
					artigo.getDataCadastro());
			
			artigosResponse.add(artigoResponse);
		}
		
		
		return artigosResponse;
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

	public AutorResponseArtigo getAutor() {
		return autor;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}	
	
}
