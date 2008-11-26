package org.luz.debug;


public class SwingDebugger implements DebugInterface {
	
	private DebugFrame frame;
	
	public SwingDebugger() {
		frame = new DebugFrame();
	}
	public void debug(Object debug) {
		frame.appendTextDebug(debug);
	}
	public void error(Exception debug) {
		frame.appendTextError(debug.toString());	
		for(int i = debug.getStackTrace().length -1;i>=0;i--) {
			frame.appendTextError(debug.getStackTrace()[i].toString());
		}	
	}
	public void error(Object debug) {
		frame.appendTextError(debug.toString());
	}
	public String getDebugType() {		
		return "Swing Debugger";
	}
}