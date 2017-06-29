/**
 * 
 */
package br.com.processo.sociotorcedor.converter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * @author developer
 *
 */
public class LocalDateSerializer extends JsonSerializer {

	 
	    public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		 	LocalDate dt = (LocalDate)value;
		 	gen.writeObject(dt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
	        //gen.writeString(dt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
	    }

		@Override
		public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers)
				throws IOException, JsonProcessingException {
			// TODO Auto-generated method stub
			
		}



}
