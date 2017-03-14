package br.com.crypto.commons.security;

public interface HttpSQLInjectionTest {
	
	boolean injectPOSTFields(String url, String[] fields);

}
