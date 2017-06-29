/**
 * 
 */
package br.com.processo.campanha.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Vagner
 *
 */
@RestControllerAdvice
public class ExceptionResolver {
	
	public static final int CODE_CAMPANHA_NOT_FOUND = 200;
	public static final int CODE_CAMPANHA_NOT_INSERTED=204;
	
	@ExceptionHandler(CampanhaNotFoundException.class)
    public ResponseMsg socioNotFoundExceptionHandler(CampanhaNotFoundException ex) {
		ResponseMsg responseMsg = new ResponseMsg(ex.getMessage(), CODE_CAMPANHA_NOT_FOUND);
		return responseMsg;
    }
	
	@ExceptionHandler(BusinessException.class)
	public ResponseMsg socioNotInserted(BusinessException ex){
		ResponseMsg responseMsg = new ResponseMsg(ex.getMessage(), HttpStatus.NOT_FOUND.value());
		return responseMsg;
	}
	
	


}
