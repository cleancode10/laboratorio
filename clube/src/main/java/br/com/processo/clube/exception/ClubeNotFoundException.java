/**
 * 
 */
package br.com.processo.clube.exception;

/**
 * @author Vagner
 *
 */
public class ClubeNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1415919646102444396L;
	
	public ClubeNotFoundException(){
		
	}
	
	public ClubeNotFoundException(String message){
		super(message);
	}
	
	public ClubeNotFoundException(Throwable cause) {
		super(cause);
	}

	public ClubeNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ClubeNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}


}
