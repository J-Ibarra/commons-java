package br.com.crypto.commons.pdfbox.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import br.com.crypto.commons.pdfbox.PDFUtils;

public class PDFUtilsImpl implements PDFUtils {
	
	@Override
	public PDDocument getDocument(File pdf) {
		PDDocument doc = null;
		try {
			doc = PDDocument.load(pdf);
			doc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return doc;
	}

	@Override
	public boolean merge(List<File> pdfList, File destinationFile) {

		try {
			PDFMergerUtility ut = new PDFMergerUtility();
			for (File pdf : pdfList) {
				ut.addSource(pdf);
			}

			ut.setDestinationFileName(destinationFile.getAbsolutePath());
			ut.mergeDocuments(MemoryUsageSetting.setupTempFileOnly());

			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public int pages(File pdf) {

		PDDocument doc = getDocument(pdf);
		return doc!=null?doc.getNumberOfPages():0;
		
	}

	@Override
	public List<PDPage> getAllPages(File pdf) {
		
		PDDocument doc = getDocument(pdf);
			
		if(doc!=null){
			Iterator<PDPage> pages = doc.getPages().iterator();
			List<PDPage> pagesList = new ArrayList<>();
			
			pages.forEachRemaining(p -> pagesList.add(p));
			
			return pagesList;
		}
		return null;
			
	}

	@Override
	public PDPage getPage(File pdf, int index) {
		
		PDDocument doc = getDocument(pdf);
		return doc!=null?doc.getPage(index):null;
		
	}

}
