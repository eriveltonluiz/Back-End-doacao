package com.project.doacao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.doacao.model.Filho;
import com.project.doacao.repository.FilhoRepository;

@RestController
@RequestMapping("/filho/")
public class FilhoController {

	@Autowired
	private FilhoRepository filhoRepository;
	
	@GetMapping
	public ResponseEntity<List<Filho>> listar(){
		return ResponseEntity.ok().body(filhoRepository.findAll());
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<List<Filho>> listar(@PathVariable Long id){
		return ResponseEntity.ok().body(filhoRepository.findFilhoByID(id));
	}
	
	@PutMapping
	public ResponseEntity<Filho> editar(@RequestBody Filho filho){
		return ResponseEntity.ok().body(filhoRepository.save(filho));
	}
	
	@PostMapping
	public ResponseEntity<Filho> salvar(@RequestBody Filho filho){
		return ResponseEntity.ok().body(filhoRepository.save(filho));
	}
	
	@DeleteMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Filho> deletar(@PathVariable(value = "id") Long id) {
		filhoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
