package br.com.crypto.commons.mail.impl;

import java.io.File;
import java.util.List;

import javax.mail.Authenticator;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import br.com.crypto.commons.mail.MailUtils;

public abstract class MailUtilsAbstract implements MailUtils{
	
	String[] from = null;
	boolean sslOnConnect = true;
	int smtpPort = 443;
	String hostName = null;
	Authenticator authenticator = null;

	@Override
	public boolean send(String subject, String html, String[] to, String[] cc, String[] cco, List<File> attachments) {
		
		
		try {
			
			HtmlEmail email = new HtmlEmail();
			email.setHostName(this.getHostName());
			email.setSmtpPort(this.getSmtpPort());
			email.setAuthenticator(this.getAuthenticator());
			email.setSSLOnConnect(this.getSSLOnConnect());
			email.setFrom(this.getFrom()[0], this.getFrom()[1]);
			email.setSubject(subject);
			email.setHtmlMsg(html);
			
			if(to != null && to.length >0)
				email.addTo(to);
			
			if(cc != null && cc.length >0)
				email.addCc(cc);
			
			if(cco != null && cco.length >0)
				email.addBcc(cco);
			
			if(attachments != null){
				for(File f : attachments){
					email.attach(f);
				}
			}
			
			email.send();
			
			return true;
			
		} catch (EmailException e) {
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	public MailUtilsAbstract() {
		
		this.from = this.getFrom();
		this.hostName = this.getHostName();
		this.authenticator = this.getAuthenticator();
		this.smtpPort = this.getSmtpPort();
		this.sslOnConnect = this.getSSLOnConnect();
		
	}

}
