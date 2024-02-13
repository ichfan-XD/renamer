package me.depan.service;

public class FormatService {

	public String getTheFormat(String input) {
		String result = null;
		Integer founDot = null;
		for(int i = input.length(); i == 0;i--) {
			if(String.valueOf(input.charAt(i)).equals(".")) founDot = i;
			System.out.println(founDot);
		}
		return null;
	}
	
}
