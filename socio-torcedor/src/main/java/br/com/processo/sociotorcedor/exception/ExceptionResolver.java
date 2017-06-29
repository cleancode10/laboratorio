/**
 * 
 */
package br.com.processo.sociotorcedor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Vagner
 *
 */
@RestControllerAdvice
public class ExceptionResolver {
	
	public static final int CODE_SOCIO_NOT_FOUND = 200;
	public static final int CODE_SOCIO_NOT_INSERTED=204;
	
	@ExceptionHandler(SocioNotFoundException.class)
    public ResponseMsg socioNotFoundExceptionHandler(SocioNotFoundException ex) {
		ResponseMsg responseMsg = new ResponseMsg(ex.getMessage(), CODE_SOCIO_NOT_FOUND);
		return responseMsg;
    }
	
	@ExceptionHandler(BusinessException.class)
	public ResponseMsg socioNotInserted(BusinessException ex){
		ResponseMsg responseMsg = new ResponseMsg(ex.getMessage(), HttpStatus.NOT_FOUND.value());
		return responseMsg;
	}
	
	


}
