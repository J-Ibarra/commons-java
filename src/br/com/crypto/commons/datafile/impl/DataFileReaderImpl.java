package br.com.crypto.commons.datafile.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import br.com.crypto.commons.charset.CharsetUtils;
import br.com.crypto.commons.datafile.DataFileReader;

public class DataFileReaderImpl implements DataFileReader {
	
	private LineIterator lineIterator;
	private Integer resultLines = 1000;
	private File file;
	private Map<Integer, Integer> tabbedColumns;
	private Set<Integer> keySet;
	private String columnSeparator;

	@Override
	public void readFile() {
		
		try {
			this.lineIterator = FileUtils.lineIterator(this.file, CharsetUtils.getFileCharset(this.file).displayName());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Integer getSize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getLines() {
	
		Integer counter = 0;
		List<String> nString = new ArrayList<>();
		
		while(this.lineIterator.hasNext()){
			nString.add(this.lineIterator.next());
			counter++;
			if(counter.equals(resultLines)){
				break;
			}
		}
		
		return nString;
		
	}

	@Override
	public List<String[]> getColumns() {
		
		Integer counter = 0;
		List<String[]> nString = new ArrayList<>();
		
		while(this.lineIterator.hasNext()){
			nString.add(this.lineIterator.next().split(this.columnSeparator));
			counter++;
			if(counter.equals(resultLines)){
				break;
			}
		}
		
		return nString;
		
	}
	
	@Override
	public List<String[]> getTabbedColumns() {
		
		Integer counter = 0;
		List<String[]> nString = new ArrayList<>();
		
		while(this.lineIterator.hasNext()){
			
			String[] colunas = new String[this.keySet.size()];
			String line = this.lineIterator.next();
			Integer insideCounter = 0;
			
			for(Integer key : this.keySet){
				if(line.length() >= this.tabbedColumns.get(key)){
					colunas[insideCounter] = line.substring(key, this.tabbedColumns.get(key));
					insideCounter++;
				}else{
					System.out.println("Invalid index on the line, will be ignored: " + line);
					colunas = null;
					break;
				}
			}
			
			if(colunas!=null){
				nString.add(colunas);
			}
			
			counter++;
			if(counter.equals(resultLines)){
				break;
			}
		}
		
		return nString;
		
	}

	@Override
	public Boolean hasNext() {
		return this.lineIterator.hasNext();
	}

	@Override
	public void close() {
		this.lineIterator.close();
	}

	@Override
	public void setTabbedColumns(Map<Integer, Integer> tabbedColumns) {
		this.tabbedColumns = tabbedColumns;
		this.keySet = this.tabbedColumns.keySet();
	}

	@Override
	public void setColumnSeparator(String columnSeparator) {
		this.columnSeparator = columnSeparator;
	}

	@Override
	public void setPath(File file) {
		this.file = file;
		
	}
	
}
