package br.com.crypto.commons.poi.impl;

import org.apache.poi.ss.usermodel.Cell;

public class POIUtils {
	
	public static int getCellType(Object object){
		
		if(object instanceof Number){
			return Cell.CELL_TYPE_NUMERIC;
		}else if(object instanceof String){
			return Cell.CELL_TYPE_STRING;
		}else if(object instanceof Boolean){
			return Cell.CELL_TYPE_BOOLEAN;
		}else if(object == null){
			return Cell.CELL_TYPE_BLANK;
		}
		
		return Cell.CELL_TYPE_ERROR;
		
	}

}
