package br.com.processo.sociotorcedor.exception;

public class SocioNotFoundException extends RuntimeException {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8229402686478104992L;

	public SocioNotFoundException(){
		
	}
	
	public SocioNotFoundException(String message){
		super(message);
	}
	
	public SocioNotFoundException(Throwable cause) {
		super(cause);
	}

	public SocioNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public SocioNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}



}
