package br.com.crypto.commons.ftp.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import br.com.crypto.commons.ftp.FTPConnection;
import br.com.crypto.commons.ftp.helper.ConnectionParameters;

public abstract class SFTPConnectionAbstract implements FTPConnection{

	private Session session = null;
	private Channel channel = null;
	private ChannelSftp channelSftp = null;
	private JSch jsch = null;

	public SFTPConnectionAbstract() {
		jsch = new JSch();
	}

	public boolean connect() {

		try {

			session = jsch.getSession(this.getUser(), this.getHost(), this.getPort());
			session.setPassword(this.getPassword());
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();
			
			return true;
			
		} catch (JSchException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean upload(String dir, File file) {

		try {
			
			this.openConnection();
			
			this.channelSftp.cd(dir);
			this.channelSftp.put(new FileInputStream(file), file.getName());
			this.disconnect();
			
		} catch (JSchException e) {
			e.printStackTrace();
			return false;
		} catch (SftpException e) {
			e.printStackTrace();
			return false;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;

	}
	
	public boolean upload(String dir, String name, File file) {

		try {
			
			this.openConnection();
			
			this.channelSftp.cd(dir);
			this.channelSftp.put(new FileInputStream(file), name);
			this.disconnect();
			
		} catch (JSchException e) {
			e.printStackTrace();
			return false;
		} catch (SftpException e) {
			e.printStackTrace();
			return false;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;

	}
	
	public InputStream download(String filePath){
	
		InputStream inputStream = null;
		
		try {
			
			this.openConnection();
			
			inputStream = this.channelSftp.get(filePath);
			this.disconnect();
			
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SftpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return inputStream;
		
	}
	
	private void openConnection() throws JSchException{
		
		this.channel = this.session.openChannel("sftp");
		this.channel.connect();
		this.channelSftp = (ChannelSftp) this.channel;
		
	}

	public boolean disconnect() {

		try {
			if (null != channelSftp && !channelSftp.isClosed())
				channelSftp.exit();
			channelSftp.disconnect();

			session.disconnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;

	}

}
