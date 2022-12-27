package me.depan.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import me.depan.TestResponse;
import me.depan.guiresponse.ButtonResponseStyle;
import me.depan.guiresponse.ComponentResponsiveStyle;
import me.depan.service.PretierService;
import me.depan.service.RemoverService;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RenamerPage extends JPanel {
	
	public JLabel title;
	public JLabel textPath;
	public JTextField inputPath;
	public JButton btnScan;
	public JButton btnExecute;
	public JScrollPane scrollPaneOld;
	public JScrollPane scrollPaneModified;
	public JTable tableOld;
	public JTable tableModified;
	
	private List<String> oldListOfFiles = new ArrayList<>();
	private List<String> modifiedListOfFiles = new ArrayList<>();
	private File[] rawListOfFiles;
	private String sourcePath;
	
	private String[] mainTheme;
	
	private RemoverService remover = new RemoverService();
	private PretierService pretierService = new PretierService();

	public RenamerPage(String[] theme) {
		setBounds(0,0,600,300);
		setOpaque(false);
		setLayout(null);
		
		if(theme == null) {
			mainTheme = new String[] {"#D2D2D2","#000000","#D2D2D2"};
		}else {
			mainTheme = theme;
		}
		
		ComponentResponsiveStyle crs = new ComponentResponsiveStyle(mainTheme);
		
		title = new JLabel("R E N A M E R");
		title.setForeground(Color.decode(mainTheme[1]));
		title.setFont(new Font("Arial", Font.BOLD, 17));
		title.setBounds(10, 11, 112, 21);
		
		textPath = new JLabel("Path :");
		textPath.setFont(new Font("Arial", Font.BOLD, 14));
		textPath.setForeground(Color.decode(mainTheme[1]));
		textPath.setBounds(10, 54, 50, 14);
		
		inputPath = new JTextField();
		inputPath.setForeground(Color.decode(mainTheme[1]));
		inputPath.setFont(new Font("Arial", Font.PLAIN, 17));
		inputPath.setBounds(65, 47, 232, 26);
		inputPath.setColumns(10);
		
		btnScan = new JButton("s c a n");
		btnScan.setBounds(307, 45, 100, 30);
		crs.buttonSet_2(btnScan);		
		btnScan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scanPath();
			}
		});
		
		btnExecute = new JButton("e x e c u t e");
		btnExecute.setBounds(307, 90, 100, 30);
		crs.buttonSet_2(btnExecute);
		btnExecute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executeRename();
			}
		});

		
		scrollPaneOld = new JScrollPane();
		scrollPaneOld.setBounds(10, 150, ((getWidth()/2)-25), (getHeight()-200));
		
		scrollPaneModified = new JScrollPane();
		scrollPaneModified.setBounds(((getWidth()/2)), 150, ((getWidth()/2)-25), (getHeight()-200));
		
		tableOld = new JTable();
		tableOld.setShowHorizontalLines(true);
        tableOld.setFillsViewportHeight(true);
		tableOld.setShowGrid(true);
        tableOld.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPaneOld.setViewportView(tableOld);
		
		tableModified = new JTable();
		tableModified.setShowHorizontalLines(true);
        tableModified.setFillsViewportHeight(true);
		tableModified.setShowGrid(true);
        tableModified.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPaneModified.setViewportView(tableModified);
		
		add(title);
		add(textPath);
		add(inputPath);
		add(btnScan);
		add(btnExecute);
		add(scrollPaneOld);
		add(scrollPaneModified);
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				scrollPaneOld.setBounds(10, 150, ((getWidth()/2)-25), (getHeight()-200));
				scrollPaneModified.setBounds(((getWidth()/2)), 150, ((getWidth()/2)-25), (getHeight()-200));
				super.componentResized(e);
			}
		});
		
	}
	
	private void scanPath() {	
		sourcePath = inputPath.getText();
		if(sourcePath != null) {
			if(oldListOfFiles.size() > 0) oldListOfFiles = new ArrayList<>();;
			if(modifiedListOfFiles.size() > 0) modifiedListOfFiles = new ArrayList<>();;
			getListOfFiles();
			appendListForOldTable();
			cutTheGroupsName();
		}
		else {
			System.out.println("empty");
		}
	}

	private void getListOfFiles() {
		File folder = new File(sourcePath);
		rawListOfFiles = folder.listFiles();	
		for (int i = 0; i < rawListOfFiles.length; i++) {
			this.oldListOfFiles.add(rawListOfFiles[i].getName());
		}		
	}
	
	private void cutTheGroupsName() {
		for(int i = 0; i < oldListOfFiles.size(); i++) {
			String firstChar = String.valueOf(oldListOfFiles.get(i).charAt(0));
			if(firstChar.equals("(")) {
				String result = remover.foundParenthesis(oldListOfFiles.get(i));
				modifiedListOfFiles.add(result);
			}else if(firstChar.equals("[")) {				
				String result = remover.foundOpenBracket(oldListOfFiles.get(i));
				modifiedListOfFiles.add(result);
			}else {
				String result = remover.underScoreRemover(oldListOfFiles.get(i));
				modifiedListOfFiles.add(result);
			}
		}
		
		for(int i = 0; i < modifiedListOfFiles.size(); i++) {
			String pretied = pretierService.beautify(modifiedListOfFiles.get(i));
		}
		
		appendListForModifiedTable();
	}

	private void appendListForOldTable() {
		Object[][] model = new Object[oldListOfFiles.size()][];
		
		for(int i = 0; i < oldListOfFiles.size(); i++) {
			Object[] data = {(i+1),oldListOfFiles.get(i)};
			model[i] = data;
		}
		
		tableOld.setModel(new DefaultTableModel(model,new String[] {"#","name"}));
		tableOld.getColumnModel().getColumn(0).setMaxWidth(35);
		tableOld.getColumnModel().getColumn(1).setMinWidth(1200);
	}
	
	private void appendListForModifiedTable() {
		Object[][] model = new Object[modifiedListOfFiles.size()][];
		
		for(int i = 0; i < modifiedListOfFiles.size(); i++) {
			Object[] data = {(i+1),modifiedListOfFiles.get(i)};
			model[i] = data;
		}
		
		tableModified.setModel(new DefaultTableModel(model,new String[] {"#","name"}));
		tableModified.getColumnModel().getColumn(0).setMaxWidth(35);
		tableModified.getColumnModel().getColumn(1).setMinWidth(1200);
	}
	
	private String foundAlphabet() {
		return null;
	}
	
	private void executeRename() {
		for(int i = 0; i < rawListOfFiles.length;i++) {
			String newNameWithPath = sourcePath+"\\"+modifiedListOfFiles.get(i);
			File newName = new File(newNameWithPath);
			if(!rawListOfFiles[i].getName().equals(newNameWithPath)) rawListOfFiles[i].renameTo(newName);
		}
	}
}
