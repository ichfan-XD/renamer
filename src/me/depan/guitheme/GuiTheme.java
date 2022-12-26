package me.depan.guitheme;

public class GuiTheme {
	private String[][] themes = {
		{"#B0EEEB","#F765A3","#D7D7FF"},
		{"#202020","#F7AD19","#505050"}
	};
	
	public String[] getTheme(int theme) {
		return themes[theme];
	}
}
