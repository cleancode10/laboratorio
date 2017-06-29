/**
 * 
 */
package br.com.processo.sociotorcedor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.processo.sociotorcedor.entity.Socio;
import br.com.processo.sociotorcedor.repository.SocioRepository;

/**
 * @author Vagner
 *
 */
@Service
public class SocioServiceImpl implements SocioService {
	

	
	@Autowired
	private SocioRepository repository;

	/* (non-Javadoc)
	 * @see br.com.processo.sociotorcedor.service.SocioService#findById(java.lang.Long)
	 */
	@Override
	public Socio findById(Long id) {
		return repository.findOne(id);
	}

	/* (non-Javadoc)
	 * @see br.com.processo.sociotorcedor.service.SocioService#findByNome(java.lang.String)
	 */
	@Override
	public Socio findByNome(String nome) {
		return repository.findByNome(nome);
	}

	/* (non-Javadoc)
	 * @see br.com.processo.sociotorcedor.service.SocioService#findByEmail(java.lang.String)
	 */
	@Transactional
	@Override
	public Socio findByEmail(String email) {
		return repository.findByEmail(email);
	}

	/* (non-Javadoc)
	 * @see br.com.processo.sociotorcedor.service.SocioService#findByNomeIgnoreCaseContaining(java.lang.String)
	 */
	@Override
	public List<Socio> findByNomeIgnoreCaseContaining(String nome) {
		return repository.findByNomeIgnoreCaseContaining(nome);
	}

	/* (non-Javadoc)
	 * @see br.com.processo.sociotorcedor.service.SocioService#findByNomeStartingWith(java.lang.String)
	 */
	@Override
	public List<Socio> findByNomeStartingWith(String nome) {
		return repository.findByNomeStartingWith(nome);
	}

	@Override
	public List<Socio> listAll() {
		return repository.findAll();
	}

	@Override
	public Socio salvarSocio(Socio entity) {
		return repository.save(entity);
	}

	@Override
	public Socio atualizarSocio(Socio entity) {
		return repository.saveAndFlush(entity);
	}

	@Override
	public void excluirSocio(Long id) {
		repository.delete(id);
	}

	
		
}
