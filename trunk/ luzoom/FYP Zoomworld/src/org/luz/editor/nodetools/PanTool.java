package org.luz.editor.nodetools;

import edu.umd.cs.piccolo.event.PPanEventHandler;

public class PanTool extends Tool{
	
	PPanEventHandler pan;

	public PanTool(ToolBelt data) {
		super(data);
		pan = new PPanEventHandler();
	}
	public void toolActive() {
		data.getPaintPanel().addInputEventListener(pan);		
	}
	public void toolSwap() {
		data.getPaintPanel().removeInputEventListener(pan);		
	}

}
