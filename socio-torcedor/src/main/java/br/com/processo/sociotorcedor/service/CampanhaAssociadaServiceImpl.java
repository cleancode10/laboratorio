/**
 * 
 */
package br.com.processo.sociotorcedor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.processo.sociotorcedor.entity.CampanhaAssociada;
import br.com.processo.sociotorcedor.entity.Socio;
import br.com.processo.sociotorcedor.repository.CampanhaAssociadaRepository;

/**
 * @author Vagner
 *
 */
@Service
public class CampanhaAssociadaServiceImpl implements CampanhaAssociadaService {
	
	@Autowired
	private CampanhaAssociadaRepository repository;

	/* (non-Javadoc)
	 * @see br.com.processo.sociotorcedor.service.CampanhaAssociadaService#findByIdSocio(java.lang.Long)
	 */
	@Override
	public List<CampanhaAssociada> findByIdSocio(Socio idSocio) {
		
		return repository.findBySocio(idSocio);
	}

	/* (non-Javadoc)
	 * @see br.com.processo.sociotorcedor.service.CampanhaAssociadaService#findByIdCampanha(java.lang.Long)
	 */
	@Override
	public List<CampanhaAssociada> findByIdCampanha(Long idCampanha) {
		return repository.findByCampanha(idCampanha);
	}

	@Override
	public List<CampanhaAssociada> salvarListAssociacao(List<CampanhaAssociada> lst) {
		
		return repository.save(lst);
	}

}
