package br.com.crypto.commons.date;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public interface DateCalc {
	
	Integer businessDays(Calendar date1, Calendar date2, Integer endOfDayHour);
	List<Calendar> monthsBefore(Integer quantity);
	List<Calendar> monthsAfter(Integer quantity);
	Date addMinutes(int minutes, Date date);
	
}
