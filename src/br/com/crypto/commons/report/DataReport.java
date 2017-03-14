package br.com.crypto.commons.report;

import java.io.File;
import java.util.List;

import br.com.crypto.commons.connection.jdbc.JDBCConnection;

public interface DataReport {

	public String getQuery();
	public Object[] getQueryParameters();
	public JDBCConnection getConnection();
	public String[] getHeaders();
	public void prepare();
	public File getXLSX();
	public File getCSV();
	public List<Object[]> getObjectArrayList();
	
}
