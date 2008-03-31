/* $Id$
 * Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.util.date;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.junit.Test;

/**
 * This is the test-case for {@link DateUtil}.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 */
@SuppressWarnings("all")
public class Iso8601UtilTest {

  public Iso8601Util getIso8601Util() {

    return Iso8601Util.getInstance();
  }

  private void dump(Date date) {

    System.out.println(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(date));
  }

  public void checkCombined(String date) {

    Calendar calendar = getIso8601Util().parseCalendar(date);
    String newDate = getIso8601Util().formatDateTime(calendar);
    assertEquals(date, newDate);
  }

  @Test
  public void testExtendedFormat() {

    checkCombined("1999-12-31T23:59:59+01:00");
    checkCombined("2000-01-01T00:00:00+00:00");
    checkCombined("2000-01-01T00:00:00-02:00");
    checkCombined("2000-01-01T00:00:00-00:30");
    Calendar calendar = getIso8601Util().parseCalendar("2007-01-31T11:22:33Z");
    assertEquals(2007, calendar.get(Calendar.YEAR));
    assertEquals(1, calendar.get(Calendar.MONTH) + 1);
    assertEquals(31, calendar.get(Calendar.DAY_OF_MONTH));
    assertEquals(11, calendar.get(Calendar.HOUR_OF_DAY));
    assertEquals(22, calendar.get(Calendar.MINUTE));
    assertEquals(33, calendar.get(Calendar.SECOND));
    assertEquals(TimeZone.getTimeZone("UTC"), calendar.getTimeZone());
    Calendar newCalendar = Calendar.getInstance(Locale.GERMANY);
    newCalendar.setTime(calendar.getTime());
    String newString = getIso8601Util().formatDateTime(newCalendar);
    assertEquals("2007-01-31T12:22:33+01:00", newString);
  }

  @Test
  public void testNow() {

    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.MILLISECOND, 0);
    String formatted = getIso8601Util().formatDateTime(calendar);
    Calendar parsed = getIso8601Util().parseCalendar(formatted);
    assertEquals(formatted, getIso8601Util().formatDateTime(parsed));
    // ATTENTION: parsed and calendar may NOT be equal because the timezone
    // may have changed to the fixed UTC-Offset...
    assertEquals(calendar.getTimeInMillis(), parsed.getTimeInMillis());
    assertEquals(calendar.getTimeZone().getOffset(calendar.getTimeInMillis()), parsed.getTimeZone()
        .getOffset(parsed.getTimeInMillis()));
  }

}
