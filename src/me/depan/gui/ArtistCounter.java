package me.depan.gui;

import java.awt.Color;
import java.awt.Font;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import me.depan.guiresponse.ComponentResponsiveStyle;

public class ArtistCounter extends JPanel {

	public JLabel textTitle;
	public JLabel textPath;
	public JTextField inputPath;
	
	private File[] rawListOfSortingFiles;
	private String sourcePath;
	
	private String[] mainTheme;
	public ArtistCounter(String[] theme) {
		setBounds(0,0,600,300);
		setOpaque(false);
		setLayout(null);
		
		if(theme == null) {
			mainTheme = new String[] {"#D2D2D2","#000000","#D2D2D2"};
		}else {
			mainTheme = theme;
		}
		
		ComponentResponsiveStyle crs = new ComponentResponsiveStyle(mainTheme);
		
		textTitle = new JLabel("ARTIST COUNTER");
		textTitle.setForeground(Color.decode(mainTheme[1]));
		textTitle.setFont(new Font("Arial", Font.BOLD, 17));
		textTitle.setBounds(10, 11, 200, 21);
		
		textPath = new JLabel("Files Path");
		textPath.setFont(new Font("Arial", Font.BOLD, 14));
		textPath.setForeground(Color.decode(mainTheme[1]));
		textPath.setBounds(10, 54, 200, 14);
		
		inputPath = new JTextField();
		inputPath.setForeground(Color.decode(mainTheme[2]));
		inputPath.setFont(new Font("Arial", Font.PLAIN, 17));
		inputPath.setBounds(10, 79, 232, 26);
		inputPath.setColumns(10);
		
		add(textTitle);
		add(textPath);
		add(inputPath);
	}

}
