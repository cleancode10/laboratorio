/**
 * 
 */
package br.com.processo.clube.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Vagner
 *
 */
@RestControllerAdvice
public class ExceptionResolver {
	
	public static final int CODE_CLUBE_NOT_FOUND = 200;
	
	@ExceptionHandler(ClubeNotFoundException.class)
    public ResponseMsg clubeNotFoundHandler(ClubeNotFoundException ex) {
		ResponseMsg responseMsg = new ResponseMsg(ex.getMessage(), CODE_CLUBE_NOT_FOUND);
		return responseMsg;
    }


}
