/**
 * 
 */
package br.com.processo.campanha.exception;

/**
 * @author Vagner
 *
 */
public class CampanhaNewException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2595554474783324489L;

	public CampanhaNewException(){
		
	}
	
	public CampanhaNewException(String message){
		super(message);
	}
	
	public CampanhaNewException(Throwable cause) {
		super(cause);
	}

	public CampanhaNewException(String message, Throwable cause) {
		super(message, cause);
	}

	public CampanhaNewException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}



}
