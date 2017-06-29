/**
 * 
 */
package br.com.processo.sociotorcedor.service;

import java.util.List;

import br.com.processo.sociotorcedor.entity.Socio;

/**
 * @author Vagner
 *
 */
public interface SocioService {
	
	Socio findById(Long id);
	
	Socio findByNome(String nome);
	
	Socio findByEmail(String email);
	
	List<Socio> findByNomeIgnoreCaseContaining(String nome);
	
	List<Socio> findByNomeStartingWith(String nome);
	
	List<Socio> listAll();
	
	Socio salvarSocio(Socio entity);
	
	Socio atualizarSocio(Socio entity);
	
	void excluirSocio(Long id);

}
