package br.com.crypto.commons.poi;

import java.io.File;
import java.util.List;

public interface POIReader {
	
	boolean readFile();
	boolean readOldFile();
	
	List<List<Object[]>> getColumns();
	
	void setFile(File file);
	void close();

}

