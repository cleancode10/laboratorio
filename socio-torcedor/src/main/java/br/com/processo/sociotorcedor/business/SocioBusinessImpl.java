/**
 * 
 */
package br.com.processo.sociotorcedor.business;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
//import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Strings;

import br.com.processo.sociotorcedor.client.Campanha;
import br.com.processo.sociotorcedor.entity.CampanhaAssociada;
import br.com.processo.sociotorcedor.entity.Socio;
import br.com.processo.sociotorcedor.service.CampanhaAssociadaService;
import br.com.processo.sociotorcedor.service.SocioService;
import br.com.processo.sociotorcedor.vo.CampanhaVO;
import br.com.processo.sociotorcedor.vo.SocioVO;

/**
 * @author Vagner
 *
 */
@Component
public class SocioBusinessImpl implements SocioBusines{
	
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	
	@Autowired
	private SocioService service;
	
	@Autowired
	private CampanhaAssociadaService campanhaAssociadaService;

	@Override
	public SocioVO cadastrar(SocioVO socio) {
		
		boolean isValido = isEmail(socio.getEmail());
		if(isValido){
			Socio entity = findSocioByEmail(socio.getEmail());
			if(entity == null){
				SocioVO inserted = salvarSocio(socio);
			}else{
				List<CampanhaAssociada> listAssociada = campanhaAssociadaService.findByIdSocio(entity);
				
				
			}
		}
		
		return null;
	}
	

	@Override
	public boolean isEmail(String emailStr) {
		boolean retorno = (!Strings.isNullOrEmpty(emailStr)? true :false);
		if(retorno){
			Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);	
			retorno = matcher.find();
		}
		return retorno;
	}

	@Override
	public SocioVO findById(Long id) {
		Socio entity = service.findById(id);
		if(entity != null)
			return copySocio(entity);
		return null;
	}

	@Override
	public SocioVO findByNome(String nome) {
		
		Socio entity = service.findByNome(nome);
		if(entity != null)
			return copySocio(entity);
		return null;
	}

	@Override
	public SocioVO findByEmail(String email) {
		Socio entity = service.findByEmail(email);
		if(entity != null)
			return copySocio(entity);
		return null;
	}
	
	@Override
	public Socio findSocioByEmail(String email) {
		return service.findByEmail(email);
	}



	@Override
	public List<SocioVO> listAll() {
		return listSocioAll().stream().map(SocioBusinessImpl::copySocio).collect(Collectors.toList());
	}
	
	@Override
	public List<Socio> listSocioAll() {
		return  service.listAll();
	}


	@Override
	public SocioVO salvarSocio(SocioVO vo) {
		SocioVO retorno = new SocioVO();
		Socio entity = new Socio();
		BeanUtils.copyProperties(vo, entity);
		Socio socioInserted = service.salvarSocio(entity);
		BeanUtils.copyProperties(socioInserted, retorno);
					
		return retorno;
	}
	

	@Override
	public SocioVO atualizarSocio(SocioVO vo) {
		Socio entity = copySocioVO(vo);
		Socio updated = service.atualizarSocio(entity);
		
		return copySocio(updated);
	}

	@Override
	public void excluirSocio(Long id) {
		service.excluirSocio(id);
		
	}
	
	private static SocioVO copySocio(Socio entity){
		
		SocioVO socio = null;
		if(entity != null){
			socio = new SocioVO();
			org.springframework.beans.BeanUtils.copyProperties(entity, socio);
		}
		return socio;
	}
	
	private static Socio copySocioVO(SocioVO vo){
		Socio socio = new Socio();
		org.springframework.beans.BeanUtils.copyProperties(vo, socio);
		return socio;
	}

	@Override
	public List<CampanhaAssociada> findByIdSocio(Socio idSocio) {
		
		return campanhaAssociadaService.findByIdSocio(idSocio);
	}

	@Override
	public List<CampanhaAssociada> findByIdCampanha(Long idCampanha) {
		return campanhaAssociadaService.findByIdCampanha(idCampanha);
	}


	@Override
	public List<CampanhaVO> transformarClientToCampanhaVO(List<Campanha> campanhas) {
		
		return campanhas.stream().map(SocioBusinessImpl::transformarClientToVo).collect(Collectors.toList());
		
	}
	
	@Override
	public SocioVO converterEntityParaVO(Socio entity, List<Campanha> campanhaClient) {
		
		SocioVO vo = copySocio(entity);
		if(vo != null){
			
			if(campanhaClient != null && campanhaClient.size() > 0){
				vo.setCampanhas(transformarClientToCampanhaVO(campanhaClient));
			}
		}
		return vo;
	}

	
	private static CampanhaVO transformarClientToVo(Campanha campanha){
		
		CampanhaVO vo = new CampanhaVO();
		BeanUtils.copyProperties(campanha, vo);
		return vo;
	}


	@Override
	public  List<CampanhaAssociada> associarCampanhas(final Long socio, final List<CampanhaVO> lst) {
		List<CampanhaAssociada> campanhasAssociadas = lst.stream().map(c-> getCampanhaAssociada(socio, c.getId())).collect(Collectors.toList());
		
		List<CampanhaAssociada> listInserted = campanhaAssociadaService.salvarListAssociacao(campanhasAssociadas);
		
		return listInserted;
		
	}

	private CampanhaAssociada getCampanhaAssociada(Long socio, Long campnha){
		Socio entity = new Socio();
		entity.setId(socio);
		CampanhaAssociada campanhaAssociada = new CampanhaAssociada();
		campanhaAssociada.setCampanha(campnha);
		campanhaAssociada.setSocio(entity);
		return campanhaAssociada;
		
	}
	
	@Override
	public List<CampanhaAssociada> associarCampanhas(Socio socio, List<CampanhaVO> lst) {
		
		List<CampanhaAssociada> campanhasAssociadas = lst.stream().map(c -> new CampanhaAssociada(socio, c.getId())).collect(Collectors.toList());
		
		return  campanhaAssociadaService.salvarListAssociacao(campanhasAssociadas);
		
	}


	@Override
	public Socio findSocioById(Long id) {
		
		return service.findById(id);
	}
	
	







}
