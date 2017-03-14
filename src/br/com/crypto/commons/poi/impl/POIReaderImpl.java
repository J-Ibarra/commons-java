package br.com.crypto.commons.poi.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.crypto.commons.poi.POIReader;

public class POIReaderImpl implements POIReader {

	private File file;
	private XSSFWorkbook workbook;
	private Workbook workbookOld;
	private List<List<Object[]>> linesAndSheets = new ArrayList<>();
	private DataFormatter formatter = new DataFormatter();

	@Override
	public boolean readFile() {

		try {

			FileInputStream fis = new FileInputStream(this.file);
			this.workbook = new XSSFWorkbook(fis);
			
			if(this.workbook.getNumberOfSheets()>0){

				for (int i = 0; i < this.workbook.getNumberOfSheets(); i++) {
					
					List<Object[]> currentSheet = new ArrayList<>();
					
					Sheet sheet = this.workbook.getSheetAt(i);
					
					for (int j = 0; j < sheet.getLastRowNum(); j++) {
						
						Row row = sheet.getRow(j);
						
						Object[] object = new Object[row.getLastCellNum()];
						
						for (int k = 0; k < row.getLastCellNum(); k++) {
							
							object[k] = formatter.formatCellValue(row.getCell(k));
							
						}
						
						currentSheet.add(object);
						
					}
					
					linesAndSheets.add(currentSheet);
					
				}
				
				
			}
				
			return true;

		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	@Override
	public boolean readOldFile() {

		try {

			try {
				this.workbookOld = WorkbookFactory.create(this.file);
			} catch (InvalidFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(this.workbookOld.getNumberOfSheets()>0){

				for (int i = 0; i < this.workbookOld.getNumberOfSheets(); i++) {
					
					List<Object[]> currentSheet = new ArrayList<>();
					
					Sheet sheet = this.workbookOld.getSheetAt(i);
					
					for (int j = 0; j < sheet.getLastRowNum(); j++) {
						
						Row row = sheet.getRow(j);
						
						if(row!=null){
						
							Object[] object = new Object[row.getLastCellNum()];
							
							for (int k = 0; k < row.getLastCellNum(); k++) {
								
								object[k] = formatter.formatCellValue(row.getCell(k));
								
							}
							
							currentSheet.add(object);
						
						}
						
					}
					
					linesAndSheets.add(currentSheet);
					
				}
				
				
			}
				
			return true;

		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public List<List<Object[]>> getColumns() {
		return linesAndSheets;
	}

	@Override
	public void setFile(File file) {
		this.file = file;
	}

	@Override
	public void close() {
		try {
			this.workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
