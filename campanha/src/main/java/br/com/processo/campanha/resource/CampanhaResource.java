/**
 * 
 */
package br.com.processo.campanha.resource;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import br.com.processo.campanha.entity.Campanha;
import br.com.processo.campanha.exception.BusinessException;
import br.com.processo.campanha.exception.CampanhaNotFoundException;
import br.com.processo.campanha.exception.ExceptionResolver;
import br.com.processo.campanha.servico.CampanhaServico;


/**
 * @author Vagner
 *
 */
@RestController
@Api(value = "campanha") //Diz ao Swagger que esse é um endpoint e REST deve ser documentado
@RequestMapping(value="/campanha")
public class CampanhaResource {
	
	@Autowired
	private CampanhaServico service;
	
	@ApiOperation(value="consulta todos os Campanhas")
	@ApiResponses(value = {
			@ApiResponse(code = 200, response=Campanha.class ,message = "Pesquisa realizada com sucesso"),
			@ApiResponse(code = ExceptionResolver.CODE_CAMPANHA_NOT_FOUND, message = "Sem resultado")
	})

	@GetMapping(value="/api/campanhas")
	public ResponseEntity<List<Campanha>> listAll(){
		
		List<Campanha> list = service.findAll();
		if(list.isEmpty()){
			throw new CampanhaNotFoundException("Registro não encontrado");
		}
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(value="/api/ativas")
	public ResponseEntity<List<Campanha>> listCampanhaAtiva(){
		List<Campanha> list = service.findByDtFimGreaterThanEqual(LocalDate.now());
		if(list.isEmpty()){
			throw new CampanhaNotFoundException("Resgistro não encontrado");
		}
		return ResponseEntity.ok(list);

	}

	@GetMapping(value="/api/ativas/{dtAtual}")
	@ApiOperation(value="buscar campanhas ativas")
	@ApiResponses(value = {
			@ApiResponse(code = 200, response=Campanha.class ,message = "Consulta realizada com sucesso")
	})
	public ResponseEntity<List<Campanha>> listCampanhaAtivaByDtAtual(@PathVariable(value="dtAtual", required=true) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dtAtual){
		List<Campanha> list = service.findByDtFimGreaterThanEqual(dtAtual);
		if(list.isEmpty()){
			throw new CampanhaNotFoundException("Resgistro não encontrado");
		}
		return ResponseEntity.ok(list);

	}
	
	
	@GetMapping(value="/api/ids/{ids}")
	@ApiOperation(value="consulta Campanha pelo Ids")
	@ApiResponses(value = {
			@ApiResponse(code = 200, response=Campanha.class ,message = "Pesquisa realizada com sucesso"),
			@ApiResponse(code = ExceptionResolver.CODE_CAMPANHA_NOT_FOUND, message = "Sem resultado")
	})
	public ResponseEntity<List<Campanha>> findByIds(@ApiParam(value="List do Campanha", 
			required=true) @PathVariable(value="ids", required=true) List<String> ids){

		List<Campanha> list = service.findByIdIn(ids.stream().map(s -> Long.valueOf(s)).collect(Collectors.toList()));
		if(list.isEmpty()){
			throw new CampanhaNotFoundException("Resgistro não encontrado");
		}
		return ResponseEntity.ok(list);
		
	}
	
	
	@GetMapping(value="/api/id/{id}")
	@ApiOperation(value="consulta Campanha pelo Id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, response=Campanha.class ,message = "Pesquisa realizada com sucesso"),
			@ApiResponse(code = ExceptionResolver.CODE_CAMPANHA_NOT_FOUND, message = "Sem resultado")
	})
	public ResponseEntity<Campanha> findOneById(@ApiParam(value="Id do Campanha", 
			required=true) @PathVariable(value="id", required=true) Long id){

		Campanha entity = service.findById(id);
		if(entity == null)
			throw new CampanhaNotFoundException("Registro não encontrado");
		
		return ResponseEntity.ok(entity);
		
	}
	
	
	@ApiOperation(value="consulta pelo Nome")
	@ApiResponses(value = {
			@ApiResponse(code = 200, response=Campanha.class ,message = "Pesquisa realizada com sucesso"),
			@ApiResponse(code = ExceptionResolver.CODE_CAMPANHA_NOT_FOUND, message = "Sem resultado")
	})
	@GetMapping(value="/api/{nome}")
	public ResponseEntity<Campanha> findByNome(@ApiParam(value="nome do Campanha", 
			required=true) @PathVariable(value="nome", required=true) String nome){
		
		Campanha entity = service.findByNome(nome);
		if(entity == null){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(entity);
	}
	
	@ApiOperation(value="Inseri Campanha")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Campanha Inserido com sucesso"),
			@ApiResponse(code = ExceptionResolver.CODE_CAMPANHA_NOT_INSERTED, message = "Campanha não encontrado")
	})
	@PostMapping(value="/api/novo")
	public @ResponseBody Campanha salvarCampanha(@RequestBody Campanha entity){
		try {
			Campanha inserted = service.salvarCampanha(entity);
			return inserted;
		} catch (ResourceNotFoundException e) {
			throw new BusinessException("Registro não inserido");
		}
		
	}

	@ApiOperation(value="Atualizar Campanha")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Campanha atualizado com sucesso"),
			@ApiResponse(code = ExceptionResolver.CODE_CAMPANHA_NOT_INSERTED, message = "Campanha não atualizado")
	})
	@PutMapping(value="/api/atualizar")
	public ResponseEntity<Campanha> atualizarCampanha(@RequestBody Campanha entity){
		try {
			Campanha inserted = service.atualizarCampanha(entity);
			return ResponseEntity.ok(inserted);
		} catch (ResourceNotFoundException e) {
			throw new BusinessException("Registro não atualizado");
		}
		
	}
	
	@ApiOperation(value="Excluir Campanha")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Campanha excluido com sucesso"),
			@ApiResponse(code = ExceptionResolver.CODE_CAMPANHA_NOT_INSERTED, message = "Campanha não excluido")
	})
	@DeleteMapping(value="/api/excluir")
	public ResponseEntity<Void> excluirCampanha(@ApiParam(value="Id do Campanha", 
		required=true) @PathVariable("id") Long id) {

		try {
			service.excluirCampanha(id);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException e) {
			throw new CampanhaNotFoundException("Registro não encontrado"); 
			
		}
	}	
	
	
	

}
