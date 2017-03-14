package br.com.crypto.commons.poi;

import java.io.File;
import java.text.DateFormat;
import java.util.List;

public interface POIWriter {
	
	boolean writeFile();
	File writeTempFile();
	
	void setObjectArrayList(List<Object[]> objectArrayList);
	void setDateFormat(DateFormat dateFormat);
	void setHeaders(String[] headers);
	void setDestinationFile(File destinationFile);

}
