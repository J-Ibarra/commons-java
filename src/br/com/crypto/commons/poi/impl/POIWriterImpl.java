package br.com.crypto.commons.poi.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import br.com.crypto.commons.date.impl.FormatUtils;
import br.com.crypto.commons.poi.POIWriter;

public class POIWriterImpl implements POIWriter {

	private SXSSFWorkbook workbook;
	private Sheet sheet;
	
	private List<Object[]> objectArrayList;
	private DateFormat dateFormat;
	private String[] headers;
	private File destinationFile;

	@Override
	public boolean writeFile() {

		try {

			this.workbook = new SXSSFWorkbook();
			this.sheet = workbook.createSheet();
			
			Row currentRowHeader = this.sheet.createRow(sheet.getLastRowNum() + 1);

			for (int i = 0; i < this.headers.length; i++) {

				Cell currentCell = currentRowHeader.createCell(i);
				currentCell.setCellType(Cell.CELL_TYPE_STRING);
				currentCell.setCellValue(this.headers[i]);

			}

			for (Object[] objects : this.objectArrayList) {

				Row currentRow = this.sheet.createRow(this.sheet.getLastRowNum() + 1);

				for (int i = 0; i < objects.length; i++) {

					Cell currentCell = currentRow.createCell(i);
					currentCell.setCellType(POIUtils.getCellType(objects[i]));
					
					if(objects[i] instanceof Date){
						currentCell.setCellValue(dateFormat!=null?dateFormat.format(objects[i]):FormatUtils.getBrazilianPatternWithHour().format(objects[i]));
					}else{
						currentCell.setCellValue(objects[i]!=null?String.valueOf(objects[i]):"");
					}

				}

			}

			OutputStream os = new FileOutputStream(this.destinationFile);
			this.workbook.write(os);
			os.close();
			this.workbook.close();
			
			return true;

		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
			return false;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public File writeTempFile() {
		
		try {
			
			File temp = File.createTempFile(UUID.randomUUID().toString(), ".xlsx");
			this.setDestinationFile(temp);
			this.writeFile();
			
			return temp;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return null;
	}

	public void setObjectArrayList(List<Object[]> objectArrayList) {
		this.objectArrayList = objectArrayList;
	}

	public void setDateFormat(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}

	public void setHeaders(String[] headers) {
		this.headers = headers;
	}

	public void setDestinationFile(File destinationFile) {
		this.destinationFile = destinationFile;
	}

}
