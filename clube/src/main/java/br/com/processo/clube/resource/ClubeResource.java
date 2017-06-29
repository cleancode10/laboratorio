/**
 * 
 */
package br.com.processo.clube.resource;

import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import br.com.processo.clube.entity.ClubeEntity;
import br.com.processo.clube.exception.ClubeNotFoundException;
import br.com.processo.clube.exception.ExceptionResolver;
import br.com.processo.clube.service.ClubeService;

/**
 * @author Vagner
 *
 */
@RestController
@Api(value = "clube") //Diz ao Swagger que esse é um endpoint e REST deve ser documentado
@RequestMapping(value="/clube")
public class ClubeResource {
	
	
	@Autowired
	private ClubeService service;
	
	@GetMapping(value="/api/clubes")
	public ResponseEntity<List<ClubeEntity>> listAll(){
		
		List<ClubeEntity> list = service.listAll();
		if(list.isEmpty()){
			throw new ClubeNotFoundException("Registro não encontrado");
		}
		return ResponseEntity.ok(list);
	}
	
	@ApiOperation(value="consulta clube pelo Id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, response=ClubeEntity.class ,message = "Pesquisa realizada com sucesso"),
			@ApiResponse(code = ExceptionResolver.CODE_CLUBE_NOT_FOUND, message = "Sem resultado")
	})
	@GetMapping(value="/api/find/{id}")
	public ResponseEntity<ClubeEntity> findOneById(@PathVariable(name="id", required=true) Long id){

		ClubeEntity entity = service.findOne(id);
		if(entity == null)
			throw new ClubeNotFoundException("Registro não encontrado");
		
		return ResponseEntity.ok(entity);
		
	}

	@ApiOperation(value="consulta clube pelo nome")
	@ApiResponses(value = {
			@ApiResponse(code = 200, response=ClubeEntity.class ,message = "Pesquisa realizada com sucesso"),
			@ApiResponse(code = ExceptionResolver.CODE_CLUBE_NOT_FOUND, message = "Sem resultado")
	})
	@GetMapping(value="/api/{nome}")
	public ResponseEntity<ClubeEntity> findByNome(@PathVariable(name="nome", required=true) String nome){
		
		ClubeEntity entity = service.findByNome(nome);
		if(entity == null){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(entity);
	}

	@ApiOperation(value="consulta clube pelos Ids")
	@ApiResponses(value = {
			@ApiResponse(code = 200, response=ClubeEntity.class ,message = "Pesquisa realizada com sucesso"),
			@ApiResponse(code = ExceptionResolver.CODE_CLUBE_NOT_FOUND, message = "Sem resultado")
	})
	@GetMapping(value="/api/ids/{lst}")
	public ResponseEntity<List<ClubeEntity>> findByListIds(@PathVariable(name="lst") List<String>lst){
		List<ClubeEntity> list = service.findByIdIn(lst.stream().map(s -> Long.valueOf(s)).collect(Collectors.toList()));
		if(list.isEmpty()){
			throw new ClubeNotFoundException("Resgistro não encontrado");
		}
		return ResponseEntity.ok(list);
		
	}
	
	@ApiOperation(value="consulta clube pelo nome")
	@ApiResponses(value = {
			@ApiResponse(code = 200, response=ClubeEntity.class ,message = " com sucesso"),
			@ApiResponse(code = ExceptionResolver.CODE_CLUBE_NOT_FOUND, message = "Sem resultado")
	})
	@PostMapping(value="/api/novo")
	public ResponseEntity<ClubeEntity> salvarClube(@RequestBody ClubeEntity entity){
		
		ClubeEntity inserted = service.salvarClube(entity);
		
		if(inserted == null){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(inserted);
	}

	@PutMapping(value="/api/atualizar")
	public ResponseEntity<ClubeEntity> atualizarClube(@RequestBody ClubeEntity entity){
		ClubeEntity updated = service.salvarClube(entity);
		
		if(updated == null){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(updated);
		
	}
	
	@DeleteMapping(value="/api/excluir")
	public ResponseEntity<Void> excluirClube(@PathVariable(name = "id", required = true) Long id) {

		try {
			service.excluirClube(id);
			return ResponseEntity.noContent().build();
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}	

}
