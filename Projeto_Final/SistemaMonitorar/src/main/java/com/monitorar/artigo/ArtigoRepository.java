package com.monitorar.artigo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ArtigoRepository 
	extends JpaRepository<Artigo, Long> {
	public boolean existsByTitulo(String titulo);
	
	public List<Artigo> findAllByAutorId(long id);
	public List<Artigo> findAllByStatus(String status);
	public List<Artigo> findAllByCategoria(String categoria);
}
