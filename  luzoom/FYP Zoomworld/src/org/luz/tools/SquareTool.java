package org.luz.tools;

import org.luz.input.RectangleDragHandler;


public class SquareTool extends Tool {
	
	RectangleDragHandler drag;

	public SquareTool(ToolBelt data) {
		super(data);
		drag = new RectangleDragHandler(data);
	}

	@Override
	public void toolActive() {
		data.getPaintPanel().addInputEventListener(drag);		
	}
	@Override
	public void toolSwap() {
		data.getPaintPanel().removeInputEventListener(drag);		
	}

}
