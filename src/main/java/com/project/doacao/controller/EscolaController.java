package com.project.doacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.doacao.model.Escola;
import com.project.doacao.model.Filho;
import com.project.doacao.repository.EscolaRepository;

@RestController
@RequestMapping("/escola/")
public class EscolaController {

	@Autowired
	private EscolaRepository escolaRepository;
	
	@PutMapping
	public ResponseEntity<Escola> editar(@RequestBody Escola escola){
		return ResponseEntity.ok().body(escolaRepository.save(escola));
	}
	
	@PostMapping
	public ResponseEntity<Escola> salvar(@RequestBody Escola escola){
		return ResponseEntity.ok().body(escolaRepository.save(escola));
	}
	
}
