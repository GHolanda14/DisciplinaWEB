package com.monitorar.autor;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/autor")
@ResponseBody
public class AutorController {	
	private final AutorService autorService;
	
	@Autowired	
	public AutorController(AutorService autorService) {
		this.autorService = autorService;
	}

	//CREATE
	@PostMapping
	public ResponseEntity<Object> addAutor(@RequestBody @Valid CreateAutor createAutor) {
		Autor autor = createAutor.toModel();
		return autorService.addAutor(autor);
	}
	
	//READ
	@GetMapping
	public List<AutorResponse> getAutores(){
		List<Autor> autores = autorService.getAutores();
		if(autores.isEmpty()) {
			return null;
		}
		
		List<AutorResponse> autoresResponse= new ArrayList<>();
		
		for(Autor autor : autores) {
			AutorResponse a = new AutorResponse(autor.getId(),
					autor.getNome(),
					autor.getEmail(),
					autor.getSiglaCurso(),
					autor.getMatricula());
			
			autoresResponse.add(a);
		}
		
		return autoresResponse;
	}
	
	//UPDATE
	@PutMapping(path="/{id}")
	public ResponseEntity<Object> updateAutor(@PathVariable("id") long id, @RequestBody @Valid CreateAutor createAutor){
		Autor autor = createAutor.toModel();
		return autorService.updateAutor(id, autor);
	}
	
	//DELETE
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Object> deleteAutor(@PathVariable("id") long id){
		return autorService.deleteAutor(id);
	}
}
