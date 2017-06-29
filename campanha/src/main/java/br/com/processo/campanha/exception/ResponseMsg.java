/**
 * 
 */
package br.com.processo.campanha.exception;

/**
 * @author Vagner
 *
 */
public class ResponseMsg {
	
	private String message;
	
	private Integer codigo;

	public ResponseMsg() {
	}

	public ResponseMsg(String message) {
		this.message = message;
	}
	
	public ResponseMsg(String message, Integer codigo){
		this.message = message;
		this.codigo = codigo;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	

}
