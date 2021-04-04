package com.project.doacao.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class Pai {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String nome;
	
	@Column(unique = true)
	private String email;
	
	private String senha;
	
	@Column(unique = true)
	private String cpf;
	
	@Column(unique = true)
	private String celular;
	
	private Double renda;
	
	@JsonIgnore
	@OneToMany(mappedBy = "pai")
	private List<Filho> filhos = new ArrayList<Filho>();
	
}
