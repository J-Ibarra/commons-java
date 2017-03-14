package br.com.crypto.commons.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

public class FileUtils {
	
	@Test
	public void test(){
		
		getRecursiveFileList(new File("/Users/reginaldo/Desktop")).forEach(f -> System.out.println(f.toFile().getName()));
		
	}
	
	public Stream<Path> getRecursiveFileList(File directory){
			
		try {
			return Files.walk(directory.toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}

}
