package org.luz.tools;

import edu.umd.cs.piccolo.event.PDragEventHandler;

public class SelectTool extends Tool {

	private PDragEventHandler drag;
	
	public SelectTool(ToolBelt data) {
		super(data);
		drag = new PDragEventHandler();
	}
	public void toolActive() {
		data.getPaintPanel().addInputEventListener(drag);
		
	}
	public void toolSwap() {
		data.getPaintPanel().removeInputEventListener(drag);
	}

}
