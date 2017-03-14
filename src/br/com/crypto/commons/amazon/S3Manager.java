package br.com.crypto.commons.amazon;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public interface S3Manager {
	
	boolean delete(String amazonPath);
	boolean send(File fileToSend, String amazonPath);
	InputStream download(String amazonPath);
	List<String> listNames(String amazonPath);
	List<File> listFiles(String amazonPath);
	File getUniqueFileInsideDirectory(String amazonPath);
	String getAwsAccessKey();
	String getAwsSecretKey();
	String getBucket();
	

}
