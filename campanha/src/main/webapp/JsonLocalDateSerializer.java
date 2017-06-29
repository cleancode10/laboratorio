/**
 * 
 */
package br.com.processo.sociotorcedor.converter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * @author developer
 *
 */
@Component
public class JsonLocalDateSerializer extends JsonSerializer<LocalDate> {

	@Override

	public void serialize(LocalDate date, JsonGenerator gen, SerializerProvider provider)

	throws IOException, JsonProcessingException {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate = date.format(formatter);

		gen.writeString(formattedDate);

	}

}
