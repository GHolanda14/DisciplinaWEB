package com.monitorar.artigo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ArtigoService {
	private final ArtigoRepository artigoRepository;
	
	@Autowired
	public ArtigoService(ArtigoRepository artigoRepository) {
		this.artigoRepository = artigoRepository;
	}

	//CREATE
	@Transactional
	public ResponseEntity<Object> addArtigo(Artigo artigo){
		if(!artigoRepository.existsByTitulo(artigo.getTitulo())) {
			artigoRepository.save(artigo);
		}
		else{
			return ResponseEntity.internalServerError().body("Este título já foi cadastrado");				
		}

		return ResponseEntity.created(null).body("Artigo cadastrado com sucesso");
	}
	
	//READ
	@Transactional
	public List<Artigo> getArtigos(){return artigoRepository.findAll();}
	
	@Transactional
	public List<Artigo> getArtigosByCategoria(String nomeCategoria){
		List<Artigo> artigos;
		try{
			artigos = artigoRepository.findAllByCategoria(nomeCategoria);
		}
		catch (Exception e) {
			return null;
		}
		
		return artigos;
	}
	
	@Transactional
	public List<Artigo> getArtigosByStatus(String nomeStatus){
		List<Artigo> artigos;
		try {
			artigos = artigoRepository.findAllByStatus(nomeStatus);
		}
		catch (Exception e) {
			return null;
		}
		
		return artigos;
	}
	
	@Transactional
	public List<Artigo> getArtigosByAutor(long autorId) {
		List<Artigo> artigos;
		try {
			artigos = artigoRepository.findAllByAutorId(autorId);
		}
		catch (Exception e) {
			ResponseEntity.badRequest().body("Aluno com o id "+autorId+" não encontrado");
			return null;
		}
		
		return artigos;
	}
	
	//UPDATE
	@Transactional
	public ResponseEntity<Object> updateStatus(long id, String status) {
		Artigo artigo;
		
		try {		
			artigo = artigoRepository.findById(id).get();
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body("Artigo com o id "+id+" não encontrado");
		}
		
		artigo.setStatus(status);		
		artigoRepository.save(artigo);
		
		return ResponseEntity.ok("Status do artigo "+"alterado para "+status);
	}
	@Transactional
	public ResponseEntity<Object> updateTitulo(long id, String titulo) {
		Artigo artigo;
		
		try {		
			artigo = artigoRepository.findById(id).get();
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body("Artigo com o id "+id+" não encontrado");
		}
		
		if(artigoRepository.existsByTitulo(titulo)) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Esse título já existe");
		}
			
		artigo.setTitulo(titulo);		
		artigoRepository.save(artigo);
		
		return ResponseEntity.ok("Titulo do artigo alterado para "+titulo);
	}
	
	@Transactional
	public ResponseEntity<Object> updateSubtitulo(long id, String subtitulo) {
		Artigo artigo;
		
		try {		
			artigo = artigoRepository.findById(id).get();
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body("Artigo com o id "+id+" não encontrado");
		}
		
		artigo.setSubtitulo(subtitulo);		
		artigoRepository.save(artigo);
		
		return ResponseEntity.ok("Subtitulo do artigo alterado para "+subtitulo);
	}

	
	//DELETE
	@Transactional
	public ResponseEntity<Object> deleteArtigo(long id){
		if(artigoRepository.existsById(id)) {
			artigoRepository.deleteById(id);
		}else {
			return ResponseEntity.badRequest().body("Artigo com o id "+id+" não encontrado");
		}
		
		return ResponseEntity.ok("Artigo deletado com sucesso"); 
	}	
}
