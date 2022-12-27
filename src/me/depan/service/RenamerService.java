package me.depan.service;

public class RenamerService {
	
	public String foundParenthesis(String text) {
		String afterCut = null;
		Integer parenthesisStart = null,parenthesisEnd = null;
		
		for(int i = 0; i < text.length();i++) {
			char theChar = text.charAt(i);
			if(theChar == '(' && parenthesisStart == null) {
				parenthesisStart = i;
			}else if(theChar == ')' && parenthesisEnd == null) {
				parenthesisEnd = i+1;
			}
		}
		
		if(parenthesisStart != null && parenthesisEnd != null) {
			String theCutText = text.substring(parenthesisStart,parenthesisEnd);
			afterCut = text.replace(theCutText,"");
		}
		
		if(afterCut.charAt(0) == ' ') afterCut = afterCut.substring(1);
		if(afterCut.charAt(0) == '[') afterCut = foundOpenBracket(afterCut);
		else afterCut = text;
		afterCut = underScoreRemover(afterCut);
		afterCut = doubleSpaceRemover(afterCut);
		
		return afterCut;
	}
	
	public String foundOpenBracket(String text) {
		String afterCut = null,insideBracket = null, outsideBracket = null;
		Integer openBracket = null,closedBracket = null,parenthesisStart = null,parenthesisEnd = null;
		for(int i = 0; i < text.length();i++) {
			char theChar = text.charAt(i);
			if(theChar == '[' && openBracket == null) {
				openBracket = i; 
				
			}else if(theChar == ']' && closedBracket == null) {
				closedBracket = i+1;
			}
		}
		
		if(openBracket != null && closedBracket != null) {
			insideBracket = text.substring(openBracket,closedBracket);
			
			outsideBracket = text.replace(insideBracket,"");
			if(outsideBracket.charAt(0) == ' ') outsideBracket = outsideBracket.substring(1);
		}
		
		//if parenthesis inside bracket
		if(insideBracket != null) {
			for(int i = 0; i < insideBracket.length(); i++) {
				if(insideBracket.charAt(i) == '(') parenthesisStart = i+1;
				else if(insideBracket.charAt(i) == ')') parenthesisEnd = i;
			}
			
			if(parenthesisStart != null && parenthesisEnd != null) {
				afterCut = insideBracket.substring(parenthesisStart, parenthesisEnd);
				afterCut = "["+afterCut+"] "+outsideBracket;
			}else afterCut = text;
		}
		else afterCut = text;
		
		afterCut = underScoreRemover(afterCut);
		afterCut = doubleSpaceRemover(afterCut);
		return afterCut;
	}
	
	public String underScoreRemover(String text) {		
		String afterCut = text.replace("_", " ");
		return afterCut;
	}
	
	public String doubleSpaceRemover(String text) {
		String afterCut = text.replace("  ", " ");
		return afterCut;
	}
}
