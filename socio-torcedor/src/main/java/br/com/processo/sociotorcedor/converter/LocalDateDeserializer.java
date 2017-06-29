/**
 * 
 */
package br.com.processo.sociotorcedor.converter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * @author developer
 *
 */
@SuppressWarnings("rawtypes")
public class LocalDateDeserializer extends JsonDeserializer {


	
    @Override
    public LocalDate deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        return LocalDate.parse(parser.getValueAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
