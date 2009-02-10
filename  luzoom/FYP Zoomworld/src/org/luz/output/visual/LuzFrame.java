package org.luz.output.visual;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import org.luz.browser.BrowserFrame;
import org.luz.input.Handler;
import org.luz.input.Reader;
import org.luz.output.io.LuzIO;
import org.luz.tools.ToolBelt;

public class LuzFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private LuzPanel panel;
	private ToolBelt data;
	private JToolBar bar;

	private JToggleButton panButton;
	private JToggleButton squareButton;
	private JToggleButton changeButton;
	private JToggleButton textButton;
	private JToggleButton ellipseButton;
	private JToggleButton polyLineButton;
	private JToggleButton lineButton;

	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu editMenu;
	private JMenu helpMenu;
	private JMenu navigateMenu;

	private JMenuItem fileNew;
	private JMenuItem fileTest;
	private JMenuItem fileSave;
	private JMenuItem fileSaveAs;
	private JMenuItem fileLoad;
	private JMenuItem fileExit;

	private JMenuItem editCopy;
	private JMenuItem editPaste;
	private JMenuItem editDelete;

	private JMenuItem helpAbout;

	private JMenuItem naviResetView;
	private JMenu naviGotoView;
	private JMenuItem gotoViewSet;

	private JFileChooser fileC;

	private Reader xmlReader;
	private Handler xmlHandler;

	public LuzFrame() {
		super("LUZ - Lancaster University Zoomworld");
		bar = new JToolBar();						
		addButtons(bar);

		fileC = new JFileChooser("C:\\Documents and Settings\\Owner\\Desktop\\Backup\\Code\\Eclipse Workspace\\FYP Zoomworld");
		fileC.setFileFilter(new XMLFilter());
		
		panel = new LuzPanel();
		data = new ToolBelt(panel);		
		data.changeToolBoxState(0);
		xmlHandler = new Handler(panel);
		xmlReader =  new Reader();
		createMenu();
		this.setJMenuBar(menuBar);

		add(bar,BorderLayout.PAGE_START);
		add(panel);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		pack();
		setVisible(true);	
	}
	private void createMenu() {
		menuBar = new JMenuBar();

		fileMenu = new JMenu("File");
		fileNew = new JMenuItem("New Scene");
		fileTest = new JMenuItem("Open with Browser");
		fileSave = new JMenuItem("Save Scene");
		fileSaveAs = new JMenuItem("Save Scene As..");
		fileLoad = new JMenuItem("Open Scene");
		fileExit = new JMenuItem("Exit");

		editMenu = new JMenu("Edit");
		editCopy = new JMenuItem("Copy");
		editPaste = new JMenuItem("Paste");
		editDelete = new JMenuItem("Delete");		

		helpMenu = new JMenu("Help");
		helpAbout = new JMenuItem("About");

		navigateMenu = new JMenu("Navigate");
		naviResetView = new JMenuItem("Reset View");
		naviGotoView = new JMenu("Bookmarks ..");

		gotoViewSet = new JMenuItem("Add bookmark");

		menuBar.add(fileMenu);
		fileMenu.add(fileNew);
		fileNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.layer.removeAllChildren();
			}			
		});
		fileMenu.add(fileLoad);	
		fileLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fileC.showOpenDialog(null)== JFileChooser.APPROVE_OPTION){
					xmlReader.startXMLParse(fileC.getSelectedFile(), xmlHandler);
				}
			}			
		});
		fileMenu.addSeparator();
		fileMenu.add(fileSave);
		fileSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fileC.showSaveDialog(null)== JFileChooser.APPROVE_OPTION){
					try {
						LuzIO.save(panel.layer, fileC.getSelectedFile());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}			
		});
		fileMenu.add(fileSaveAs);
		fileMenu.addSeparator();
		fileMenu.add(fileTest);
		fileTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BrowserFrame b = new BrowserFrame();
			}			
		});
		fileMenu.addSeparator();
		fileMenu.add(fileExit);
		fileExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}			
		});

		menuBar.add(editMenu);
		editMenu.add(editCopy);
		editMenu.add(editPaste);
		editMenu.add(editDelete);		

		menuBar.add(navigateMenu);
		navigateMenu.add(naviResetView);
		navigateMenu.add(naviGotoView);
		naviGotoView.add(gotoViewSet);
		naviGotoView.addSeparator();

		menuBar.add(helpMenu);	
		helpMenu.add(helpAbout);

	}
	private void addButtons(JToolBar bar) {

		ImageIcon squareIcon = createImageIcon("squareIcon.jpg");
		ImageIcon selectIcon = createImageIcon("selectIcon.jpg");
		ImageIcon textIcon = createImageIcon("textIcon.jpg");
		ImageIcon panIcon = createImageIcon("panIcon.jpg");
		ImageIcon ellipseIcon = createImageIcon("ellipseIcon.jpg");
		ImageIcon polyLineIcon = createImageIcon("polyLineIcon.jpg");
		ImageIcon lineIcon = createImageIcon("lineIcon.jpg");
		
		bar.setFloatable(false);
		bar.setFocusable(false);

		panButton = new JToggleButton(panIcon);
		changeButton = new JToggleButton(selectIcon);
		squareButton = new JToggleButton(squareIcon);
		textButton = new JToggleButton(textIcon);
		ellipseButton = new JToggleButton(ellipseIcon);
		polyLineButton = new JToggleButton(polyLineIcon);	
		lineButton = new JToggleButton(lineIcon);
		
		panButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data.changeToolBoxState(0);				
			}			
		});
		squareButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data.changeToolBoxState(1);				
			}			
		});
		changeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data.changeToolBoxState(2);
			}
		});		
		textButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data.changeToolBoxState(3);				
			}			
		});
		ellipseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data.changeToolBoxState(4);				
			}			
		});
		polyLineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data.changeToolBoxState(5);				
			}			
		});
		lineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data.changeToolBoxState(6);				
			}			
		});

		ButtonGroup group = new ButtonGroup();
		group.add(panButton);
		group.add(squareButton);
		group.add(changeButton);
		group.add(textButton);
		group.add(ellipseButton);
		group.add(polyLineButton);
		group.add(lineButton);

		group.setSelected(panButton.getModel(), true);

		bar.add(panButton);
		bar.add(squareButton);
		bar.add(changeButton);
		bar.add(textButton);
		bar.add(ellipseButton);
		bar.add(polyLineButton);
		bar.add(lineButton);
	}
	private ImageIcon createImageIcon(String filePath){
		return new ImageIcon(filePath);
	}
}
