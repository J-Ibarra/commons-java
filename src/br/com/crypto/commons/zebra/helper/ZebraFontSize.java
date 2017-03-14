package br.com.crypto.commons.zebra.helper;

public enum ZebraFontSize {

	PT4(1),PT6(2),PT8(3),PT10(4),PT21(5);
	
	int value;
	
	private ZebraFontSize(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
	
}
