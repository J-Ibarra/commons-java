package br.com.crypto.commons.date.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import br.com.crypto.commons.date.DateCalc;
import br.com.crypto.commons.date.Holiday;

public class DateCalcImpl implements DateCalc {

	private Holiday holiday = new HolidayImpl();

	public Integer businessDays(Calendar date1, Calendar date2, Integer endOfDayHour) {

		Integer days = 0;

		if (null != date1) {

			if (null == date2) {
				date2 = Calendar.getInstance();
			}

			while (date2.after(date1)) {

				date1.add(Calendar.DAY_OF_MONTH, 1);
				if (date1.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && date1.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY && !holiday.isHoliday(date1)) {
					days++;
				}

			}

			if (null != endOfDayHour && date1.get(Calendar.HOUR_OF_DAY) > endOfDayHour) {

				days--;

			}

		}

		return days;

	}

	@Override
	public List<Calendar> monthsBefore(Integer quantity) {
		
		Integer count = 1;
		List<Calendar> lCalendar = new ArrayList<Calendar>();
		
		Calendar atual = Calendar.getInstance();
		
		lCalendar.add((Calendar) atual.clone());
		
		while (count <= quantity){
			
			atual.add(Calendar.MONTH, -1);
			
			lCalendar.add((Calendar) atual.clone());
			
			count++;
			
		}
		
		Collections.reverse(lCalendar);
		
		return lCalendar;
		
	}
	
	@Override
	public List<Calendar> monthsAfter(Integer quantity) {
		
		Integer count = 1;
		List<Calendar> lCalendar = new ArrayList<Calendar>();
		
		Calendar atual = Calendar.getInstance();
		
		lCalendar.add((Calendar) atual.clone());
		
		while (count <= quantity){
			
			atual.add(Calendar.MONTH, -1);
			
			lCalendar.add((Calendar) atual.clone());
			
			count++;
			
		}
		
		return lCalendar;
	}

	@Override
	public Date addMinutes(int minutes, Date date) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minutes);
		
		return calendar.getTime();
	}

}
