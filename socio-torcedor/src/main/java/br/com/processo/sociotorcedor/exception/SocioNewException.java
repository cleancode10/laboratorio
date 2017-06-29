/**
 * 
 */
package br.com.processo.sociotorcedor.exception;

/**
 * @author Vagner
 *
 */
public class SocioNewException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2595554474783324489L;

	public SocioNewException(){
		
	}
	
	public SocioNewException(String message){
		super(message);
	}
	
	public SocioNewException(Throwable cause) {
		super(cause);
	}

	public SocioNewException(String message, Throwable cause) {
		super(message, cause);
	}

	public SocioNewException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}



}
