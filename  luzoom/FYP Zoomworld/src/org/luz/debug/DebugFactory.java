package org.luz.debug;

public class DebugFactory {

	public static  DebugInterface debugger;
	public static boolean debug = true;
	
	public static DebugInterface getDebugger() {
		if(debug == false) {
			debugger = new DebugFalse();
		}
		if(debugger == null) {
			debugger = new SwingDebugger();
		}
		return debugger;
	}	 
}
