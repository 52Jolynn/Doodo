package com.laud.doodo.date;

import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

/**
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2012-9-22 下午2:17:16
 * @copyright: www.dreamoriole.com
 */
public class DateTest extends TestCase {
	private Date testDate = DateUtils.string2Date("2012-09-23 10:31:02",
			DateUtils.DEFAULT_DATE_PATTERN);

	public void testDate2String() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2012, Calendar.SEPTEMBER, 22, 9, 18, 1);
		String date = DateUtils.date2String(calendar.getTime());
		assertEquals("2012-09-22 09:18:01", date);

		date = DateUtils.date2String(calendar.getTime(), "yyyy-MM-dd");
		assertEquals("2012-09-22", date);
	}

	public void testAddYear() {
		Date date = DateUtils.addYear(testDate, 1);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		assertEquals(2013, calendar.get(Calendar.YEAR));
	}

	public void testAddMonth() {
		Date date = DateUtils.addMonth(testDate, 1);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		assertEquals(Calendar.OCTOBER, calendar.get(Calendar.MONTH));

		date = DateUtils.addMonth(testDate, 4);
		calendar.setTime(date);
		assertEquals(Calendar.JANUARY, calendar.get(Calendar.MONTH));
		assertEquals(2013, calendar.get(Calendar.YEAR));
	}

	public void testAddDay() {
		Date date = DateUtils.addDay(testDate, 1);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		assertEquals(24, calendar.get(Calendar.DAY_OF_MONTH));

		date = DateUtils.addDay(testDate, 360);
		calendar.setTime(date);
		assertEquals(2013, calendar.get(Calendar.YEAR));
	}
}
