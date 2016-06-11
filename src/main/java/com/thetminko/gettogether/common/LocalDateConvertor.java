package com.thetminko.gettogether.common;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by developer on 11/6/16.
 */
@Converter(autoApply = true)
public class LocalDateConvertor implements AttributeConverter<LocalDate, Date> {

  @Override
  public Date convertToDatabaseColumn(LocalDate locDate) {
    if(locDate != null){
      return Date.valueOf(locDate);
    }

    return null;
  }

  @Override
  public LocalDate convertToEntityAttribute(Date sqlDate) {
    if(sqlDate != null){
      return sqlDate.toLocalDate();
    }

    return null;
  }
}
