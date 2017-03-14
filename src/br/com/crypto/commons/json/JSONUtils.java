package br.com.crypto.commons.json;

import java.io.Writer;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.json.JsonWriter;

public class JSONUtils {
	
	static XStream xstream = new XStream(new JettisonMappedXmlDriver(){
		
		public HierarchicalStreamWriter createWriter(Writer writer) {
            return new JsonWriter(writer, JsonWriter.DROP_ROOT_MODE);
        }
		
	});
	
	public static String toJson(Object object){
		xstream.setMode(XStream.NO_REFERENCES);
		String json = xstream.toXML(object);
        return json;
	}
	
	public static <T> T toObject(String json, Class<T> aClass){
		xstream.setMode(XStream.NO_REFERENCES);
		return (T) xstream.fromXML(json);
		
	}

}
