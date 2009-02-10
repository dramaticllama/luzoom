package org.luz.browser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.luz.input.Handler;
import org.luz.input.Reader;
import org.luz.output.visual.LuzPanel;

@SuppressWarnings("serial")
public class BrowserFrame extends JFrame {

	private LuzPanel panel;
	
	private JMenuBar bar;
	
	private JMenu file;
	private JMenuItem fOpen;
	private JMenuItem fExit;
	
	private JFileChooser fileC;
	
	private Reader xmlReader;
	private Handler xmlHandler;
	
	public BrowserFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Luz Browser");
		setLocationRelativeTo(null);	
		xmlReader = new Reader();
		xmlHandler = new Handler(panel);
		fileC = new JFileChooser();
		
		addDrawPanel();
		addMenu();


		pack();
		setVisible(true);
	}

	private void addMenu() {
		bar = new JMenuBar();
		setJMenuBar(bar);
		
		file = new JMenu("File");
		bar.add(file);
		fOpen = new JMenuItem("Open");
		fOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fileC.showSaveDialog(null)== JFileChooser.APPROVE_OPTION){
					xmlReader.startXMLParse(fileC.getSelectedFile(), xmlHandler);
				}				
			}			
		});
		file.add(fOpen);
		fExit = new JMenuItem("Exit");
		fExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();		
			}			
		});
		file.add(fExit);
	}

	private void addDrawPanel() {
		panel = new LuzPanel();		
		add(panel);
	}

}
