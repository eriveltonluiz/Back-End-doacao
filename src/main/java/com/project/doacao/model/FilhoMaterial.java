package com.project.doacao.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.project.doacao.Enum.EnumDoacao;
import com.project.doacao.model.PK.FilhoMaterialPK;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilhoMaterial {
	
	@EmbeddedId
	private FilhoMaterialPK id = new FilhoMaterialPK();
	
	private Double quantidadeDesejada;
	private Double quantidadeDoada;
	
	@Enumerated(EnumType.STRING)
	private EnumDoacao statusDoacao;
}
