package br.com.crypto.commons.ftp.helper;

public class ConnectionParameters {
	String user;
	String password;
	Integer port;
	String host;
	
	public ConnectionParameters(String user, String password, Integer port, String host) {
		this.user = user;
		this.password = password;
		this.port = port;
		this.host = host;
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	
	
	
}
