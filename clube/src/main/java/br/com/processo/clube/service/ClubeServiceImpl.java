/**
 * 
 */
package br.com.processo.clube.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.processo.clube.entity.ClubeEntity;
import br.com.processo.clube.repository.ClubeRepository;

/**
 * @author Vagner
 *
 */
@Service
public class ClubeServiceImpl implements ClubeService {
	
	@Autowired
	private ClubeRepository repository;

	/* (non-Javadoc)
	 * @see br.com.processo.clube.service.ClubeService#findOne(java.lang.Long)
	 */
	@Override
	public ClubeEntity findOne(Long id) {
		return repository.findOne(id);
	}

	/* (non-Javadoc)
	 * @see br.com.processo.clube.service.ClubeService#findByNome(java.lang.String)
	 */
	@Override
	public ClubeEntity findByNome(String nome) {
		return repository.findByNome(nome);
	}

	/* (non-Javadoc)
	 * @see br.com.processo.clube.service.ClubeService#salvarClube(br.com.processo.clube.entity.ClubeEntity)
	 */
	@Override
	public ClubeEntity salvarClube(ClubeEntity entity) {
		return repository.save(entity);
	}

	/* (non-Javadoc)
	 * @see br.com.processo.clube.service.ClubeService#excluirClube(java.lang.Long)
	 */
	@Override
	public void excluirClube(Long id) {
		repository.delete(id);
	}

	/* (non-Javadoc)
	 * @see br.com.processo.clube.service.ClubeService#atualizarClube(br.com.processo.clube.entity.ClubeEntity)
	 */
	@Override
	public ClubeEntity atualizarClube(ClubeEntity entity) {
		return repository.saveAndFlush(entity);
	}

	@Override
	public List<ClubeEntity> listAll() {
		
		return repository.findAll();
	}

	@Override
	public List<ClubeEntity> findByIdIn(List<Long> ids) {
		
		return repository.findByIdIn(ids);
	}

}
