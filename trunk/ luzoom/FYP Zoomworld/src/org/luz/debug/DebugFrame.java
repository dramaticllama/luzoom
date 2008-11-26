package org.luz.debug;

import java.awt.Dimension;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DebugFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JTextArea out;
	private JScrollPane scroll;
	private JPanel panel;
	private Date d;
	
	public DebugFrame() {
		super("Debugger");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationByPlatform(true);
		setResizable(false);
		
		panel = new JPanel();
		
		this.add(panel);
		
		out = new JTextArea();		
		
		scroll = new JScrollPane(out);		
		scroll.setAutoscrolls(false);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(scroll);
		scroll.setPreferredSize(new Dimension(800,200));
		
		
		setContentPane(panel);
		setVisible(true);
		pack();
		
	}
	public void appendText(Object o) {
		
	}
	@SuppressWarnings("deprecation")
	public void appendTextDebug(Object debug) {
		d = new Date();
		out.append(Integer.toString(d.getDate()) + "-" + Integer.toString(d.getMonth()) + "-" + Integer.toString(d.getHours()) + "-" + Integer.toString(d.getMinutes()) + ":" +Integer.toString(d.getSeconds()) + " Debug: " +debug.toString());
		out.append("\n");
		
	}
	@SuppressWarnings("deprecation")
	public void appendTextError(Object debug) {
		d = new Date();
		out.append(Integer.toString(d.getDate()) + "-" + Integer.toString(d.getMonth()) + "-" + Integer.toString(d.getHours()) + "-" + Integer.toString(d.getMinutes()) + ":" +Integer.toString(d.getSeconds()) + " Error: " +debug.toString());
		out.append("\n");
		
	}

}
