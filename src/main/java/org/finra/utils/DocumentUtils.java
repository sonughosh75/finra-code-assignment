package org.finra.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class DocumentUtils {
	private static final Logger LOG = Logger.getLogger(DocumentUtils.class);

	public static Date convertStringToDate(String dateStr) {
		DateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");
		Date convertedDate = null;
		try {
			convertedDate = formatter.parse(dateStr);
		} catch (ParseException parseException) {
			LOG.error("Error parsing the Date string");
			throw new RuntimeException("Error parsing the Date string");
		}
		return convertedDate;
	}

}
