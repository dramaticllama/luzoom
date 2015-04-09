package org.luz.output.visual;

import org.luz.browser.BrowserFrame;
import org.luz.editor.input.Handler;
import org.luz.editor.input.Reader;
import org.luz.editor.nodetools.ToolBelt;
import org.luz.editor.output.io.LuzIO;
import org.luz.editor.output.visual.LuzPanel;
import org.luz.editor.output.visual.XMLFilter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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
	private JToggleButton scribbleButton;
	private JToggleButton lineButton;
	private JToggleButton polyLineButton;
	
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
		ImageIcon scribbleIcon = createImageIcon("scribbleIcon.jpg");
		ImageIcon lineIcon = createImageIcon("lineIcon.jpg");
		ImageIcon polyLineIcon = createImageIcon("polyLineIcon.jpg");
		
		bar.setFloatable(false);
		bar.setFocusable(false);

		panButton = new JToggleButton(panIcon);
		changeButton = new JToggleButton(selectIcon);
		squareButton = new JToggleButton(squareIcon);
		textButton = new JToggleButton(textIcon);
		ellipseButton = new JToggleButton(ellipseIcon);
		scribbleButton = new JToggleButton(scribbleIcon);	
		lineButton = new JToggleButton(lineIcon);
		polyLineButton = new JToggleButton(polyLineIcon);
		
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
		scribbleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data.changeToolBoxState(5);				
			}			
		});
		lineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data.changeToolBoxState(6);				
			}			
		});
		polyLineButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				data.changeToolBoxState(7);
			}			
		});

		ButtonGroup group = new ButtonGroup();
		group.add(panButton);
		group.add(squareButton);
		group.add(changeButton);
		group.add(textButton);
		group.add(ellipseButton);
		group.add(scribbleButton);
		group.add(lineButton);
		group.add(polyLineButton);

		group.setSelected(panButton.getModel(), true);

		bar.add(panButton);
		bar.add(squareButton);
		bar.add(changeButton);
		bar.add(textButton);
		bar.add(ellipseButton);
		bar.add(scribbleButton);
		bar.add(lineButton);
		bar.add(polyLineButton);
	}
	private ImageIcon createImageIcon(String filePath){
		return new ImageIcon(filePath);
	}
}
