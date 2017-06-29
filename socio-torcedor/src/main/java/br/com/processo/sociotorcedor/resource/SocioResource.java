/**
 * 
 */
package br.com.processo.sociotorcedor.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import br.com.processo.sociotorcedor.business.SocioBusines;
import br.com.processo.sociotorcedor.client.Campanha;
import br.com.processo.sociotorcedor.client.Clube;
import br.com.processo.sociotorcedor.entity.CampanhaAssociada;
import br.com.processo.sociotorcedor.entity.Socio;
import br.com.processo.sociotorcedor.exception.BusinessException;
import br.com.processo.sociotorcedor.exception.ExceptionResolver;
import br.com.processo.sociotorcedor.exception.SocioNotFoundException;
import br.com.processo.sociotorcedor.vo.CampanhaVO;
import br.com.processo.sociotorcedor.vo.SocioVO;

/**
 * @author Vagner
 *
 */
@RestController
@Api(value = "socio") //Diz ao Swagger que esse é um endpoint e REST deve ser documentado
@RequestMapping(value="/socio")
public class SocioResource {
	
	
	public static final String END_POINT_CLUBE = "http://clube-service/clube";
	public static final String END_POINT_CAMPANHA = "http://campanha-service/campanha";
	public static final String END_POINT_LOCALHOST= "http://localhost:";
	
	@Autowired
	private SocioBusines business;


	@Autowired
	private RestTemplate restTemplate;
	
	@ApiOperation(value="consulta todos os socios")
	@ApiResponses(value = {
			@ApiResponse(code = 200, response=Socio.class ,message = "Pesquisa realizada com sucesso"),
			@ApiResponse(code = ExceptionResolver.CODE_SOCIO_NOT_FOUND, message = "Sem resultado")
	})

	@GetMapping(value="api/socios")
	public ResponseEntity<List<SocioVO>> listAll(){
		
		final List<Socio> list = business.listSocioAll();
		if(list.isEmpty()){
			throw new SocioNotFoundException("Registro não encontrado");
		}else{
			return ResponseEntity.ok(converterListaObjetos(list));
			
		}
	}
	

	private List<SocioVO> converterListaObjetos(final List<Socio> list){
		final List<SocioVO> socios = list.stream().map(s -> converterObjetos(s)).collect(Collectors.toList());
		final List<Clube> clubes = findClubeByIds(socios.stream().map(SocioVO::getIdClube).collect(Collectors.toList()));
		final Map<Long, String> clubeNomePorId = clubes.stream().collect(Collectors.toMap(Clube::getId, Clube::getNome));
		socios.forEach(s -> s.setClube(clubeNomePorId.get(s.getIdClube())));
		return socios;
	}
	private SocioVO converterObjetos(Socio entity){
		final List<Campanha> clientCampanhas = findCampanhaById(entity.getCampanhas().stream().map(CampanhaAssociada :: getCampanha).collect(Collectors.toList()));
		return business.converterEntityParaVO(entity, clientCampanhas);
	}
	
	private Clube findClubeBySocio(SocioVO socio){
		return findClubeById(socio.getIdClube());
	}
	
	
	@GetMapping(value="/api/id/{id}")
	@ApiOperation(value="consulta socio pelo Id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, response=Socio.class ,message = "Pesquisa realizada com sucesso"),
			@ApiResponse(code = ExceptionResolver.CODE_SOCIO_NOT_FOUND, message = "Sem resultado")
	})
	public ResponseEntity<SocioVO> findOneById(@ApiParam(value="Id do socio", 
			required=true) @PathVariable(value="id")final Long id){

		final Socio socio = business.findSocioById(id);
		if(socio == null)
			throw new SocioNotFoundException("Registro não encontrado");
		SocioVO vo = converterObjetos(socio);
		if(vo != null){
			final Clube clube = findClubeById(vo.getIdClube());
			if(clube != null)
				vo.setClube(clube.getNome());
		}
		
		return ResponseEntity.ok(vo);
		
	}
	
	@GetMapping(value="/api/email/{email}")
	@ApiOperation(value="consulta socio pelo email")
	@ApiResponses(value = {
			@ApiResponse(code = 200, response=SocioVO.class ,message = "Pesquisa realizada com sucesso"),
			@ApiResponse(code = ExceptionResolver.CODE_SOCIO_NOT_FOUND, message = "Sem resultado")
	})
	public ResponseEntity<SocioVO> findOneByEmail(@ApiParam(value="Email do socio", 
			required=true) @PathVariable(value="email")final String email){

		final SocioVO vo = business.findByEmail(email);
		if(vo == null)
			throw new SocioNotFoundException("E-mail não encontrado");
		else{
			final Clube clube = findClubeById(vo.getIdClube());
			vo.setClube(clube.getNome());
		}
			
		return ResponseEntity.ok(vo);
	}
	
	@ApiOperation(value="consulta pelo Nome")
	@ApiResponses(value = {
			@ApiResponse(code = 200, response=SocioVO.class ,message = "Pesquisa realizada com sucesso"),
			@ApiResponse(code = ExceptionResolver.CODE_SOCIO_NOT_FOUND, message = "Sem resultado")
	})
	@GetMapping(value="/api/nome/{nome}")
	public ResponseEntity<SocioVO> findByNome(@ApiParam(value="nome do socio", 
			required=true) @PathVariable(value="nome") final String nome){
		
		final SocioVO vo = business.findByNome(nome);
		if(vo == null)
			throw new SocioNotFoundException("Nome não encontrado");
		else{
			final Clube clube = findClubeById(vo.getIdClube());
			vo.setClube(clube.getNome());
		}

		return ResponseEntity.ok(vo);
	}
	
	@ApiOperation(value="Inseri Socio")
	@ApiResponses(value = {
			@ApiResponse(code = 200, response=SocioVO.class, message = "Socio Inserido com sucesso"),
			@ApiResponse(code = ExceptionResolver.CODE_SOCIO_NOT_INSERTED, message = "Socio não encontrado")
	})
	@PostMapping(value="/api/novo")
	public @ResponseBody SocioVO salvarSocio(@RequestBody final SocioVO vo){
		try {
			findClubeByNome(vo);
			final SocioVO inserted = business.salvarSocio(vo);
			inserted.setClube(vo.getClube());
			return inserted;
		} catch (ResourceNotFoundException e) {
			throw new BusinessException("Registro não inserido");
		}
		
	}

	@ApiOperation(value="Atualizar Socio")
	@ApiResponses(value = {
			@ApiResponse(code = 200, response=SocioVO.class, message = "Socio atualizado com sucesso"),
			@ApiResponse(code = ExceptionResolver.CODE_SOCIO_NOT_INSERTED, message = "Socio não atualizado")
	})
	@PutMapping(value="/api/atualizar")
	public ResponseEntity<SocioVO> atualizarSocio(@RequestBody final SocioVO vo){
		try {
			findClubeByNome(vo);
			final SocioVO updated = business.atualizarSocio(vo);
			updated.setClube(vo.getClube());
			return ResponseEntity.ok(updated);
		} catch (ResourceNotFoundException e) {
			throw new BusinessException("Registro não atualizado");
		}
		
	}
	
	@ApiOperation(value="Excluir Socio")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Socio excluido com sucesso"),
			@ApiResponse(code = ExceptionResolver.CODE_SOCIO_NOT_INSERTED, message = "Socio não excluido")
	})
	@DeleteMapping(value="/api/excluir")
	public ResponseEntity<Void> excluirSocio(@ApiParam(value="Id do socio", 
		required=true) @PathVariable("id") Long id) {

		try {
			business.excluirSocio(id);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException e) {
			throw new SocioNotFoundException("Registro não encontrado"); 
			
		}
	}
	
	private List<Clube> findClubeByIds(List<Long>lst){
		
		try{
			final String param = lst.stream().map(Object::toString).collect(Collectors.joining(","));
			List<Clube> list = Arrays.asList(restTemplate.getForEntity("http://clube-service/clube" + "/api/ids/" + param, Clube[].class).getBody());
			
			return list;
		}catch(ResourceAccessException |  HttpClientErrorException e){
			return null;
		}
	}
	
	private Clube findClubeById(Long id) {
		
		try{
			return restTemplate.getForObject(END_POINT_CLUBE +"/api/find/"+ id, Clube.class);	
		}catch(ResourceAccessException |  HttpClientErrorException e){
			return null;
		}
		
	}

	private void findClubeByNome(final SocioVO socio) {
		
		try{
			final Clube clube = restTemplate.getForObject(END_POINT_CLUBE +"/api/"+ socio.getClube(), Clube.class);
			socio.setIdClube(clube.getId());
			socio.setClube(clube.getNome());
		}catch(ResourceAccessException  |  HttpClientErrorException e){
			 return;
		}
		
	}
	
	private List<Campanha> findCampanhasAll(){
		try{
			ResponseEntity<Campanha[]> responseEntity = restTemplate.getForEntity(END_POINT_CAMPANHA + "/api/campanhas/" , Campanha[].class);
			return new ArrayList<>(Arrays.asList(responseEntity.getBody()));
			
		}catch(ResourceAccessException | HttpClientErrorException e){
			throw new BusinessException("Service de campanha não esta UP");
			
		}
		
	}
	
	private List<Campanha> findCampanhaAtiva(){
		try{
			ResponseEntity<Campanha[]> responseEntity = restTemplate.getForEntity(END_POINT_CAMPANHA + "/api/ids/" , Campanha[].class);
			return new ArrayList<>(Arrays.asList(responseEntity.getBody()));
			
		}catch(ResourceAccessException e){
			throw new BusinessException("Service de campanha não esta UP");
			
		}
		
	}
	
	private List<Campanha> findCampanhaById(final List<Long> ids){
		try{
			final String param = ids.stream().map(Object::toString).collect(Collectors.joining(","));
			List<Campanha> list = Arrays.asList(restTemplate.getForEntity("http://campanha-service/campanha" + "/api/ids/" + param, Campanha[].class).getBody());

			return list;
		}catch(ResourceAccessException |  HttpClientErrorException e){
			return null;
		}
		
	}
	
	@PostMapping(value="/api/cadastrar")
	public ResponseEntity<List<CampanhaVO>> cadastrarSocio(@RequestBody final SocioVO socio){

		final boolean isEmail = business.isEmail(socio.getEmail());
		if(isEmail) {
			final SocioVO socioExistente = business.findByEmail(socio.getEmail());
			if(socioExistente == null){
				findClubeByNome(socio);
				SocioVO socioInserted = business.salvarSocio(socio);
				final List<CampanhaVO> lstCampanhas = business.transformarClientToCampanhaVO(findCampanhasAll());
				
				return ResponseEntity.ok(lstCampanhas); 
			}else{
				throw new SocioNotFoundException("Cadastro ja foi efetuado");
			}
		}else{
			throw new SocioNotFoundException("Email não foi validado");
		}
	}

	@ApiOperation(value="Efetuar Associacao entre Socio e Campanha")
	@ApiResponses(value = {
			@ApiResponse(code = 200, response=SocioVO.class, message = "Associacao realizada com sucesso"),
			@ApiResponse(code = ExceptionResolver.CODE_SOCIO_NOT_INSERTED, message = "Associacao nao realizada")
	})
	@PostMapping(value="/api/associacao")
	public ResponseEntity<SocioVO> associarCampanha(@ApiParam(value="email", required=true)  @RequestParam final String email){
		
		final Socio entity = business.findSocioByEmail(email);
		if(entity != null && entity.getCampanhas() != null){
			throw new SocioNotFoundException("Não ha associação a ser feita" );
		}else{
			// buscar campanhas ativas
			final List<CampanhaVO> lstCampanhas = business.transformarClientToCampanhaVO(findCampanhasAll());
			// efetuar a associacao
			final List<CampanhaAssociada> campanhasAssociadas = business.associarCampanhas(entity.getId(), lstCampanhas);
			// converti entity de Socio e Client da Camapnha para VO
			final SocioVO socio = business.converterEntityParaVO(entity,
					findCampanhaById(campanhasAssociadas.stream().map(c -> c.getCampanha()).collect(Collectors.toList())));
			
			findClubeByNome(socio);
			return ResponseEntity.ok(socio);
		}	
	}
}
