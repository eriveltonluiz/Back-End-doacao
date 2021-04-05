package com.project.doacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.doacao.model.Pai;
import com.project.doacao.repository.PaiRepository;

@RestController
@RequestMapping("/login/")
public class LoginController {
	
	@Autowired
	private PaiRepository paiRepository;
	
	@PostMapping(value = "logar", produces = "application/json")
	public ResponseEntity<Pai> listar(@RequestBody Pai pai){
		Pai p = paiRepository.findPaiByEmailSenha(pai.getEmail(), pai.getSenha());
		return ResponseEntity.ok().body(p);
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<Pai> listar(@PathVariable Long id){
		return ResponseEntity.ok().body(paiRepository.findById(id).get());
	}
	
	@PostMapping
	public ResponseEntity<Pai> salvar(@RequestBody Pai pai){
		return ResponseEntity.ok().body(paiRepository.save(pai));
	}

}
