package com.project.doacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.doacao.model.FilhoMaterial;
import com.project.doacao.model.Material;
import com.project.doacao.model.dto.FilhoMatInterfaceDTO;
import com.project.doacao.repository.MaterialFilhoRepository;

@Service
public class DoacaoService {

	@Autowired
	private MaterialFilhoRepository materialFilhoRepository;

	public List<FilhoMatInterfaceDTO> filterListChildrenDependentOfDonationOfMaterial(Long idmaterial) {
		try {
		Material material = new Material(idmaterial, null, null);
		FilhoMaterial list = materialFilhoRepository.findFirstByIdMaterialId(material.getId()).get();
			
//			for (FilhoMaterial f : list) {
//				System.out.println(f.getId().getMaterial().getDescricao());
//			}
			
			List<FilhoMatInterfaceDTO> interfaceDTO = materialFilhoRepository.findMaterialsOfChildById(idmaterial);
			return interfaceDTO;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<FilhoMatInterfaceDTO> listChildrenDependentOfDonation() {
		List<FilhoMatInterfaceDTO> list = materialFilhoRepository.openChildMaterials();
		return list;
	}

	public List<FilhoMaterial> listChildrenMaterialDonation(String nome) {
		return materialFilhoRepository.findMaterialsChildrenOpen(nome);
	}
	
	public List<FilhoMaterial> listChildrenMaterialsById(Long id){
		return materialFilhoRepository.findAllMaterialsChildById(id);
	}
	
	public FilhoMaterial registerOfChildMaterialById(Long idFilho, Long idMaterial) {
		FilhoMaterial filhoMaterial = materialFilhoRepository.findChildMaterialById(idFilho, idMaterial).get();
		return filhoMaterial;
	}
	
	public List<FilhoMaterial> editAll(List<FilhoMaterial> list){
		return materialFilhoRepository.saveAll(list);
	}
	
	public FilhoMaterial edit(FilhoMaterial filhoMaterial){
		return materialFilhoRepository.save(filhoMaterial);
	}
	
	public FilhoMaterial save(FilhoMaterial filhoMaterial){
		return materialFilhoRepository.save(filhoMaterial);
	}
	
	public void delete(FilhoMaterial filhoMaterial){
		materialFilhoRepository.delete(filhoMaterial);
	}
}
