/**
 * 
 */
package br.com.processo.campanha.servico;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.processo.campanha.entity.Campanha;
import br.com.processo.campanha.repository.CampanhaRepository;

/**
 * @author Vagner
 *
 */
@Service
public class CampanhaServicoImpl implements CampanhaServico {

	@Autowired
	private CampanhaRepository repository;
	/* (non-Javadoc)
	 * @see br.com.processo.campanha.servico.CampanhaServico#findAll()
	 */
	@Override
	public List<Campanha> findAll() {
		return repository.findAll();
	}

	/* (non-Javadoc)
	 * @see br.com.processo.campanha.servico.CampanhaServico#findById(java.lang.Long)
	 */
	@Override
	public Campanha findById(Long id) {
		return repository.findOne(id);
	}

	/* (non-Javadoc)
	 * @see br.com.processo.campanha.servico.CampanhaServico#findByNome(java.lang.String)
	 */
	@Override
	public Campanha findByNome(String nome) {
		return repository.findByNome(nome);
	}

	/* (non-Javadoc)
	 * @see br.com.processo.campanha.servico.CampanhaServico#salvarCampanha(br.com.processo.campanha.entity.Campanha)
	 */
	@Override
	public Campanha salvarCampanha(Campanha entity) {
		return repository.save(entity);
	}

	/* (non-Javadoc)
	 * @see br.com.processo.campanha.servico.CampanhaServico#atualizarCampanha(br.com.processo.campanha.entity.Campanha)
	 */
	@Override
	public Campanha atualizarCampanha(Campanha entity) {
		return repository.saveAndFlush(entity);
	}

	/* (non-Javadoc)
	 * @see br.com.processo.campanha.servico.CampanhaServico#excluirCampanha(java.lang.Long)
	 */
	@Override
	public void excluirCampanha(Long id) {
		repository.delete(id);
	}

	@Override
	public List<Campanha> findByDtFimGreaterThanEqual(LocalDate dtNow) {
		DateTimeFormatter formatadorBarra = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String format = dtNow.format(formatadorBarra);
		
		
		return repository.findByDtFimGreaterThanEqual(LocalDate.parse(format));
	}

	@Override
	public List<Campanha> findByDtFimAfter(LocalDate dtNow) {

		return repository.findByDtFimAfter(dtNow);
	}

	@Override
	public List<Campanha> findByIdIn(List<Long> list) {
		
		return repository.findByIdIn(list);
	}

}
