package org.luz.editor.nodetools;

import org.luz.editor.input.SquiggleHandler;

public class ScribbleTool extends Tool {

	SquiggleHandler scribble;
	
	public ScribbleTool(ToolBelt data) {
		super(data);
		scribble = new SquiggleHandler(data);		
	}
	public void toolActive() {
		data.getPaintPanel().addInputEventListener(scribble);	
	}
	public void toolSwap() {
		data.getPaintPanel().removeInputEventListener(scribble);	
	}
}
