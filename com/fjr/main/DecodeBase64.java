package com.fjr.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

public class DecodeBase64 {
	public DecodeBase64(String input, String output) {
		File fInput = new File( "./"  + input);
		try {
			FileOutputStream fos = new FileOutputStream( "./" + output);
			String fString = getFileString(fInput);
			byte[] decodedString = Base64.getMimeDecoder().decode(fString); 
			fos.write(decodedString);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new DecodeBase64(args[0], args[1] );
	}
	
	public String getFileString(File file) throws IOException {
		byte[] buffer = new byte[(int) file.length()];
		FileInputStream fileIn = null;
		try {
			fileIn = new FileInputStream(file);
			fileIn.read(buffer);
		} finally {
			if (fileIn != null) {
				try {
					fileIn.close();
				} catch (IOException ex) {
				}
			}
		}
		return new String(buffer);
	}
}