package fpa.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public static String format(LocalDate date){
		if (date == null) {
			return "";
		}
		return date.format(DATE_FORMAT);
	}
}
