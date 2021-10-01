package com.monitorar.autor;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository 
	extends JpaRepository<Autor, Long> {
	public Optional<Autor> findByEmail(String email);
	
	boolean existsByEmail(String email);	
	boolean existsByMatricula(String matricula);
}