package com.fjr.process.ktp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.HashSet;

import org.apache.commons.codec.digest.DigestUtils;

public class BersihkanKTPGanda {
	HashSet<String> cekUnik = new HashSet<>();
	public BersihkanKTPGanda(String input) {
		try {
			File f = new File(input); 
			File[] listFile = f.listFiles(); 
			System.out.println("jumlah file: " + listFile.length); 
			FileInputStream is = null;
			for( File ff: listFile ) {
				try  {
					is = new FileInputStream(ff);
					String mm = DigestUtils.md5Hex(is);
					is.close();
					if(! cekUnik.contains(mm)) {
						cekUnik.add(mm);
					}else {
						ff.delete(); 
					}
				}catch(Exception e) {
					System.out.println("error: " + e); 
				}finally {
					if( is != null) {
						is.close();
					}
				}
			}
			System.out.println("jumlah ktp: " + cekUnik.size()); 
			System.out.println( "FINISH" ); 
		}catch(Exception e) {
			System.out.println("eror: " + e); 
		}
	}
	
	public static void main(String[] args) {
		new BersihkanKTPGanda(args[0]); 
	}
}
