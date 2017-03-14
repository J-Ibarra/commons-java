package br.com.crypto.commons.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtils {
	
	static Logger log = null;
	
	public static void errorLog(Class eClass, Throwable error, String message){
		
		log = LoggerFactory.getLogger(eClass);
		log.error(message, error);
		
	}
	
	public static void warningLog(Class eClass, String message){
		
		log = LoggerFactory.getLogger(eClass);
		log.warn(message);
		
	}
	
	public static <T> void infoLog(Class eClass, String message){
		
		log = LoggerFactory.getLogger(eClass);
		log.info(message);
		
	}
	

}
