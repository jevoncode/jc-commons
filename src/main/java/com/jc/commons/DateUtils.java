package com.jc.commons;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public synchronized static Date parse(String dateStr) throws ParseException {
		return sdf.parse(dateStr);
	}

	public synchronized static String format(Date date) {
		return sdf.format(date);
	}
	 
}
