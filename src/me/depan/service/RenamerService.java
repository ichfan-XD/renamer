package me.depan.service;

import java.util.Objects;

public class RenamerService {
	
	private Integer parenthesisStart = null,parenthesisEnd = null,bracketStart = null,bracketEnd = null;
	
	public String renamer(String input) {
		String result = "";
		for(int i = 0; i < input.length();i++) {
			if(String.valueOf(input.charAt(i)).equals("[") && parenthesisStart == null) parenthesisStart = i;
			if(String.valueOf(input.charAt(i)).equals("]") && parenthesisEnd == null) parenthesisEnd = i;
			if(String.valueOf(input.charAt(i)).equals("(") && bracketStart == null) bracketStart = i;
			if(String.valueOf(input.charAt(i)).equals(")") && bracketEnd == null) bracketEnd = i;
		}
		System.out.println(input);
//		System.out.print(parenthesisStart);
//		System.out.print(parenthesisEnd);
//		System.out.print(bracketStart);
//		System.out.println(bracketEnd);

		if(Objects.equals(parenthesisStart, 0) && parenthesisEnd != null) {
			System.out.println("parenthesis");
			result = foundParenthesis(input);
		}else if(Objects.equals(bracketStart, 0) && bracketEnd != null) {
			System.out.println("bracket");
//			result = foundOpenBracket(input);
		}else {
			System.out.println("not found or different format");
			result = input;
		}
//		if(firstChar.equals("(")) {
//			try {
//				result = foundParenthesis(input);
//			}catch(Exception e) {
//				
//			}
//			ListOfFiles.get(i).setNewName(result);
//		}else if(firstChar.equals("[")) {
//			try{
//				result = renamer.foundOpenBracket(ListOfFiles.get(i).getOldName());
//			}catch(Exception e){
//				String breakLine = "\n";
//				errorLog.append(ListOfFiles.get(i).getOldName() + breakLine);
//			}
//			ListOfFiles.get(i).setNewName(result);
//		}
//		else {
//			result = renamer.underScoreRemover(ListOfFiles.get(i).getOldName());
//			ListOfFiles.get(i).setNewName(result);
//		}
		resetVariable();
		return result;
	}
	
	private String foundParenthesis(String input) {
		String afterCut = "",frontName = "",backName = "";
		if(bracketStart != null && bracketEnd != null) {
			if(bracketStart > parenthesisStart && bracketEnd < parenthesisEnd) {
				frontName = input.substring(bracketStart+1,bracketEnd);
				backName = input.substring(parenthesisEnd+1,input.length());
				System.out.println(frontName+" - "+backName);
			}
		}
		
		
//		Integer parenthesisStart = null,parenthesisEnd = null;
		
//		for(int i = 0; i < text.length();i++) {
//			char theChar = text.charAt(i);
//			if(theChar == '(' && parenthesisStart == null) {
//				parenthesisStart = i;
//			}else if(theChar == ')' && parenthesisEnd == null) {
//				parenthesisEnd = i+1;
//			}
//		}
	
//		if(parenthesisStart != null && parenthesisEnd != null) {
//			String theCutText = text.substring(parenthesisStart,parenthesisEnd);
//			afterCut = text.replace(theCutText,"");
//		}
//		
//		if(afterCut.charAt(0) == ' ') afterCut = afterCut.substring(1);
//		if(afterCut.charAt(0) == '[') afterCut = foundOpenBracket(afterCut);
//		else afterCut = text;
//		afterCut = underScoreRemover(afterCut);
//		afterCut = doubleSpaceRemover(afterCut);
		
		return null;
	}
	
	private String foundOpenBracket(String text) {
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
	
	private void resetVariable() {
		parenthesisStart = null;
		parenthesisEnd = null;
		bracketStart = null;
		bracketEnd = null;
	}
	
	private String underScoreRemover(String text) {		
		String afterCut = text.replace("_", " ");
		return afterCut;
	}
	
	private String doubleSpaceRemover(String text) {
		String afterCut = text.replace("  ", " ");
		return afterCut;
	}
}
