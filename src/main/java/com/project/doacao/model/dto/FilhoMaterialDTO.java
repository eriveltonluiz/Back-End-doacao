package com.project.doacao.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilhoMaterialDTO {
	
	private String nomePai;
	private String nomeFilho;
	private String emailPai;
	private String emailDoador;
	private String contatoPai;
	private String rua;
	private String numero;
	private String cep;
	private String uf;
	private String localidade;
	
}
