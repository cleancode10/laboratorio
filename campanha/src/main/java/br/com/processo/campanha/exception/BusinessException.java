/**
 * 
 */
package br.com.processo.campanha.exception;

/**
 * @author Vagner
 *
 */
public class BusinessException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6162960288422847084L;

	public BusinessException(){
		
	}
	
	public BusinessException(String message){
		super(message);
	}
	
	public BusinessException(Throwable cause) {
		super(cause);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}



}
