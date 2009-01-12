package org.luz.tools;


import org.luz.input.SelectionHandler;

import edu.umd.cs.piccolox.event.PSelectionEventHandler;

public class SelectTool extends Tool {

	PSelectionEventHandler selectionEventHandler;
	
	public SelectTool(ToolBelt data) {
		super(data);
		selectionEventHandler= new SelectionHandler(data.getPaintPanel().layer, data.getPaintPanel().layer,data);
	}
	public void toolActive() {
		data.getPaintPanel().addInputEventListener(selectionEventHandler);
		data.getPaintPanel().getRoot().getDefaultInputManager().setKeyboardFocus(selectionEventHandler);
	}
	public void toolSwap() {
		selectionEventHandler.unselectAll();
		data.getPaintPanel().removeInputEventListener(selectionEventHandler);
		data.getPaintPanel().getRoot().getDefaultInputManager().setKeyboardFocus(null);
	}

}
