/**
 * 
 */
package br.com.processo.sociotorcedor.business;

import java.util.List;

import br.com.processo.sociotorcedor.client.Campanha;
import br.com.processo.sociotorcedor.entity.CampanhaAssociada;
import br.com.processo.sociotorcedor.entity.Socio;
import br.com.processo.sociotorcedor.vo.CampanhaVO;
import br.com.processo.sociotorcedor.vo.SocioVO;

/**
 * @author Vagner
 *
 */
public interface SocioBusines {
	
	SocioVO cadastrar(final SocioVO socio);
	
	boolean isEmail(final String emailStr);
	
	SocioVO findById(final Long id);
	
	Socio findSocioById(final Long id);
	
	SocioVO findByNome(final String nome);
	
	SocioVO findByEmail(final String email);
	
	List<SocioVO> listAll();
	
	List<Socio> listSocioAll();
	
	SocioVO salvarSocio(final SocioVO vo);
	
	SocioVO atualizarSocio(final SocioVO vo);
	
	void excluirSocio(final Long id);
	
	List<CampanhaAssociada> findByIdSocio(final Socio idSocio);
	
	List<CampanhaAssociada> findByIdCampanha(final Long idCampanha);
	
	List<CampanhaVO> transformarClientToCampanhaVO(final List<Campanha> campanhas);
	
	List<CampanhaAssociada> associarCampanhas(final Long socio, final List<CampanhaVO> lst);
	
	List<CampanhaAssociada> associarCampanhas(final Socio socio, final List<CampanhaVO> lst);
	
	Socio findSocioByEmail(final String email);
	
	SocioVO converterEntityParaVO(Socio entity, List<Campanha> campanhaClient);


}
