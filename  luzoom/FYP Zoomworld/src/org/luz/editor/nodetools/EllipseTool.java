package org.luz.editor.nodetools;

import org.luz.editor.input.EllipseDragHandler;

public class EllipseTool extends Tool {

	private EllipseDragHandler drag;
	
	public EllipseTool(ToolBelt data) {
		super(data);
		drag = new EllipseDragHandler(data);
	}



    public void toolActive() {
		data.getPaintPanel().addInputEventListener(drag);		
	}
	public void toolSwap() {
		data.getPaintPanel().removeInputEventListener(drag);		
	}
}
