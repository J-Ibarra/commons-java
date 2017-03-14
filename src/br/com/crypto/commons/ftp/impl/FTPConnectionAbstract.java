package br.com.crypto.commons.ftp.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;

import br.com.crypto.commons.ftp.FTPConnection;

public abstract class FTPConnectionAbstract implements FTPConnection {
	
	String user;
	String password;
	Integer port;
	String host;

	private FTPClient ftp = null;

	@Override
	public boolean connect() {
		try {
			this.ftp = new FTPClient();
			this.ftp.connect(this.getHost());
			this.ftp.setDefaultPort(this.getPort());
			this.ftp.login(this.getUser(), this.getPassword());
		} catch (SocketException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean upload(String dir, File file) {
		try {
			this.ftp.changeWorkingDirectory(dir);
			this.ftp.storeFile(file.getName(), new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public boolean upload(String dir, String name, File file) {
		try {
			this.ftp.changeWorkingDirectory(dir);
			this.ftp.storeFile(name, new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public InputStream download(String filePath) {
		
		try {
			return this.ftp.retrieveFileStream(filePath);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	public boolean disconnect() {

		try {
			ftp.logout();
			ftp.disconnect();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	

}
