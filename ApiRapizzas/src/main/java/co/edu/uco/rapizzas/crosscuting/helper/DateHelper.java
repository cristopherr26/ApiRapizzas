package co.edu.uco.rapizzas.crosscuting.helper;

import java.time.LocalDate;

public class DateHelper {
	
	private static final LocalDate DATE_DEFAULT = LocalDate.of(2500, 12, 31);
	
	private DateHelper() {
		
	}
	
	public static LocalDate getDefault() {
		return DATE_DEFAULT;
	}
	
	public static LocalDate getDefault(final LocalDate date) {
		return ObjectHelper.getDefault(date, getDefault());
	}
	
	public static boolean isDefaultDate(final LocalDate value) {
	    if (value == null) {
	        return true;
	    }
	    return getDefault().equals(value);
	}


}
