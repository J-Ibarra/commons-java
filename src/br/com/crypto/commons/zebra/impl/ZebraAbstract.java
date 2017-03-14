package br.com.crypto.commons.zebra.impl;

import java.io.DataOutputStream;
import java.net.Socket;

import br.com.crypto.commons.zebra.Zebra;
import br.com.crypto.commons.zebra.helper.ZebraTicket;

public abstract class ZebraAbstract implements Zebra{

	@Override
	public boolean print(ZebraTicket ticket) {
		
		Socket socket = null;
        DataOutputStream dos = null;

        try {

            socket = new Socket(this.getHost(), this.getPort());
            dos = new DataOutputStream(socket.getOutputStream());
            dos.writeBytes(ticket.getTicket());

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
            }
            try {
                dos.flush();
            } catch (Exception e) {
            }
            try {             
                dos.close();
            } catch (Exception e) {
            }
            
            socket = null;
            dos = null;
        }               

        return true;
		
	}
	
}
