package br.com.crypto.commons.zebra.helper;

public enum ZebraQRCodeErrorCorrection {
	
	LOWER("eL"), MEDIUM("eM"), OPTIMIZED("eQ"), HIGHEST("eH");
	
	String value;
	
	private ZebraQRCodeErrorCorrection(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}

}
