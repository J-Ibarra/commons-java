package br.com.crypto.commons.date;

import java.util.Calendar;

public interface Holiday {
	
	Boolean isHoliday(Calendar date);
	Calendar getCarnaval(Integer year);
	Calendar getCarnavalSegundaFeira(Integer year);
	Calendar getCorpusChristi(Integer year);
	Calendar getSextaFeiraPaixao(Integer year);
	Calendar getPascoa(Integer year);
	
}
