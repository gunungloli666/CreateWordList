package com.fjr.process.ktp;

import java.io.File;

public class TestDeleteBanyakFile {
	public static void main(String[] args) {
		File all = new File("F:/backup-ktp/"); 
		File f[] = all.listFiles(); 
		for( File ff : f ) {
			if( ff.exists()) {
//				System.out.println( ff.getAbsolutePath()); 
				ff.delete();
			}
		}
	}
}
