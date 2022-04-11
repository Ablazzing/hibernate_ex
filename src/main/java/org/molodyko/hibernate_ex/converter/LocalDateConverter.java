package org.molodyko.hibernate_ex.converter;

import javax.persistence.AttributeConverter;
import java.sql.Date;
import java.time.LocalDate;


public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate localDate) {
        return Date.valueOf(localDate);
    }

    @Override
    public LocalDate convertToEntityAttribute(Date date) {
        return date.toLocalDate();
    }


}
