package com.fjr.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import fjr.com.utils.FileUtils;


/**
 * sortir peserta pertama berdasarkan gender kemudian umur
 * @author fjr_ksbr
 *
 */
public class TestSortirPeserta {
	
	static final int BAWAH = 1; 
	static final int ATAS = -1; 
	static final int TETAP = 0; 
	
	PrintWriter out = null;
	
	BufferedReader reader = null; 
	public TestSortirPeserta(String input, String output){
		File f = new File( "./" + input ); 
		Pattern p = Pattern.compile("[0-9]{16}"); 
		ArrayList<String> allWord = new ArrayList<>();
		try {
			out = new PrintWriter(new FileOutputStream( "./" + output )); 
			String content = FileUtils. getFileString(f); 
			Matcher m = p.matcher(content); 
			while(m.find()) {
				allWord.add(m.group()); 
			}
			
			Collections.sort(allWord, new Comparator<String>() {
				@Override
				public int compare(String nik1, String nik2) {
					return sortir1(nik1, nik2);
				}
			});
			
			for(String nik: allWord) {
				out.println(nik); 
				out.flush();
			}
			System.out.println("FINISH"); 
		}catch(Exception e) {
			System.out.println("salah."); 
		}finally {
			if( out != null) {
				out.close(); 
			}
		}
	}
	
	public int sortir2(String nik1, String nik2) {
		String tanggal1 = convertNikWanita(nik1, true); 
		String tanggal2 = convertNikWanita(nik2, true); 
		int nilaiTgl1 = Integer.valueOf(tanggal1.substring(0,2)).intValue(); 
		int nilaiTgl2 = Integer.valueOf(tanggal2.substring(0,2)).intValue(); 
		if(nilaiTgl1 > 40)
			nilaiTgl1 = nilaiTgl1 - 40; 
		if(nilaiTgl2 > 40)
			nilaiTgl2  = nilaiTgl2 - 40; 
		int nilaiBulan1 = Integer.valueOf(tanggal1.substring(2, 4)).intValue(); 
		int nilaiBulan2 = Integer.valueOf(tanggal2.substring(2, 4)).intValue(); 
		int nilaiTahun1 = Integer.valueOf(tanggal1.substring(4)).intValue();
		int nilaiTahun2 =  Integer.valueOf(tanggal2.substring(4)).intValue();
		
		Calendar c1 = Calendar.getInstance(); 
		Calendar c2 = Calendar.getInstance();
		
		c1.set(nilaiTahun1, nilaiBulan1, nilaiTgl1);
		c2.set(nilaiTahun2, nilaiBulan2, nilaiTgl2);
		return c1.compareTo(c2); 
	}
	
	public int sortir1( String nik1, String nik2) {
		String tgl1 = nik1.substring(6, 8);
		String tgl2 = nik2.substring(6, 8); 
		
		int genderTgl1 = Integer.valueOf(tgl1); 
		int genderTgl2 = Integer.valueOf(tgl2); 
		
		if(genderTgl1 > 40) {
			if(genderTgl2 <= 40)
				return ATAS;
			if(genderTgl2 > 40)
				return sortir2(nik1, nik2);
			}
		else {
			if(genderTgl2 > 40)
				return BAWAH;
			if(genderTgl2 <= 40)
				return sortir2(nik1, nik2);
		}
		return 0;
	}
		
	public String convertNikWanita(String nik , boolean includeYearPrefix) {		
		String nik_ = nik.substring(6,12); 
		int tgl_ktp = Integer.valueOf(nik_.substring(0,2)); 
		if( tgl_ktp > 40) {
			tgl_ktp  = tgl_ktp - 40 ;
		}
		String tgl_ktp_str = Integer.toString(tgl_ktp);
		if(tgl_ktp_str.length() == 1) {
			tgl_ktp_str = "0".concat(tgl_ktp_str);
		}
		String bulan_ktp_str = nik_.substring(2,4); 
		String tahun_ktp_str = nik_.substring(4,6)  ;
		if( includeYearPrefix) {
			tahun_ktp_str = "19".concat( tahun_ktp_str ) ;
		}
		String tgl_final = tgl_ktp_str.concat( bulan_ktp_str ).concat( tahun_ktp_str );	
		return tgl_final; 
	}
	
	public static void main(String[] args) {
		new TestSortirPeserta(args[0], args[1]); 
	}

}
