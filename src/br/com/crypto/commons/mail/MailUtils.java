package br.com.crypto.commons.mail;

import java.io.File;
import java.util.List;

import javax.mail.Authenticator;

public interface MailUtils {
	
	boolean send(String subject, String html, String[] to, String[] cc, String[] bcc, List<File> attachments);
	String getHostName();
	int getSmtpPort();
	Authenticator getAuthenticator();
	boolean getSSLOnConnect();
	String[] getFrom();
	
}
