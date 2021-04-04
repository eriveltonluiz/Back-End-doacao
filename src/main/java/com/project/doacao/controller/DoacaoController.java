package com.project.doacao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.doacao.model.FilhoMaterial;
import com.project.doacao.repository.MaterialFilhoRepository;

@RestController
@RequestMapping("/doacao/")
public class DoacaoController {
	
	@Autowired
	private MaterialFilhoRepository materiaisFilhoRepository;
	
	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Object>> listarCriancasDependentesDoacao(){
		
		List<Object> list = materiaisFilhoRepository.materiaisFilhoAberto();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="{nome}",produces = "application/json")
	public ResponseEntity<List<FilhoMaterial>> listarMateriaisCriancasDoacao(@PathVariable String nome){
		
		return ResponseEntity.ok().body(materiaisFilhoRepository.materiaisFilhoConfirmar(nome));
	}
	
	@GetMapping(value="buscar/{id}",produces = "application/json")
	public ResponseEntity<List<FilhoMaterial>> listarMateriaisCriancasDoacaoId(@PathVariable Long id){
		return ResponseEntity.ok().body(materiaisFilhoRepository.materiaisFilhoPorID(id));
	}
	
	@PutMapping(produces = "application/json")
	public ResponseEntity<List<FilhoMaterial>> returnConclusaoDoacaoMat(@RequestBody List<FilhoMaterial> filhoMaterial){
		return ResponseEntity.ok().body(materiaisFilhoRepository.saveAll(filhoMaterial));
	}
}
