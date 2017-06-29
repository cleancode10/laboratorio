/**
 * 
 */
package br.com.processo.campanha.servico;

import java.time.LocalDate;
import java.util.List;

import br.com.processo.campanha.entity.Campanha;

/**
 * @author Vagner
 *
 */
public interface CampanhaServico {
	
	List<Campanha> findAll();
	
	Campanha findById(Long id);
	
	Campanha findByNome(String nome);
	
	Campanha salvarCampanha(Campanha entity);
	
	Campanha atualizarCampanha(Campanha entity);
	
	void excluirCampanha(Long id);
	
	List<Campanha> findByDtFimGreaterThanEqual(LocalDate dtNow);
	
	List<Campanha> findByDtFimAfter(LocalDate dtNow);

	List<Campanha> findByIdIn(List<Long> list);
	
	

}
