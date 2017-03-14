package br.com.crypto.commons.date.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class FormatUtils {
	
	public static DateFormat getddMMyyyyHHmm(){
		return new SimpleDateFormat("ddMMyyyyHHmm");
	}
	
	public static DateFormat getddMMyyyy(){
		return new SimpleDateFormat("ddMMyyyy");
	}
	
	public static DateFormat getyyyyMMddDashed(){
		return new SimpleDateFormat("yyyy-MM-dd");
	}
	
	public static DateFormat getSqlServer(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	}
	
	public static DateFormat getBrazilianPatternWithHour(){
		return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	public static DateFormat getBrazilianPatternWithoutHour(){
		return new SimpleDateFormat("dd/MM/yyyy");
	}
	
	public static DateFormat getFullTimeIdentifier(){
		return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS");
	}

	public static DateFormat getMySQL() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}

}
