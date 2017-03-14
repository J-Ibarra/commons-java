package br.com.crypto.commons.string;

import java.text.Normalizer;

public class StringUtils {
	
	public static String removeSpecialCharacters(String str){
		
		if(str==null){
			return null;
		}
		
		str = Normalizer.normalize(str, Normalizer.Form.NFD);
		str = str.replaceAll("[^\\p{ASCII}]", "");
		str = str.replaceAll("'", "");
		str = str.replaceAll(":", "");
		str = str.replaceAll(".", "");
		str = str.replaceAll(";", "");
		str = str.replaceAll("*", "");
		str = str.replaceAll("!", "");
		str = str.replaceAll("@", "");
		str = str.replaceAll("#", "");
		str = str.replaceAll("$", "");
		str = str.replaceAll("%", "");
		str = str.replaceAll("ˆ", "");
		str = str.replaceAll("&", "");
		str = str.replaceAll("(", "");
		str = str.replaceAll(")", "");
		str = str.replaceAll("-", "");
		str = str.replaceAll("_", "");
		str = str.replaceAll("+", "");
		str = str.replaceAll("=", "");
		str = str.replaceAll("\"", "");
		str = str.replaceAll("˜", "");
		str = str.replaceAll("~", "");
		str = str.replaceAll("^", "");
		str = str.replaceAll("Ü", "");
		str = str.replaceAll("ü", "");
		str = str.replaceAll(",", "");
		str = str.replaceAll("?", "");
		str = str.replaceAll("<", "");
		str = str.replaceAll(">", "");
		
		return str;
		
	}
	
	public static String safeSubstring(String str, int prefixIndex, int sulfixIndex){
		
		if(str==null){
			return null;
		}
		
		if (str.length() > prefixIndex && str.length() > sulfixIndex) {

			return str.substring(prefixIndex, sulfixIndex);

		}

		return str;
		
	}
	
	public static String safeSubstring(String str, int sulfixIndex){
		
		if(str==null){
			return null;
		}
		
		if (str.length() > sulfixIndex) {

			return str.substring(0, sulfixIndex);

		}

		return str;
		
	}
	
	public static Long getOnlyNumericDigits(String str){
		
		String valueOf = str.replaceAll("\\D+","");
		
		if(valueOf.length()>0){
			return Long.valueOf(valueOf);
		}else{
			return null;
		}
		
		
		
	}
	
	public static String getOnlyAlphaCharacters(String str){
		
		if(str==null){
			return null;
		}
		
		return str.replaceAll("\\P{L}+", "");
		
	}
	
	public static String object2String(Object object){
		return object!=null?object.toString():null;
	}

}
