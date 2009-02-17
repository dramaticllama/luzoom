package org.luz.tools;

import org.luz.input.LineHandler;


public class LineTool extends Tool {

	LineHandler line;
	
	public LineTool(ToolBelt data) {
		super(data);
		line = new LineHandler(data);
	}
	public void toolActive() {
		data.getPaintPanel().addInputEventListener(line);	
	}
	public void toolSwap() {
		data.getPaintPanel().removeInputEventListener(line);	
	}

}
