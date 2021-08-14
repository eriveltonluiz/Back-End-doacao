package com.project.doacao.model.PK;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.project.doacao.model.Filho;
import com.project.doacao.model.Material;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilhoMaterialPK implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "filho_id")
	private Filho filho;
	
	@ManyToOne
	@JoinColumn(name = "material_id")
	private Material material;
}
