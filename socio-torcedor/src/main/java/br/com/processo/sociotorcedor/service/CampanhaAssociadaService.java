/**
 * 
 */
package br.com.processo.sociotorcedor.service;

import java.util.List;

import br.com.processo.sociotorcedor.entity.CampanhaAssociada;
import br.com.processo.sociotorcedor.entity.Socio;

/**
 * @author Vagner
 *
 */
public interface CampanhaAssociadaService {
	
	
	List<CampanhaAssociada> findByIdSocio(Socio idSocio);
	
	List<CampanhaAssociada> findByIdCampanha(Long idCampanha);
	
	List<CampanhaAssociada> salvarListAssociacao(List<CampanhaAssociada>lst);


}
