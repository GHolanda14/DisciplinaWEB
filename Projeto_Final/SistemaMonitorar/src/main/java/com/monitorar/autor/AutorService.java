package com.monitorar.autor;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AutorService {
	private final AutorRepository autorRepository;

	@Autowired
	public AutorService(AutorRepository autorRepository) {
		this.autorRepository = autorRepository;
	}

	//CREATE		
	@Transactional
	public ResponseEntity<Object> addAutor(Autor autor){
		boolean existeEmail = autorRepository.existsByEmail(autor.getEmail());
		boolean existeMatricula = autorRepository.existsByMatricula(autor.getMatricula());
		
		if(existeEmail) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao cadastrar autor");
		}
		else if(existeMatricula) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Matricula já cadastrada");
		}
		
		autorRepository.save(autor);
		
		return ResponseEntity.created(null).body("Autor cadastrado com sucesso");
	}
	
	//READ
	public List<Autor> getAutores(){ return autorRepository.findAll();}
	
	//UPDATE
	@Transactional
	public ResponseEntity<Object> updateAutor(long id, Autor autor){
		Autor autorDb;
		try {
			   autorDb = autorRepository.findById(id).get();
			}
		catch (NoSuchElementException e) {
			   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Aluno não encontrado");
		}
		
		boolean existeEmail = autorRepository.existsByEmail(autor.getEmail());
		boolean existeMatricula = autorRepository.existsByMatricula(autor.getMatricula());
		
		if(existeEmail && !autorDb.getEmail().equals(autor.getEmail())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao atualizar autor");
		}
		else if(existeMatricula && !autorDb.getMatricula().equals(autor.getMatricula())) {
			System.out.println(autorDb.getMatricula()+ " "+autor.getMatricula());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Matricula já está cadastrada");
		}
		
		autorDb.setNome(autor.getNome());
		autorDb.setEmail(autor.getEmail());
		autorDb.setMatricula(autor.getMatricula());
		autorDb.setSiglaCurso(autor.getSiglaCurso());
		
		autorRepository.save(autorDb);
		
		return ResponseEntity.created(null).body("Autor atualizado com sucesso");
	}
	
	//DELETE
	@Transactional
	public ResponseEntity<Object> deleteAutor(long id ) {
		if(!autorRepository.existsById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não cadastrado");
		}
		autorRepository.deleteById(id);
		return ResponseEntity.ok("Autor deletado com sucesso");
	}
}

