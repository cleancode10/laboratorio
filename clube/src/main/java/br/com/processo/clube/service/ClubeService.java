/**
 * 
 */
package br.com.processo.clube.service;

import java.util.List;

import br.com.processo.clube.entity.ClubeEntity;

/**
 * @author Vagner
 *
 */
public interface ClubeService {
	
	
	ClubeEntity findOne(Long id);
	
	ClubeEntity findByNome(String nome);
	
	ClubeEntity salvarClube(ClubeEntity entity);
	
	void excluirClube(Long id);
	
	ClubeEntity atualizarClube(ClubeEntity entity);
	
	List<ClubeEntity> listAll();
	
	List<ClubeEntity> findByIdIn(List<Long> ids);
	

}
