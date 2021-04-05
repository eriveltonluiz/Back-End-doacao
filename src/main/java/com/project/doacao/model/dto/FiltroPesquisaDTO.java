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
public class FiltroPesquisaDTO {
	
	private Long material;
	private String nomeEscola;
	private String localidade;
}
