package me.depan.service;

public class PretierService {
	public String beautify(String text) {
		String[] splitedText;
		String pretied = "";
		int splitedTextCount;
		
		splitedText = text.split(" ");
		splitedTextCount = splitedText.length;
		
		for(int i = 0; i < splitedTextCount; i++) {
			pretied += splitedText[i];
			if(i != (splitedTextCount-1)) pretied += " ";
		}
		System.out.println(pretied);
		return null;
	}
}
