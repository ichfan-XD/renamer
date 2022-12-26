package me.depan;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class TestResponse {
	public void testX() {
		
	}
	
	public void buttonSet_1(JButton button) {
		button.setOpaque(false);
		button.setFocusPainted(false);
		button.setFocusTraversalKeysEnabled(false);
		button.setFocusable(false);
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		button.setForeground(Color.decode(mainTheme[0]));
		button.setFont(new Font("Arial", Font.BOLD, 16));
//		button.setBorder(new LineBorder(Color.decode(mainTheme[0]), 2));
//		button.setBackground(Color.decode(mainTheme[0]));
	}
}
