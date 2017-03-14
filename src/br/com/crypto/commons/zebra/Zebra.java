package br.com.crypto.commons.zebra;

import br.com.crypto.commons.zebra.helper.ZebraTicket;

public interface Zebra {
	
	int getPort();
	String getHost();
	boolean print(ZebraTicket ticket);

}
