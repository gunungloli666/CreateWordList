package com.fjr.process.ktp;

import java.io.File;

public class DeleteFile {
	
	public static void main(String[] args) {
		File f = new File("F:/ktp/ACD Systems Digital Imaging 980x331_025630.jpg"); 
		if(f.exists()) {
			System.out.println("ada"); 
			f.delete(); 
		}
	}

}
