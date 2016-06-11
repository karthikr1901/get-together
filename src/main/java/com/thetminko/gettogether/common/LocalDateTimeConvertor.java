package com.thetminko.gettogether.common;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by developer on 11/6/16.
 */
@Converter(autoApply = true)
public class LocalDateTimeConvertor implements AttributeConverter<LocalDateTime, Timestamp> {

  @Override
  public Timestamp convertToDatabaseColumn(LocalDateTime locDateTime) {
    if (locDateTime != null) {
      return Timestamp.valueOf(locDateTime);
    }

    return null;
  }

  @Override
  public LocalDateTime convertToEntityAttribute(Timestamp sqlTimestamp) {
    if (sqlTimestamp != null) {
      return sqlTimestamp.toLocalDateTime();
    }

    return null;
  }
}
