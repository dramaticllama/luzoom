package org.luz.editor.nodetools;

import org.luz.editor.input.TextHandler;

public class TextTool extends Tool {

	private TextHandler textHandler;
	
	public TextTool(ToolBelt data) {
		super(data);
		textHandler = new TextHandler(data);
	}
	public void toolActive() {
		data.getPaintPanel().addInputEventListener(textHandler);
	}
	public void toolSwap() {
		data.getPaintPanel().removeInputEventListener(textHandler);
	}
}
