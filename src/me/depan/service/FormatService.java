package me.depan.service;

import java.util.ArrayList;

public class FormatService {

	public ArrayList<String> getTheFormat(String input) {
		
		ArrayList<String> result = new ArrayList<String>();
		String nameWithoutFormat = null;
		String format = null;
		Integer founDot = null;
		
		if(input != null) {
			for(int i = (input.length()-1); i >= 0;i--) {
				if(String.valueOf(input.charAt(i)).equals(".")) {
					founDot = i;
					break;
				}
			}
			if(founDot != null) {
				format = input.substring(founDot,input.length());
				nameWithoutFormat = input.substring(0,founDot);
			}
			System.out.println(nameWithoutFormat);
			
		}
		
		result.add(nameWithoutFormat);
		result.add(format);
		return result;
	}
	
}
