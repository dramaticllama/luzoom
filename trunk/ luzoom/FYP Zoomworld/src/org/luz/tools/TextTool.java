package org.luz.tools;


import edu.umd.cs.piccolox.event.PStyledTextEventHandler;

public class TextTool extends Tool {

	private PStyledTextEventHandler textHandler;
	
	public TextTool(ToolBelt data) {
		super(data);
		textHandler = new PStyledTextEventHandler(data.getPaintPanel());
	}
	public void toolActive() {
		data.getPaintPanel().addInputEventListener(textHandler);
	}
	public void toolSwap() {
		textHandler.stopEditing();
		data.getPaintPanel().removeInputEventListener(textHandler);
	}
}
