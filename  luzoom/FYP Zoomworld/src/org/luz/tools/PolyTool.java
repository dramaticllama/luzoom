package org.luz.tools;

import org.luz.input.PolyLineHandler;

public class PolyTool extends Tool {

	PolyLineHandler poly;
	
	public PolyTool(ToolBelt data) {
		super(data);
		poly = new PolyLineHandler(data);
	}
	public void toolActive() {
		data.getPaintPanel().addInputEventListener(poly);

	}
	public void toolSwap() {
		data.getPaintPanel().removeInputEventListener(poly);
	}

}
