package br.com.processo.sociotorcedor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.processo.sociotorcedor.entity.Socio;

@Repository
public interface SocioRepository extends JpaRepository<Socio, Long> {
	
	Socio findByNome(String nome);
	
	Socio findByEmail(String email);
	
	
	List<Socio> findByNomeIgnoreCaseContaining(String nome);
	
	List<Socio> findByNomeStartingWith(String nome);

}
