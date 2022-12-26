package me.depan.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;

public class SorterPage extends JPanel {
	
	public JLabel textTitle;
	public JLabel textSortingPath;
	public JLabel textListPath;
	public JTextField inputSortingPath;
	public JTextField inputListPath;
	public JScrollPane scrollPaneList;
	public JScrollPane scrollPaneSorted;
	public JTable tableList;
	public JTable tableSorted;
	
	private String[] mainTheme;
	
	public SorterPage(String[] theme) {
		setBounds(0,0,600,300);
		setOpaque(false);
		setLayout(null);
		
		if(theme == null) {
			mainTheme = new String[] {"#D2D2D2","#000000","#D2D2D2"};
		}else {
			mainTheme = theme;
		}
		
		textTitle = new JLabel("S O R T E R");
		textTitle.setForeground(Color.decode(mainTheme[1]));
		textTitle.setFont(new Font("Arial", Font.BOLD, 17));
		textTitle.setBounds(10, 11, 112, 21);
		
		textSortingPath = new JLabel("Path :");
		textSortingPath.setFont(new Font("Arial", Font.BOLD, 14));
		textSortingPath.setForeground(Color.decode(mainTheme[1]));
		textSortingPath.setBounds(10, 54, 50, 14);
		
		textListPath = new JLabel("List Path :");
		textListPath.setFont(new Font("Arial", Font.BOLD, 14));
		textListPath.setForeground(Color.decode(mainTheme[1]));
		textListPath.setBounds(10, 123, 232, 14);
		
		inputSortingPath = new JTextField();
		inputSortingPath.setForeground(Color.decode(mainTheme[1]));
		inputSortingPath.setFont(new Font("Arial", Font.PLAIN, 17));
		inputSortingPath.setBounds(10, 79, 232, 26);
		inputSortingPath.setColumns(10);
		
		inputListPath = new JTextField();
		inputListPath.setForeground(Color.decode(mainTheme[1]));
		inputListPath.setFont(new Font("Arial", Font.PLAIN, 17));
		inputListPath.setBounds(10, 148, 232, 26);
		inputListPath.setColumns(10);
		
		scrollPaneList = new JScrollPane();
		scrollPaneList.setBounds(10, 208, ((getWidth()/2)-25), (getHeight()-200));
		
		scrollPaneSorted = new JScrollPane();
		scrollPaneSorted.setBounds(((getWidth()/2)), 208, ((getWidth()/2)-25), (getHeight()-200));
		
		tableList = new JTable();
		tableList.setShowHorizontalLines(true);
        tableList.setFillsViewportHeight(true);
		tableList.setShowGrid(true);
        tableList.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPaneList.setViewportView(tableList);
		
		tableSorted = new JTable();
		tableSorted.setShowHorizontalLines(true);
        tableSorted.setFillsViewportHeight(true);
		tableSorted.setShowGrid(true);
        tableSorted.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPaneSorted.setViewportView(tableSorted);
		
		add(textTitle);
		add(textSortingPath);
		add(textListPath);
		add(inputSortingPath);
		add(inputListPath);
		add(scrollPaneList);
		add(scrollPaneSorted);
	}
	
}
