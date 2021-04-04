package com.project.doacao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.doacao.model.Filho;
import com.project.doacao.model.Pai;
import com.project.doacao.repository.FilhoRepository;
import com.project.doacao.repository.PaiRepository;

@RestController
@RequestMapping("/login/")
public class LoginController {
	
	@Autowired
	private PaiRepository paiRepository;
	
	@GetMapping
	public ResponseEntity<Pai> listar(@RequestBody Pai pai){
		return ResponseEntity.ok().body(paiRepository.findPaiByEmailSenha(pai.getEmail(), pai.getSenha()));
	}
	
	@PostMapping
	public ResponseEntity<Pai> salvar(@RequestBody Pai pai){
		return ResponseEntity.ok().body(paiRepository.save(pai));
	}

}
