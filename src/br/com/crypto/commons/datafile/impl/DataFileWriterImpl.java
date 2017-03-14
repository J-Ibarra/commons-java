package br.com.crypto.commons.datafile.impl;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;

import br.com.crypto.commons.charset.CharsetUtils;
import br.com.crypto.commons.datafile.DataFileWriter;

public class DataFileWriterImpl implements DataFileWriter {

	private File file;
	private String columnSeparator;

	private List<String> lines = null;
	private List<Object[]> linesAndColumns = null;
	private String uniqueText = null;
	private String[] headers;
	private DateFormat dateFormat;
	private Charset charset;

	@Override
	public File write(Boolean append) throws IOException {

		if(this.file != null){
			if(!this.file.exists()){
				this.file.createNewFile();
			}
		}else{
			this.file = File.createTempFile(UUID.randomUUID().toString(), ".tmp");
		}
		
		String joinHeader = String.join(this.columnSeparator, this.headers);

		if (this.uniqueText != null) {

			RandomAccessFile stream = new RandomAccessFile(this.file, "rw");
			FileChannel channel = stream.getChannel();

			this.uniqueText = joinHeader.concat(this.uniqueText).concat("\r\n");
			
			byte[] strBytes = this.uniqueText.getBytes(this.charset!=null?this.charset:CharsetUtils.getStringCharset(this.uniqueText));
			ByteBuffer buffer = ByteBuffer.allocate(strBytes.length);
			buffer.put(strBytes);
			buffer.flip();
			if (append) {
				channel.write(buffer, channel.size());
			} else {
				channel.write(buffer);
			}
			stream.close();
			channel.close();

		} else if (this.lines != null) {
			
			this.lines.add(0, joinHeader);

			FileUtils.writeLines(this.file, this.charset!=null?this.charset.displayName():CharsetUtils.getStringCharset(this.lines.get(0)).displayName(), lines, "\r\n", append ? true : false);

		} else if (this.linesAndColumns != null) {
			
			this.linesAndColumns.add(0, headers);

			if (this.columnSeparator != null) {
				
				linesAndColumns.forEach(columns -> {
					
					String[] columnsStringValue = new String[columns.length];
					
					for (int i = 0; i < columns.length; i++) {
						
						if(columns[i] != null && columns[i] instanceof Date){
							columnsStringValue[i] = this.dateFormat.format(columns[i]);
						}else{
							columnsStringValue[i] = columns[i]!=null?columns[i].toString():"";
						}
						
					}
					
					try {
						FileUtils.write(this.file, String.join(this.columnSeparator, columnsStringValue).concat("\r\n"), this.charset!=null?this.charset:CharsetUtils.getStringCharset(columnsStringValue[0]) ,true);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				});
				
			}else{
				
				System.out.println("Column separator not denified");
				
			}
			
		}
		
		return this.file;

	}

	@Override
	public void setPath(File file) {
		this.file = file;

	}

	@Override
	public void setLinesAndColumnsToWrite(List<Object[]> linesAndColumns) {
		this.linesAndColumns = linesAndColumns;

	}

	@Override
	public void setLinesToWrite(List<String> lines) {
		this.lines = lines;

	}

	@Override
	public void setStringToWrite(String uniqueText) {
		this.uniqueText = uniqueText;

	}

	@Override
	public void setColumnSeparator(String columnSeparator) {
		this.columnSeparator = columnSeparator;

	}

	@Override
	public void setHeaders(String[] headers) {
		this.headers = headers;
	}

	@Override
	public void setDateFormat(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}
	
	@Override
	public void setCharset(Charset charset) {
		this.charset = charset;
	}

}
