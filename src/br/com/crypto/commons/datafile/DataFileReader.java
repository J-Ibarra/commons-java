package br.com.crypto.commons.datafile;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface DataFileReader {
	
	void readFile();
	
	Integer getSize();
	Boolean hasNext();
	List<String> getLines();
	List<String[]> getColumns();
	List<String[]> getTabbedColumns();
	
	void setTabbedColumns(Map<Integer, Integer> tabbedColumns);
	void setColumnSeparator(String columnSeparator);
	void setPath(File file);
	void close();

}
