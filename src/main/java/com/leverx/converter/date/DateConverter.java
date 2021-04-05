package com.leverx.converter.date;

import static java.util.Date.from;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import lombok.NoArgsConstructor;

/** @author Andrei Yahorau */
@NoArgsConstructor
public final class DateConverter {

  public static LocalDate toLocalDate(final Date date) {
    return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
  }

  public static Date toDate(final LocalDate localDate) {
    return from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
  }
}
