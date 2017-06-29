/**
 * 
 */
package br.com.processo.clube.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.processo.clube.entity.ClubeEntity;

/**
 * @author Vagner
 *
 */
@Repository
public interface ClubeRepository extends JpaRepository<ClubeEntity, Long> {
	
	
	ClubeEntity findOne(Long id);
	
	ClubeEntity findByNome(String nome);
	
	List<ClubeEntity> findByIdIn(List<Long> ids);

}
