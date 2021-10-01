package com.monitorar.artigo;

public class UpdateCampo {
	private String campo;
	
	@Deprecated
	public UpdateCampo() {	}
	
	public UpdateCampo(String campo) {
		this.campo = campo;
	}
	
	public String getCampo() {
		return campo;
	}
	
}
