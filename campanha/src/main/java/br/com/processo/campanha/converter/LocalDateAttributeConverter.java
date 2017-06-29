/**
 * 
 */
package br.com.processo.campanha.converter;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author developer
 * Converter criado devido o jpa 2.1  nao ter suporte para api de data do java 8
 *
 */

@Converter(autoApply=true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Date> {
	
	@Override
	public Date convertToDatabaseColumn(LocalDate locDate) {
		return (locDate == null ? null : Date.valueOf(locDate));
	}

	@Override
	public LocalDate convertToEntityAttribute(Date sqlDate) {
		return (sqlDate == null ? null : sqlDate.toLocalDate());
	}

}
