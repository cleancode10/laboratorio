/**
 * 
 */
package br.com.processo.campanha.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.processo.campanha.entity.Campanha;

/**
 * @author Vagner
 *
 */
@Repository
public interface CampanhaRepository extends JpaRepository<Campanha, Long> {
	
	Campanha findByNome(String nome);
	List<Campanha> findByDtFimGreaterThanEqual(LocalDate dtNow);
	
	@Query(value= "SELECT c FROM Campanha c where c.dtFim > :dtNow ")
 	List<Campanha> teste(@Param("dtNow") LocalDate dtNow);
	
	List<Campanha> findByDtFimAfter(LocalDate dtNow);
	
	List<Campanha> findByIdIn(List<Long> list);

}
