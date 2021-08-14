package com.project.doacao.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.doacao.model.FilhoMaterial;
import com.project.doacao.model.Material;
import com.project.doacao.model.dto.FilhoMatInterfaceDTO;
import com.project.doacao.model.dto.FilhoMaterialDTO;
import com.project.doacao.repository.MaterialFilhoRepository;
import com.project.doacao.repository.MaterialRepository;
import com.project.doacao.service.DoacaoService;

@RestController
@RequestMapping("/doacao/")
public class DoacaoController {

	@Autowired
	private MaterialFilhoRepository materiaisFilhoRepository;

	@Autowired
	private MaterialRepository materialRepository;

	@Autowired
	private DoacaoService doacaoService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping(value = "filtros/{id}", produces = "application/json")
	public ResponseEntity<Collection<FilhoMaterialDTO>> listarCriancasDependentesDoacaoFiltroMaterial(
			@PathVariable Long id) {

		List<FilhoMatInterfaceDTO> interfaceDTO = doacaoService.filterListChildrenDependentOfDonationOfMaterial(id);
		return ResponseEntity.ok().body(interfaceToCollectionsDTO(interfaceDTO));

	}

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<FilhoMaterialDTO>> listarCriancasDependentesDoacao() {

		List<FilhoMatInterfaceDTO> list = doacaoService.listChildrenDependentOfDonation();
		return ResponseEntity.ok().body(interfaceToCollectionsDTO(list));
	}

	@GetMapping(value = "{nome}", produces = "application/json")
	public ResponseEntity<List<FilhoMaterialDTO>> listarMateriaisCriancasDoacao(@PathVariable String nome) {

		return ResponseEntity.ok().body(toCollectionsDTO(doacaoService.listChildrenMaterialDonation(nome)));
	}

	@GetMapping(value = "material/{id}", produces = "application/json")
	public ResponseEntity<Material> listarMaterialPorID(@PathVariable Long id) {
		Optional<Material> material = materialRepository.findById(id);
		return ResponseEntity.ok().body(material.get());
	}

	@GetMapping(value = "material", produces = "application/json")
	public ResponseEntity<List<Material>> listarMateriais() {
		return ResponseEntity.ok().body(materialRepository.findAll());
	}

	@GetMapping(value = "{id}", produces = "application/json")
	public ResponseEntity<List<FilhoMaterialDTO>> listarMateriaisCriancasDoacaoId(@PathVariable Long id) {
		return ResponseEntity.ok().body(toCollectionsDTO(doacaoService.listChildrenMaterialsById(id)));
	}

	@PutMapping(produces = "application/json")
	public ResponseEntity<List<FilhoMaterialDTO>> returnConclusaoDoacaoMat(
			@RequestBody List<FilhoMaterial> filhoMaterial) {
		return ResponseEntity.ok().body(toCollectionsDTO(materiaisFilhoRepository.saveAll(filhoMaterial)));
	}

	@PutMapping("/registrounico")
	public ResponseEntity<FilhoMaterial> editar(@RequestBody FilhoMaterial filhoMaterial) {
		return ResponseEntity.ok().body(doacaoService.edit(filhoMaterial));
	}

	@PostMapping
	public ResponseEntity<FilhoMaterial> salvar(@RequestBody FilhoMaterial filhoMaterial) {
		return ResponseEntity.status(HttpStatus.CREATED).body(doacaoService.save(filhoMaterial));
	}

	@DeleteMapping(value = "{id}/{idmaterial}", produces = "application/json")
	public ResponseEntity<FilhoMaterial> deletar(@PathVariable Long id, @PathVariable Long idmaterial) {
		FilhoMaterial filhoMaterial = doacaoService.registerOfChildMaterialById(id, idmaterial);
		materiaisFilhoRepository.delete(filhoMaterial);
		return ResponseEntity.noContent().build();
	}

//	private FilhoMaterialDTO toDTO(FilhoMatInterfaceDTO interfaceDTO) {
//		return modelMapper.map(interfaceDTO, FilhoMaterialDTO.class);
//	}

	private List<FilhoMaterialDTO> interfaceToCollectionsDTO(List<FilhoMatInterfaceDTO> listInterfaceDTO) {
		return listInterfaceDTO.stream().map(interfaceDTO -> modelMapper.map(interfaceDTO, FilhoMaterialDTO.class))
				.collect(Collectors.toList());
	}
	
	private List<FilhoMaterialDTO> toCollectionsDTO(List<FilhoMaterial> list) {
		return list.stream().map(filhoMaterial -> modelMapper.map(filhoMaterial, FilhoMaterialDTO.class))
				.collect(Collectors.toList());
	}
}
