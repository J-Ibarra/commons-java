package br.com.crypto.commons.logger.helper;

public enum CommonsMessage {
	
	fileNotFoundException("No File"),
	nullPointerException("Null"),
	s3GenericException("S3 Error")
	;
	
	private String value;

	private CommonsMessage(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return value;
	}

}
