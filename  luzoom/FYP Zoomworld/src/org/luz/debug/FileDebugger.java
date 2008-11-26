package org.luz.debug;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class FileDebugger implements DebugInterface {
	
	private BufferedWriter out;
	private Date d; 	
	
	@SuppressWarnings("deprecation")
	public FileDebugger() {
		d = new Date();		
		try {
			File dir = new File("debug");
			if(!dir.exists()) {
				dir.mkdir();
			}
			File f = new File( "debug\\" + Integer.toString(d.getDate()) + "-" + Integer.toString(d.getMonth()) + "-" + Integer.toString(d.getHours()) + "-" + Integer.toString(d.getMinutes()) + ".dbg");
			out = new BufferedWriter(new FileWriter(f.getAbsolutePath()));
			out.write("--New Debugger Started--");
			out.newLine();
			out.flush();
		} catch (IOException e) {
			System.out.println("Error createing debug file");
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public void debug(Object debug) {
		if(DebugFactory.debug) {
			try {
				d = new Date();
				out.write(Integer.toString(d.getDate()) + "-" + Integer.toString(d.getMonth()) + "-" + Integer.toString(d.getHours()) + "-" + Integer.toString(d.getMinutes())+ ":" + Integer.toString(d.getSeconds()) + " Debug: " + debug.toString());
				out.newLine();
				out.flush();
			} catch (IOException e) {
				System.out.println("Error debugging: " + debug.toString());
			}
		}
	}

	@SuppressWarnings("deprecation")
	public void error(Object debug) {
		try {
			d = new Date();
			out.write(Integer.toString(d.getDate()) + "-" + Integer.toString(d.getMonth()) + "-" + Integer.toString(d.getHours()) + "-" + Integer.toString(d.getMinutes()) + ":" +Integer.toString(d.getSeconds()) + " Error: " +debug.toString());
			out.newLine();
			out.flush();
		} catch (IOException e) {
			System.out.println("Error debugging: " + debug.toString());
		}

	}	

	public String getDebugType() {		
		return "File Output";
	}

	@SuppressWarnings("deprecation")
	public void error(Exception debug) {	
		try {
			d = new Date();
			out.write(Integer.toString(d.getDate()) + "-" + Integer.toString(d.getMonth()) + "-" + Integer.toString(d.getHours()) + "-" + Integer.toString(d.getMinutes()) + ":" +Integer.toString(d.getSeconds()) + " Error: " +debug.toString());
			out.newLine();
			for(int i = debug.getStackTrace().length -1;i>=0;i--) {
				out.write(Integer.toString(d.getDate()) + "-" + Integer.toString(d.getMonth()) + "-" + Integer.toString(d.getHours()) + "-" + Integer.toString(d.getMinutes()) + ":" +Integer.toString(d.getSeconds()) + " Error: " + debug.getStackTrace()[i].toString());
				out.newLine();
			}			
			out.flush();
		} catch (IOException e) {
			System.out.println("Error debugging: " + debug.toString());
		}
		
	}
}
