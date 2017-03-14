package br.com.crypto.commons.xml;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;

public class XMLUtils {
	
	private static XStream xstream = null;
	
	@Test
	public void test(){
		System.out.println(toObject(toXML(new Object()), Object.class));
	}
	
	public XMLUtils() {
		xstream = new XStream();
	}
	
	public static String toXML(Object object){
		
		String xml = xstream.toXML(object);
		return xml;
		
	}
	
	public static <T> T toObject(String xml, Class<T> aClass){
		
		return (T) xstream.fromXML(xml);
		
	}

}
