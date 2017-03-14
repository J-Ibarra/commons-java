package br.com.crypto.commons.compress;

import java.io.File;

public interface Compress {
	
	long zip(File directory, File zipFile);
	long unzip(File zipFile, File outputDirectory);

}
