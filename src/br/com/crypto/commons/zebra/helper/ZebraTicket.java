package br.com.crypto.commons.zebra.helper;

import java.text.Normalizer;

public class ZebraTicket {
	
	StringBuilder ticket = new StringBuilder(ZebraConstants.init.concat(ZebraConstants.lineEnd)); 
	String comma = ",";
	
	public String getTicket(){
		
		String ticketTratement = Normalizer.normalize(ticket.toString().concat(ZebraConstants.end).concat(ZebraConstants.lineEnd), Normalizer.Form.NFD);
		ticketTratement = ticketTratement.replaceAll("[^\\p{ASCII}]", "");
		
		return ticketTratement;
	}
	
	public void addText(int x, int y, ZebraRotation rotation, ZebraFontSize fontSize, int expandHorizontal, int expandVertical, boolean reverseImage, String text){
		ticket.append(ZebraConstants.preText+x).append(comma).append(y).append(comma).append(rotation.getValue()).append(comma).append(fontSize.getValue()).append(comma).append(expandHorizontal>0?expandHorizontal:1).append(comma).append(expandVertical>0?expandVertical:1).append(comma).append(reverseImage?"R":"N").append(ZebraConstants.textColapse).append(text).append(ZebraConstants.textColapse).append(ZebraConstants.lineEnd);
	}
	
	public void addBarCode(int x, int y, ZebraRotation rotation, ZebraBarCode barcode, int barWidth, int barHeight, boolean printHumanReadableCode, String text){
		ticket.append(ZebraConstants.preBarCode+x).append(comma).append(y).append(comma).append(rotation.getValue()).append(comma).append(barcode.getValue()).append(comma).append(barWidth>1?barWidth:2).append(comma).append(barHeight>0?barHeight:1).append(comma).append(printHumanReadableCode?"B":"N").append(ZebraConstants.textColapse).append(text).append(ZebraConstants.textColapse).append(ZebraConstants.lineEnd);
	}
	
	public void addQRCode(int x, int y, int scaleFactor, ZebraQRCodeErrorCorrection errorCorrection, String text){
		ticket.append(ZebraConstants.preBarCode+x).append(comma).append(y).append(comma).append(ZebraConstants.qrCode).append(comma).append(ZebraConstants.qrCodeScaleFactorPrefix+scaleFactor).append(comma).append(errorCorrection.getValue()).append(comma).append(ZebraConstants.textColapse).append(text).append(ZebraConstants.textColapse).append(ZebraConstants.lineEnd);
	}

}
