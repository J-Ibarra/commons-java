package br.com.crypto.commons.connection.helper;

public enum Driver {
	
	SQLSERVER("com.microsoft.sqlserver.jdbc.SQLServerDriver"),MYSQL("com.mysql.jdbc.Driver"),H2("org.h2.Driver"),JTDS("net.sourceforge.jtds.jdbc.Driver");
	
	private String value;
	
	private Driver(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
}
