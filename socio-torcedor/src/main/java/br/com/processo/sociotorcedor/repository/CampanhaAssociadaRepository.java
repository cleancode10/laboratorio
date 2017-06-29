package br.com.processo.sociotorcedor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.processo.sociotorcedor.entity.CampanhaAssociada;
import br.com.processo.sociotorcedor.entity.Socio;

@Repository
public interface CampanhaAssociadaRepository extends JpaRepository<CampanhaAssociada, Long> {
	
	
	List<CampanhaAssociada> findBySocio(Socio socio);
	
	List<CampanhaAssociada> findByCampanha(Long campanha);
	
	

}
