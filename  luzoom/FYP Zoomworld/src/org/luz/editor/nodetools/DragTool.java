package org.luz.editor.nodetools;

import edu.umd.cs.piccolo.event.PDragEventHandler;

public class DragTool extends Tool {

	private PDragEventHandler drag;
	
	public DragTool(ToolBelt data) {
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
