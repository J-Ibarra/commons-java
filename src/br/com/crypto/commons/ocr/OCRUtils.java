package br.com.crypto.commons.ocr;

import java.io.File;

import org.junit.Test;

import br.com.crypto.commons.logger.LoggerUtils;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class OCRUtils {
	
	public static void main(String[] args) {
		new OCRUtils().doOCR();
	}
	
	@Test
	public void test(){
		doOCR();
	}
	
	public void doOCR(){
		File imageFile = new File("/Users/reginaldo/Desktop/cred.png");
        ITesseract instance = new Tesseract();  // JNA Interface Mapping
        instance.setLanguage("/usr/local/Cellar/tesseract/3.04.01_1/share/tessdata/eng.traineddata");
//         ITesseract instance = new Tesseract1(); // JNA Direct Mapping

        try {
            String result = instance.doOCR(imageFile);
            System.out.println(result);
        } catch (TesseractException e) {
            LoggerUtils.errorLog(this.getClass(), e, "ops");
        }
	}

}
