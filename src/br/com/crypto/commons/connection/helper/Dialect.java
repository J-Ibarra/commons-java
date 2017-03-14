package br.com.crypto.commons.connection.helper;

public enum Dialect {
	
	SQLSERVER("org.hibernate.dialect.SQLServer2012Dialect"),MYSQL("org.hibernate.dialect.MySQL5Dialect");
	
	private String value;
	
	private Dialect(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return this.value;
	}

}
