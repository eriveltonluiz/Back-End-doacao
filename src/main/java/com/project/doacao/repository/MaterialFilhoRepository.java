package com.project.doacao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.doacao.model.FilhoMaterial;
import com.project.doacao.model.PK.FilhoMaterialPK;
import com.project.doacao.model.dto.FilhoMatInterfaceDTO;

@Repository
public interface MaterialFilhoRepository extends JpaRepository<FilhoMaterial, FilhoMaterialPK> {
	
	@Query(nativeQuery = true, value = "select e.nome as nomeEscola, f.nome as nomeFilho, f.url_foto as urlFoto, e.uf, e.localidade from filho_material as fm " + 
			"inner join filho as f on fm.filho_id = f.id " + 
			"inner join escola as e on f.escola_id = e.id " + 
			"where fm.status_doacao != 'CONFIRMADO' group by e.nome, f.nome, f.url_foto, e.uf, e.localidade")
	List<FilhoMatInterfaceDTO> openChildMaterials();
	
	@Query(nativeQuery = true,value="select * from filho_material as fm inner join filho as f " + 
			"on filho_id = f.id " + 
			"inner join material as ma on fm.material_id = ma.id " + 
			"where fm.status_doacao != 'CONFIRMADO' and f.nome = :filho")
	List<FilhoMaterial> findMaterialsChildrenOpen(@Param("filho") String filho);
	
	@Query(nativeQuery = true,value="select * from filho_material as fm inner join filho as f " + 
			"on filho_id = f.id " + 
			"inner join material as ma on fm.material_id = ma.id " + 
			"where fm.status_doacao != 'CONFIRMADO' and f.id = :id")
	List<FilhoMaterial> findAllMaterialsChildById(@Param("id") Long id);
	
	@Query("select fm from FilhoMaterial fm where fm.id.filho.id = ?1 and fm.id.material.id = ?2")
	Optional<FilhoMaterial> findChildMaterialById(Long id, Long idmat);
	
	@Query(nativeQuery = true, value = "select e.nome as nomeEscola, f.nome as nomeFilho, f.url_foto as urlFoto, e.uf, e.localidade from filho_material as fm inner join filho as f " + 
			"on fm.filho_id = f.id " + 
			"inner join escola as e on f.escola_id = e.id " + 
			"inner join material as ma on fm.material_id = ma.id " +
			"where fm.status_doacao != 'CONFIRMADO' and ma.id = :mat " +
			"group by f.nome, e.uf, e.localidade, f.url_foto, e.nome")
	List<FilhoMatInterfaceDTO> findMaterialsOfChildById(@Param("mat")Long idmaterial);
	
	Optional<FilhoMaterial> findFirstByIdMaterialId(Long idMaterial);
}
