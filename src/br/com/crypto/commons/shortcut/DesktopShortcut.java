package br.com.crypto.commons.shortcut;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.junit.Test;

import br.com.crypto.commons.shortcut.helper.Shortcut;

public class DesktopShortcut {
	
	@Test
	public void test(){
		
		Assert.assertEquals(true, create("calcs", new File("/Applications/Calculator.app")));
		
	}
	
	private static String OS = System.getProperty("os.name").toLowerCase();

	public static boolean create(String shortcutName, File executable) {

		try {
			if (OS.indexOf("win") >= 0) {

				File desktop = new File(System.getProperty("user.home") + File.separator + "Desktop", shortcutName+".lnk");
				desktop.createNewFile();
				desktop.setExecutable(true);

				Shortcut scut = new Shortcut(executable);

				scut.shellItemIDList.clear();
				scut.shellItemIDList.add(new Shortcut.ShellItemID(Shortcut.createFileSHID(executable.getAbsolutePath())));
				scut.iconFileName = "";

				OutputStream os = new FileOutputStream(desktop);
				os.write(scut.getBytes());
				os.flush();
				os.close();
				
				return true;

			} else {
				
				File desktop = new File(System.getProperty("user.home") + File.separator + "Desktop", shortcutName);
				
				if(executable.exists()){
					
					String[] cmd = { "ln", "-s", executable.getAbsolutePath(), desktop.getAbsolutePath() };
				    
					int exitCode = 0;
					try {
						exitCode = Runtime.getRuntime().exec(cmd).waitFor();
					} catch (InterruptedException e) {
						e.printStackTrace();
						return false;
					}
					
				    if (exitCode != 0) {
				    	return false;
				    }
					
					return true;
					
				}else{
					return false;
				}
				
			}
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return false;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}

}
