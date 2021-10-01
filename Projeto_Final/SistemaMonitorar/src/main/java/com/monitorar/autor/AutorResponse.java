package com.monitorar.autor;


public class AutorResponse {
	private long id;
	private String nome;
	private String email;
	private String siglaCurso;
	private String matricula;

	@Deprecated
	public AutorResponse() {
	}
	
	public AutorResponse(long id,String nome, String email, String siglaCurso, String matricula) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.siglaCurso = siglaCurso;
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}
	
	public long getId() {
		return id;
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
