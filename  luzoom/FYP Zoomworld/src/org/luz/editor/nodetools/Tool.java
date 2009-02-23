package org.luz.editor.nodetools;

public abstract class Tool {
	
	protected ToolBelt data;
	
	public Tool(ToolBelt data) {
		this.data = data;
	}
	public abstract void toolSwap();
	public abstract void toolActive();
}
