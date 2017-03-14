package br.com.crypto.commons.vcs.impl;

import java.io.File;
import java.text.ParseException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import br.com.crypto.commons.date.impl.FormatUtils;
import br.com.crypto.commons.vcs.DocManager;

public abstract class DocManagerAbstract implements DocManager{
	
	@Override
	public UUID createFileVersion(File file, UUID uid) {

		String fullPath = uid.toString().concat(File.separator).concat(FormatUtils.getFullTimeIdentifier().format(new Date())).concat(File.separator).concat(file.getName());
		this.getS3Manager().send(file, fullPath);
		return uid;
				
	}
	
	@Override
	public UUID createFileVersion(File file) {
		
		UUID uid = UUID.randomUUID();

		String fullPath = uid.toString().concat(File.separator).concat(FormatUtils.getFullTimeIdentifier().format(new Date())).concat(File.separator).concat(file.getName());
		this.getS3Manager().send(file, fullPath);
		return uid;
				
	}

	@Override
	public File getLastFileVersion(UUID uid) {
		
		String fullPath = uid.toString();

		List<String> versions = this.getS3Manager().listNames(fullPath);
		
		versions.sort(new Comparator<String>() {
			
			@Override
			public int compare(String o1, String o2) {
				try {
					return FormatUtils.getFullTimeIdentifier().parse(o1).before(FormatUtils.getFullTimeIdentifier().parse(o2))?1:0;
				} catch (ParseException e) {
					e.printStackTrace();
					return 0;
				}
			}
			
		});
		
		fullPath = uid.toString().concat(File.separator).concat(versions.get(versions.size()-1)).concat(File.separator);
		
		return this.getS3Manager().getUniqueFileInsideDirectory(fullPath);
		
		
	}
	
	

}
