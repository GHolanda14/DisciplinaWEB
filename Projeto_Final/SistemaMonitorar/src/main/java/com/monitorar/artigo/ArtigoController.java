package com.monitorar.artigo;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.monitorar.autor.Autor;
import com.monitorar.autor.AutorRepository;

@RestController
@RequestMapping(path = "/artigo")
@ResponseBody
public class ArtigoController {
	private final ArtigoService artigoService;
	private final AutorRepository autorRepository;
	
	@Autowired
	public ArtigoController(ArtigoService artigoService,AutorRepository autorRepository) {
		this.artigoService = artigoService;
		this.autorRepository = autorRepository;
	}
	
	//CREATE
	@PostMapping
	@Transactional
	public ResponseEntity<Object> addAutor(@RequestBody @Valid CreateArtigo createArtigo){
		if(!autorRepository.existsByEmail(createArtigo.getEmailAutor())) {
			return ResponseEntity.badRequest().body("Autor não cadastrado");
		}
		
		Autor autor = autorRepository.findByEmail(createArtigo.getEmailAutor()).get();
		Artigo artigo = createArtigo.toModel(autor);
		
		return artigoService.addArtigo(artigo);
	}
	
	//READ
	@GetMapping
	public List<ArtigoResponse> getArtigos(){
		List<Artigo> artigos = artigoService.getArtigos();
		
		ArtigoResponse artigoResponse = new ArtigoResponse();
		
		return artigoResponse.toResponse(artigos);
	}
	
	@GetMapping(path="/categoria")
	public List<ArtigoResponse> getArtigosByCategoria(@RequestParam String nomeCategoria){
		List<Artigo> artigos = artigoService.getArtigosByCategoria(nomeCategoria);
		
		ArtigoResponse artigoResponse = new ArtigoResponse();
		
		return artigoResponse.toResponse(artigos);
	}
	
	@GetMapping(path="/status")
	public List<ArtigoResponse> getArtigosByStatus(@RequestParam String nomeStatus){
		List<Artigo> artigos = artigoService.getArtigosByStatus(nomeStatus);
		
		ArtigoResponse artigoResponse = new ArtigoResponse();
		
		return artigoResponse.toResponse(artigos);

	}
	
	@GetMapping(path="/autor/{id}")
	public List<ArtigoResponse> getArtigosPorAutor(@PathVariable long id){
		List<Artigo> artigos = artigoService.getArtigosByAutor(id);
		
		ArtigoResponse artigoResponse = new ArtigoResponse();
		
		return artigoResponse.toResponse(artigos);

	}
	
	//UPDATE
	@PatchMapping(path="/{id}/status")
	@Transactional
	public ResponseEntity<Object> updateStatus(@PathVariable long id, @RequestBody UpdateCampo campo){
		return artigoService.updateStatus(id,campo.getCampo());
	}
	
	@PatchMapping(path="/{id}/titulo")
	@Transactional
	public ResponseEntity<Object> updateTitulo(@PathVariable long id, @RequestBody UpdateCampo campo){
		return artigoService.updateTitulo(id,campo.getCampo());
	}
	
	@PatchMapping(path="/{id}/subtitulo")
	@Transactional
	public ResponseEntity<Object> updateSubtitulo(@PathVariable long id, @RequestBody UpdateCampo campo){
		return artigoService.updateSubtitulo(id,campo.getCampo());
	}
	//DELETE
	@DeleteMapping(path="/{id}")
	@Transactional
	public ResponseEntity<Object> deleteArtigo(@PathVariable long id){
		return artigoService.deleteArtigo(id);
	}
	
}
