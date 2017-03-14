package br.com.crypto.commons.datafile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.util.List;

public interface DataFileWriter {
	
	File write(Boolean append) throws IOException;
	
	void setPath(File file);
	void setLinesAndColumnsToWrite(List<Object[]> linesAndColumns);
	void setLinesToWrite(List<String> lines);
	void setStringToWrite(String uniqueText);
	void setColumnSeparator(String columnSeparator);
	void setHeaders(String[] headers);
	void setDateFormat(DateFormat dateFormat);
	void setCharset(Charset charset);
	
}
