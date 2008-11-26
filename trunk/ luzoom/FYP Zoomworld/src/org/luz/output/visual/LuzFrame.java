package org.luz.output.visual;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

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

	public LuzFrame() {
		super("LUZ - Lancaster University Zoomworld");
		bar = new JToolBar();						
		addButtons(bar);
		
		panel = new LuzPanel();
		data = new ToolBelt(panel);		
	
		add(bar,BorderLayout.PAGE_START);
		add(panel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		pack();
		setVisible(true);	
	}
	private void addButtons(JToolBar bar) {
		
		ImageIcon squareIcon = createImageIcon("squareIcon.jpg");
	    ImageIcon selectIcon = createImageIcon("selectIcon.jpg");
	    ImageIcon textIcon = createImageIcon("textIcon.jpg");
	    ImageIcon panIcon = createImageIcon("panIcon.jpg");
	    ImageIcon ellipseIcon = createImageIcon("ellipseIcon.jpg");
	    
		bar.setFloatable(false);
		bar.setFocusable(false);

		panButton = new JToggleButton(panIcon);
		changeButton = new JToggleButton(selectIcon);
		squareButton = new JToggleButton(squareIcon);
		textButton = new JToggleButton(textIcon);
		ellipseButton = new JToggleButton(ellipseIcon);
		
		
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
		
		ButtonGroup group = new ButtonGroup();
		group.add(panButton);
		group.add(squareButton);
		group.add(changeButton);
		group.add(textButton);
		group.add(ellipseButton);

		bar.add(panButton);
		bar.add(squareButton);
		bar.add(changeButton);
		bar.add(textButton);
		bar.add(ellipseButton);
	}
	private ImageIcon createImageIcon(String filePath){
		return new ImageIcon(filePath);
	}
}
