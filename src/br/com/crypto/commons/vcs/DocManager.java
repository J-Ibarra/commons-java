package br.com.crypto.commons.vcs;

import java.io.File;
import java.util.UUID;

import br.com.crypto.commons.amazon.S3Manager;

public interface DocManager {
	
	S3Manager getS3Manager();
	UUID createFileVersion(File file, UUID uid);
	UUID createFileVersion(File file);
	File getLastFileVersion(UUID uid);

}
