package br.com.crypto.commons.compress.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.junit.Test;

import br.com.crypto.commons.compress.Compress;

public class CompressImpl implements Compress {
	int BUFFER = 2048;

	@Override
	public long zip(File directory, File zipFile) {
		try {

			FileOutputStream dest = new FileOutputStream(zipFile);
			CheckedOutputStream checksum = new CheckedOutputStream(dest, new Adler32());
			ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(checksum));

			byte data[] = new byte[BUFFER];

			Stream<Path> files = Files.list(directory.toPath());
			files.filter(f -> !f.toFile().equals(zipFile)).filter(f -> !f.toFile().isDirectory()).forEach(f -> {

				try {
					
					FileInputStream fi = new FileInputStream(f.toFile());
					BufferedInputStream origin = new BufferedInputStream(fi, BUFFER);
					ZipEntry entry = new ZipEntry(f.toFile().getAbsolutePath());
					out.putNextEntry(entry);

					int count;
					while ((count = origin.read(data, 0, BUFFER)) != -1) {
						out.write(data, 0, count);
					}
					origin.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}

			});

			out.close();

			return checksum.getChecksum().getValue();

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public long unzip(File zipFile, File outputDirectory) {

		try {

			BufferedOutputStream dest = null;
			FileInputStream fis = new FileInputStream(zipFile);
			CheckedInputStream checksum = new CheckedInputStream(fis, new Adler32());
			ZipInputStream zis = new ZipInputStream(new BufferedInputStream(checksum));

			ZipEntry entry = null;
			while ((entry = zis.getNextEntry()) != null) {

				int count;
				byte data[] = new byte[BUFFER];

				FileOutputStream fos = new FileOutputStream(entry.getName());
				dest = new BufferedOutputStream(fos, BUFFER);
				while ((count = zis.read(data, 0, BUFFER)) != -1) {
					dest.write(data, 0, count);
				}
				dest.flush();
				dest.close();

			}

			zis.close();
			return checksum.getChecksum().getValue();

		} catch (Exception e) {

			e.printStackTrace();
			return -1;

		}

	}

}
