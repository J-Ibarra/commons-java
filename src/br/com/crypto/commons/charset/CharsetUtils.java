package br.com.crypto.commons.charset;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Locale;

import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;

import br.com.crypto.commons.logger.LoggerUtils;
import br.com.crypto.commons.logger.helper.CommonsMessage;

public class CharsetUtils {

	public static Charset getFileCharset(File file) {

		CharsetDetector detector = new CharsetDetector();

		try {
			
			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
			
			detector.setText(inputStream);
			
			CharsetMatch match = detector.detect();

			return Charset.forName(match.getName().toUpperCase(Locale.ENGLISH));
			
		} catch (IOException e) {
			
			LoggerUtils.errorLog(CharsetUtils.class, e, CommonsMessage.fileNotFoundException.toString());
			
		}
		
		return null;

	}

	public static Charset getStringCharset(String string) {
		
		CharsetDetector detector = new CharsetDetector();

		detector.setText(string.getBytes());

		CharsetMatch match = detector.detect();

		return Charset.forName(match.getName().toUpperCase(Locale.ENGLISH));
		
	}

}
