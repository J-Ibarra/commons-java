package br.com.crypto.commons.report.impl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import br.com.crypto.commons.datafile.DataFileWriter;
import br.com.crypto.commons.datafile.impl.DataFileWriterImpl;
import br.com.crypto.commons.date.impl.FormatUtils;
import br.com.crypto.commons.poi.POIWriter;
import br.com.crypto.commons.poi.impl.POIWriterImpl;
import br.com.crypto.commons.report.DataReport;

public abstract class DataReportAbstract implements DataReport {

	private List<Object[]> result;
	private File fileToSend;
	
	public DataReportAbstract() {
		this.prepare();
	}

	@Override
	public void prepare() {
		this.result = this.getConnection().findObjectArrayList(this.getQuery(), this.getQueryParameters());
	}
	
	@Override
	public File getXLSX() {
		
		POIWriter poiw = new POIWriterImpl();
		poiw.setDateFormat(FormatUtils.getBrazilianPatternWithHour());
		poiw.setHeaders(this.getHeaders());
		poiw.setObjectArrayList(this.result);
		
		this.fileToSend = poiw.writeTempFile();
		return this.fileToSend;
		
	}
	
	@Override
	public List<Object[]> getObjectArrayList() {
		return this.result;
	}
	
	@Override
	public File getCSV() {
		
		DataFileWriter dfw = new DataFileWriterImpl();
		dfw.setColumnSeparator(";");
		dfw.setLinesAndColumnsToWrite(result);
		dfw.setHeaders(this.getHeaders());
		dfw.setDateFormat(FormatUtils.getBrazilianPatternWithHour());
		dfw.setCharset(Charset.forName("Cp1252"));
		
		try {
			this.fileToSend = dfw.write(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return this.fileToSend;
		
	}

}
