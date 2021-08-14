package com.project.doacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.doacao.model.Escola;
import com.project.doacao.repository.EscolaRepository;

@RestController
@RequestMapping("/escola/")
public class EscolaController {

	@Autowired
	private EscolaRepository escolaRepository;
	
	@PutMapping
	public ResponseEntity<Escola> editar(@RequestBody Escola escola){
		try {
			escola = escolaRepository.save(escola);
		return ResponseEntity.ok().body(escola);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PostMapping
	public ResponseEntity<Escola> salvar(@RequestBody Escola escola){
		escola.setId(null);
		return ResponseEntity.status(HttpStatus.CREATED).body(escolaRepository.save(escola));
	}
	
	@GetMapping(value = "{nome}")
	public ResponseEntity<Escola> listar(@PathVariable String nome){
		return ResponseEntity.ok().body(escolaRepository.findEscolaByNome(nome));
	}
	
}
