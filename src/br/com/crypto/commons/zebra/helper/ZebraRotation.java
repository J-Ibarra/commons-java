package br.com.crypto.commons.zebra.helper;

public enum ZebraRotation {
	
	NOROTATION(0),R90DEGREES(1),R180DEGREES(2),R270DEGREES(3);
	
	int value;
	
	private ZebraRotation(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}

}
