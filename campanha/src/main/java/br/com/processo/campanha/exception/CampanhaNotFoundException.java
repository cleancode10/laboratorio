package br.com.processo.campanha.exception;

public class CampanhaNotFoundException extends RuntimeException {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8229402686478104992L;

	public CampanhaNotFoundException(){
		
	}
	
	public CampanhaNotFoundException(String message){
		super(message);
	}
	
	public CampanhaNotFoundException(Throwable cause) {
		super(cause);
	}

	public CampanhaNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public CampanhaNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}



}
