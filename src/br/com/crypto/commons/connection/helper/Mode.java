package br.com.crypto.commons.connection.helper;

public enum Mode {
	
	VALIDATE("validate"),UPDATE("update"),CREATE("create"),CREATEDROP("create-drop");
	
	private String value;
	
	private Mode(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return this.value;
	}

}
