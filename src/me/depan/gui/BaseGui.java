package me.depan.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import me.depan.JFrameDepan;
import me.depan.guiresponse.ComponentResponsiveStyle;
import me.depan.guitheme.GuiTheme;

public class BaseGui extends JFrame implements JFrameDepan{

	private static final long serialVersionUID = 6688926167143199371L;
	
	private JPanel contentPane;
	
	private JPanel panelSideBar;
	private JPanel panelContent;
	
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	
	private GuiTheme gTheme = new GuiTheme();
	private String[] mainTheme = gTheme.getTheme(1);
	
	private RenamerPage firstpage = new RenamerPage(mainTheme);
	private SorterPage secondPage = new SorterPage(mainTheme);
	private ArtistCounter thirdPage = new ArtistCounter(mainTheme);
	
	private JPanel[] pages = new JPanel[]{firstpage,secondPage,thirdPage};
		
	public BaseGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 600, 400);
		setMinimumSize(new Dimension(900,300));
		
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setFocusTraversalKeysEnabled(false);
		contentPane.setBackground(new Color(255, 244, 254));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);		
		setContentPane(contentPane);
		
		panelSideBar = new JPanel();
		panelSideBar.setAutoscrolls(true);
		panelSideBar.setBackground(Color.decode(mainTheme[1]));
		panelSideBar.setBounds(0, 0, 160, getWidth());
		panelSideBar.setMinimumSize(new Dimension(130, 300));
		panelSideBar.setLayout(null);
		
		panelContent = new JPanel();
		panelContent.setVisible(true);
		panelContent.setOpaque(true);
		panelContent.setBackground(Color.decode(mainTheme[2]));
		panelContent.setBounds(160,0,(getWidth()-panelSideBar.getWidth()), 400);
		panelContent.setLayout(null);
		
//		panelContent.add(firstPage);
		
			btnNewButton = new JButton("r e n a m e r");
			btnNewButton.setBounds(10, 10, 140, 36);
			
			btnNewButton_1 = new JButton("s o r t e r");
			btnNewButton_1.setBounds(10, 60, 140, 36);
			
			btnNewButton_2 = new JButton("c o u n t e r");
			btnNewButton_2.setBounds(10, 110, 140, 36);
						
			panelSideBar.add(btnNewButton);
			panelSideBar.add(btnNewButton_1);
			panelSideBar.add(btnNewButton_2);
		
		contentPane.add(panelSideBar);
		contentPane.add(panelContent);
		
		
		setPagesToPanelContent(panelContent, pages);
		setResponsive();
		
		//----------------------------------------------------------------- event
		contentPane.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {								
				panelSideBar.setSize(new Dimension(panelSideBar.getWidth(),getHeight()));
				panelContent.setSize(new Dimension(getWidth()-panelSideBar.getWidth(),getHeight()));
				reactToFrameSize(panelContent, pages);				
				super.componentResized(e);
			}
		});
		
		btnNewButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				activatePages(0, pages);				
			}
		});
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activatePages(1, pages);
			}
		});
		
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activatePages(2, pages);
			}
		});
		
	}
	@Override
	public void setResponsive() {
		ComponentResponsiveStyle responsiveStyle = new ComponentResponsiveStyle(mainTheme);
		responsiveStyle.buttonSet_1(btnNewButton);		
		responsiveStyle.buttonSet_1(btnNewButton_1);		
		responsiveStyle.buttonSet_1(btnNewButton_2);		
	}

	private void setPagesToPanelContent(JPanel panel,JPanel[] contentPages) {
		for(int i = 0; i < contentPages.length; i++) {
			panel.add((Component) contentPages[i]);
			if(i == 0) contentPages[i].setVisible(true);
			else contentPages[i].setVisible(false);
		}
	}
	
	private void activatePages(int index, JPanel[] contentPages) {
		for(int i = 0; i < contentPages.length; i++) {
			if(i == index) contentPages[i].setVisible(true);
			else contentPages[i].setVisible(false);
		}
	}
	
	private void reactToFrameSize(JPanel panel,JPanel[] contentPages) {
		for(int i = 0; i < contentPages.length; i++) {
			contentPages[i].setSize(new Dimension(panel.getWidth(),panel.getHeight()));
		}
	}
}
