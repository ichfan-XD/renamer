package me.depan.guiresponse;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class ComponentResponsiveStyle {
	
	private String[] mainTheme;
	
	public ComponentResponsiveStyle(String[] theme) {
		if(theme == null) mainTheme = new String[] {"#ffffff","#505050","#e0f7ff"};
		else mainTheme = theme;
	}

	public void buttonSet_1(JButton button) {		
		button.setFocusPainted(false);
		button.setFocusTraversalKeysEnabled(false);
		button.setFocusable(false);
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.setForeground(Color.decode(mainTheme[1]));
		button.setFont(new Font("Arial", Font.BOLD, 16));
		button.setBorder(new LineBorder(Color.decode(mainTheme[0]), 2));
		button.setBackground(Color.decode(mainTheme[0]));
		buttonResponseSet_1(button);
	}
	
	public void buttonSet_2(JButton button) {
		button.setOpaque(false);
		button.setFocusPainted(false);
		button.setFocusTraversalKeysEnabled(false);
		button.setFocusable(false);
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.setForeground(Color.decode(mainTheme[1]));
		button.setFont(new Font("Arial", Font.BOLD, 16));
		button.setBorder(new LineBorder(Color.decode(mainTheme[1]), 2));
		button.setBackground(Color.decode(mainTheme[1]));
		buttonResponseSet_2(button);
	}
	
	private void buttonResponseSet_1(final JButton button) {
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {								
				button.setBackground(Color.decode(mainTheme[2]));
				button.setBorder(new LineBorder(Color.decode(mainTheme[2]), 2));
				super.mouseEntered(e);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {			
				button.setBackground(Color.decode(mainTheme[0]));
				button.setBorder(new LineBorder(Color.decode(mainTheme[0]), 2));
				super.mouseExited(e);
			}
		});
	}
	
	private void buttonResponseSet_2(final JButton button) {
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				button.setOpaque(true);
				button.setForeground(Color.decode(mainTheme[0]));
				super.mouseEntered(e);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				button.setOpaque(false);
				button.setForeground(Color.decode(mainTheme[1]));
				super.mouseExited(e);
			}
		});
	}
}
