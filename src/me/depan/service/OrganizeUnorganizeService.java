package me.depan.service;

import java.io.File;

public class OrganizeUnorganizeService {

	public String getTheArtist(String name) {
		String firstChar = String.valueOf(name.charAt(0));
		String theArtist = "";
		Integer openBracket = null;
		Integer closeBracket = null;		
		
		if(firstChar.equals("[")) {
			openBracket = 0;
			for(int i = 0; i < name.length();i++) {
				char theChar = name.charAt(i);
				if(theChar == ']' && openBracket != null) {
					closeBracket = i;	
					break;
				}
			}
			if(openBracket != null && closeBracket != null) {
				theArtist = name.substring((openBracket+1),(closeBracket));
//				System.out.println(theArtist);
			}
		}		
		return theArtist;
	}
}
