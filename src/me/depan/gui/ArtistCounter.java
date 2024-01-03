package me.depan.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import me.depan.guiresponse.ComponentResponsiveStyle;
import me.depan.model.ArtistCounterModel;
import me.depan.model.OrganizeNewMangaModel;
import me.depan.model.RenamerModel;
import me.depan.service.OrganizeUnorganizeService;
import me.depan.service.UtilService;

public class ArtistCounter extends JPanel {

	public JLabel textTitle;
	public JLabel textPath;
	public JTextField inputPath;
	public JButton btnScan;
	public JButton btnExecute;
	public JScrollPane scrollPaneArtist;
	public JScrollPane scrollPaneManga;
	public JTable tableArtist;
	public JTable tableManga;
	
	private List<OrganizeNewMangaModel> listOfUnorganizeMangas = new ArrayList<>();
	private List<ArtistCounterModel> listOfCountedArtist = new ArrayList<>();
	private File[] rawListOfFiles;
	private String sourcePath;
	private OrganizeUnorganizeService ouService = new OrganizeUnorganizeService();
	private UtilService utilService = new UtilService();
	
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
		
		btnScan = new JButton("s c a n");
		btnScan.setBounds(260, 77, 100, 30);
		crs.buttonSet_2(btnScan);		
		btnScan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start();
			}
		});
		
		btnExecute = new JButton("e x e c u t e");
		btnExecute.setBounds(370, 77, 100, 30);
		crs.buttonSet_2(btnExecute);		
		btnExecute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				execute();
			}
		});
		
		scrollPaneArtist = new JScrollPane();
		scrollPaneArtist.setBounds(10, 150, ((getWidth()/2)-25), (getHeight()-200));
		
		scrollPaneManga = new JScrollPane();
		scrollPaneManga.setBounds(((getWidth()/2)), 150, ((getWidth()/2)-25), (getHeight()-200));
		
		tableArtist = new JTable();
		tableArtist.setShowHorizontalLines(true);
        tableArtist.setFillsViewportHeight(true);
		tableArtist.setShowGrid(true);
        tableArtist.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPaneArtist.setViewportView(tableArtist);
		
		tableManga = new JTable();
		tableManga.setShowHorizontalLines(true);
		tableManga.setFillsViewportHeight(true);
		tableManga.setShowGrid(true);
		tableManga.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPaneManga.setViewportView(tableManga);
		
		add(textTitle);
		add(textPath);
		add(inputPath);
		add(btnScan);
		add(btnExecute);
		add(scrollPaneArtist);
		add(scrollPaneManga);
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				scrollPaneArtist.setBounds(10, 150, ((getWidth()/2)-25), (getHeight()-200));
				scrollPaneManga.setBounds(((getWidth()/2)), 150, ((getWidth()/2)-25), (getHeight()-200));
				super.componentResized(e);
			}
		});
	}
	
	private void start() {	
		sourcePath = inputPath.getText();		
		if(sourcePath.length()>0) {
			try {
				if(listOfCountedArtist.size() > 0) listOfCountedArtist = new ArrayList<>();
				if(listOfUnorganizeMangas.size() > 0) listOfUnorganizeMangas = new ArrayList<>();
				getListOfFiles();
				organizing();
				appendListForArtistTable();
				appendListForMangaTable();
//				appendListForUnorganizeTable();
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "file path is doesn't exist or wrong format");
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "file path empty");
		}
	}
	private void getListOfFiles() {
		File folder = new File(sourcePath);
		rawListOfFiles = folder.listFiles();	
		for (int i = 0; i < rawListOfFiles.length; i++) {
			String nameFile = rawListOfFiles[i].getName();
			String theArtist = ouService.getTheArtist(nameFile);
			boolean isExist = false;
			Integer row = null;
			if(theArtist != "") {
				OrganizeNewMangaModel dataManga = new OrganizeNewMangaModel();
				dataManga.setName(nameFile);
				dataManga.setArtist(theArtist.toLowerCase());
				
				for(int j = 0; j < listOfCountedArtist.size(); j++) {
					String mainArtist = listOfCountedArtist.get(j).getArtist().toLowerCase();
					if(theArtist.toLowerCase().equals(mainArtist)) {
						row = j;
						isExist = true;
						break;
					}
				}
				
				if(isExist) {
					int count = listOfCountedArtist.get(row).getCount()+1;
					listOfCountedArtist.get(row).setCount(count);
				}else {
					ArtistCounterModel dataArtist = new ArtistCounterModel();
					dataArtist.setArtist(theArtist.toLowerCase());
					dataArtist.setCount(1);
					this.listOfCountedArtist.add(dataArtist);
				}
				
				listOfUnorganizeMangas.add(dataManga);
			}else {
				System.out.println(nameFile);
				OrganizeNewMangaModel dataManga = new OrganizeNewMangaModel();
				listOfUnorganizeMangas.add(dataManga);
			}
			
		}		
	}
	
	private void appendListForArtistTable() {
		Object[][] model = new Object[listOfCountedArtist.size()][];
		
		for(int i = 0; i < listOfCountedArtist.size(); i++) {
			Object[] data = {
					(i+1),
					listOfCountedArtist.get(i).getArtist(),
					listOfCountedArtist.get(i).getCount()
			};
			model[i] = data;
		}
		
		tableArtist.setModel(new DefaultTableModel(model,new String[] {"#","Artist","Count"}));
		tableArtist.getColumnModel().getColumn(0).setMaxWidth(35);
		tableArtist.getColumnModel().getColumn(1).setMinWidth(50);
		tableArtist.getColumnModel().getColumn(2).setMinWidth(300);
	}
	
	private void appendListForMangaTable() {
		Object[][] model = new Object[listOfUnorganizeMangas.size()][];
		
		for(int i = 0; i < listOfUnorganizeMangas.size(); i++) {
			Object[] data = {
					(i+1),
					listOfUnorganizeMangas.get(i).getStatus(),
					listOfUnorganizeMangas.get(i).getName(),
					listOfUnorganizeMangas.get(i).getArtist(),
					listOfUnorganizeMangas.get(i).getNewPath()
			};
			model[i] = data;
		}
		
		tableManga.setModel(new DefaultTableModel(model,new String[] {"#","Status","File Name","Artist","New Path"}));
		tableManga.getColumnModel().getColumn(0).setMaxWidth(35);
		tableManga.getColumnModel().getColumn(1).setMinWidth(50);
		tableManga.getColumnModel().getColumn(2).setMinWidth(50);
		tableManga.getColumnModel().getColumn(3).setMinWidth(50);
		tableManga.getColumnModel().getColumn(4).setMinWidth(300);
	}
	
	private void organizing() {
		String path = utilService.normalizePath(sourcePath);
		path += "_result\\";
		
		for(int i = 0; i < listOfCountedArtist.size(); i++) {
			String theArtist = listOfCountedArtist.get(i).getArtist();
			int count = listOfCountedArtist.get(i).getCount();
			for(int j = 0; j < listOfUnorganizeMangas.size(); j++) {
				String theArtistFile = listOfUnorganizeMangas.get(j).getArtist();
				String nameFile = listOfUnorganizeMangas.get(j).getName();
				if(theArtist.equals(theArtistFile)&& count > 1) {
					String newPath = path+theArtist+"\\"+nameFile;
					listOfUnorganizeMangas.get(j).setNewPath(newPath);
				}
			}
		}
		System.out.println("raw list " + rawListOfFiles.length);
		System.out.println("list of counted artist " + listOfCountedArtist.size());
		System.out.println("unorganize mangas "+listOfUnorganizeMangas.size());
		
		
		
		appendListForMangaTable();
	}
	
	private void execute() {
		if(rawListOfFiles.length == listOfUnorganizeMangas.size()) {
			String path = utilService.normalizePath(sourcePath);
			path += "_result\\";		
			utilService.createFolder(path,path);
			
			for(int i = 0; i < listOfCountedArtist.size(); i++) {
				if(listOfCountedArtist.get(i).getCount()>1) {
					String newPath = path+listOfCountedArtist.get(i).getArtist()+"\\";
					utilService.createFolder(newPath,listOfCountedArtist.get(i).getArtist());				
				}
			}
			System.out.println(rawListOfFiles.length);
			System.out.println(listOfUnorganizeMangas.size());
			
			for(int j = 0; j < listOfUnorganizeMangas.size();j++) {
				if(listOfUnorganizeMangas.get(j).getNewPath() != null) {				
					File newName = new File(listOfUnorganizeMangas.get(j).getNewPath());
					System.out.println(listOfUnorganizeMangas.get(j).getArtist()+" "+listOfUnorganizeMangas.get(j).getNewPath());
					boolean res = rawListOfFiles[j].renameTo(newName);
//					if(!rawListOfFiles[i].getName().equals(newNameWithPath)) res = rawListOfFiles[i].renameTo(newName);
					if(res) listOfUnorganizeMangas.get(j).setStatus("sukses");
					else listOfUnorganizeMangas.get(j).setStatus("gagal");				
				}
				appendListForMangaTable();
			}
		}
		else {
			System.out.println("data not match make sure everything is correct");
		}
		
	}
	

}
