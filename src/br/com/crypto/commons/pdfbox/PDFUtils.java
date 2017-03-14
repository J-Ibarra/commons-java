package br.com.crypto.commons.pdfbox;

import java.io.File;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public interface PDFUtils {
	
	boolean merge(List<File> pdfList, File destinationFile);
	int pages(File pdf);
	PDDocument getDocument(File pdf);
	List<PDPage> getAllPages(File pdf);
	PDPage getPage(File pdf, int index);

}
