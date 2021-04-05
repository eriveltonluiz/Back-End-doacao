package com.project.doacao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.doacao.model.FilhoMaterial;
import com.project.doacao.service.EmailService;

@RestController
@RequestMapping("/email/")
public class EmailController {

	@Autowired
	private EmailService emailService;

	@PostMapping(value = "{email}" ,produces = "application/json")
	public ResponseEntity<FilhoMaterial> enviarEmail(@PathVariable("email") String email, @RequestBody List<FilhoMaterial> list) {
		
		StringBuilder sbList = new StringBuilder();
		StringBuilder sb = new StringBuilder();
		sb.append("Responsável da criança: " + list.get(0).getId().getFilho().getNome() + "\n")
		.append("Contato responsável: " + list.get(0).getId().getFilho().getPai().getCelular() + "\n")
		.append("E-mail responsável: " + list.get(0).getId().getFilho().getPai().getEmail() + "\n")
		.append("\n")
		.append("Endereço da escola: Rua " + list.get(0).getId().getFilho().getEscola().getLogradouro() + ", " + list.get(0).getId().getFilho().getEscola().getNumero()+ "\n")
		.append("CEP: " + list.get(0).getId().getFilho().getEscola().getCep() + "\n")
		.append(list.get(0).getId().getFilho().getEscola().getUf() + " - " + list.get(0).getId().getFilho().getEscola().getLocalidade() + "\n");
	
		sbList.append("Lista de materiais doados: \n\n");
	for (FilhoMaterial fm : list) {
		sbList.append("Material: " + fm.getId().getMaterial().getDescricao() + "          Quantidade: " + fm.getQuantidadeDoada().intValue() + "\n");
	}
	try {
		emailService.enviarEmail("Dados de contato do responsável e da escola.", email, sb.toString() + "\n\n\n" + sbList.toString());
		return ResponseEntity.ok().body(list.get(0));
	} catch (Exception e) {
		e.printStackTrace();
		return null;
	}

	}
}
