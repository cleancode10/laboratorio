/**
 * 
 */
package br.com.processo.sociotorcedor.converter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * @author developer
 *
 */
public class CustomDateSerializer extends StdSerializer<Date> {
	
	   /**
	 * 
	 */
	private static final long serialVersionUID = -5817018596615110738L;
	private SimpleDateFormat formatter 
	      = new SimpleDateFormat("dd-MM-yyyy");
	 
	    public CustomDateSerializer() {
	        this(null);
	    }
	 
	    public CustomDateSerializer(Class t) {
	        super(t);
	    }
	     
	    @Override
	    public void serialize (Date value, JsonGenerator gen, SerializerProvider arg2)
	      throws IOException, JsonProcessingException {
	        gen.writeString(formatter.format(value));
	    }

}
