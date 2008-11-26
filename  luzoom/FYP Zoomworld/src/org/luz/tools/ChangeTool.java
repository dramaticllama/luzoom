package org.luz.tools;


import edu.umd.cs.piccolox.event.PSelectionEventHandler;

public class ChangeTool extends Tool {

	PSelectionEventHandler selectionEventHandler;
	
	public ChangeTool(ToolBelt data) {
		super(data);
		selectionEventHandler= new PSelectionEventHandler(data.getPaintPanel().layer, data.getPaintPanel().layer);
	}
	public void toolActive() {
		data.getPaintPanel().addInputEventListener(selectionEventHandler);
	}
	public void toolSwap() {
		selectionEventHandler.unselectAll();
		data.getPaintPanel().removeInputEventListener(selectionEventHandler);
	}

}
