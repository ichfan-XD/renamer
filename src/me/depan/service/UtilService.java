package me.depan.service;

import java.io.File;

public class UtilService {
	public String normalizePath(String text) {
		String normalizeText = "";
		String newText = "";
		String[] splitedText;
		
		for(int i = 0; i < text.length(); i++) {
			if(text.charAt(i) == '\\') normalizeText += "\\"+"\\";
			else normalizeText += text.charAt(i);
		}		
					
		splitedText = normalizeText.split("\\\\");
		
		for(int i = 0; i < splitedText.length;i++) {
			if(i != splitedText.length-1) {
				if(splitedText[i] != "") newText += splitedText[i]+"\\";
			}
//			else newText += "_result\\";
		}
		return newText;
	}
	
	public void createFolder(String path, String folderName) {
		if(path != "" || path != null) {
//			String newPath = normalizePath(path);			
			File newFolder = new File(path);
			boolean res = newFolder.mkdir();
			if(res) System.out.println("berhasil membuat folder");
			else System.out.println("gagal membuat folder "+folderName);
		}
	}
}
