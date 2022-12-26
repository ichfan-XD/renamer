package me.depan.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import me.depan.guiresponse.ComponentResponsiveStyle;
import me.depan.model.SubSortingModel;
import me.depan.model.SortingModel;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SorterPage extends JPanel {
	
	public JLabel textTitle;
	public JLabel textSortingPath;
	public JLabel textListPath;
	public JButton btnScan;
	public JButton btnExecute;
	public JTextField inputSortingPath;
	public JTextField inputListPath;
	public JScrollPane scrollPaneList;
	public JScrollPane scrollPaneSorted;
	public JTable tableList;
	public JTable tableSorted;
	
	private List<SortingModel> sortingListOfFiles = new ArrayList<>();
	private List<String> mainSortingListOfFiles = new ArrayList<>();
	private List<SubSortingModel> subSortingListOfFiles = new ArrayList<>();
	private File[] rawListOfSortingFiles;
	private File[] rawListOfListingFiles;
	private String sourceSortingPath;
	private String sourceListingPath;
	
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
		
		ComponentResponsiveStyle crs = new ComponentResponsiveStyle(mainTheme);
		
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
		
		btnScan = new JButton("s c a n");
		btnScan.setBounds(307, 90, 100, 30);
		crs.buttonSet_2(btnScan);
		btnScan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scanPath();
			}
		});
		
		btnExecute = new JButton("e x e c u t e");
		btnExecute.setBounds(347, 148, 100, 30);
		crs.buttonSet_2(btnExecute);
		
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
		add(btnScan);
		add(btnExecute);
		add(inputSortingPath);
		add(inputListPath);
		add(scrollPaneList);
		add(scrollPaneSorted);
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				scrollPaneList.setBounds(10, 208, ((getWidth()/2)-25), (getHeight()-258));
				scrollPaneSorted.setBounds(((getWidth()/2)), 208, ((getWidth()/2)-25), (getHeight()-258));
				super.componentResized(e);
			}
		});
	}
	
	private void scanPath() {	
		sourceSortingPath = inputSortingPath.getText();
		sourceListingPath = inputListPath.getText();
		if(sourceSortingPath != null && sourceListingPath != null) {
			if(sortingListOfFiles.size() > 0) sortingListOfFiles = new ArrayList<>();;
			if(mainSortingListOfFiles.size() > 0) mainSortingListOfFiles = new ArrayList<>();;
			getListOfSortingFiles();
			getListOfMainListingFiles();
			testSubList();
//			appendListForOldTable();
//			cutTheGroupsName();
		}
		else {
			System.out.println("empty");
		}
	}
	
	private void getListOfSortingFiles() {
		File folder = new File(sourceSortingPath);
		rawListOfSortingFiles = folder.listFiles();	
		for (int i = 0; i < rawListOfSortingFiles.length; i++) {
			SortingModel data = new SortingModel();
			data.setOldPath(rawListOfSortingFiles[i].getName());
			this.sortingListOfFiles.add(data);
		}		
	}
	
	private void getListOfMainListingFiles() {
		File folder = new File(sourceListingPath);
		rawListOfListingFiles = folder.listFiles();	
		for (int i = 0; i < rawListOfListingFiles.length; i++) {
			getListOfSubListingFiles(rawListOfListingFiles[i].getName());
			this.mainSortingListOfFiles.add(rawListOfListingFiles[i].getName());
		}		
	}
	
	private void getListOfSubListingFiles(String subname) {
		File folder = new File(sourceListingPath+"/"+subname);
		File[] rawSubListOfListingFiles = folder.listFiles();	
		for (int i = 0; i < rawSubListOfListingFiles.length; i++) {
			SubSortingModel data = new SubSortingModel();
			data.setParentFolder(subname);
			data.setName(rawSubListOfListingFiles[i].getName());
			this.subSortingListOfFiles.add(data);
		}	
	}
	
	private void sortingTheFile() {
		for(SortingModel sortingFile : sortingListOfFiles) {
			for(SubSortingModel listing : subSortingListOfFiles) {
				
			}
		}
	}
	
	private void testSubList() {
		for(SubSortingModel data:subSortingListOfFiles) {
			System.out.println(data.getParentFolder()+" "+data.getName());
		}
	}
	
	
	private void appendListForSortingTable() {
		Object[][] model = new Object[sortingListOfFiles.size()][];
		
		for(int i = 0; i < sortingListOfFiles.size(); i++) {
			Object[] data = {(i+1),sortingListOfFiles.get(i).getOldPath(),sortingListOfFiles.get(i).getNewPath()};
			model[i] = data;
		}
		
		tableSorted.setModel(new DefaultTableModel(model,new String[] {"#","Old Path","New Path"}));
		tableSorted.getColumnModel().getColumn(0).setMaxWidth(35);
		tableSorted.getColumnModel().getColumn(1).setMinWidth(1200);
		tableSorted.getColumnModel().getColumn(2).setMinWidth(1200);
	}
	
}
