package org.luz.tools;

import org.luz.output.visual.LuzPanel;

public class ToolBelt {

	private LuzPanel paintable;
	private Tool[] toolbox;
	private int currentTool;	
	
	public ToolBelt(LuzPanel panel ) {
			paintable = panel;
			currentTool = 0;
			initTools();
	}
	public void addPanel(LuzPanel panel) {
		this.paintable = panel;		
	}
	public LuzPanel getPaintPanel() {
		return paintable;
	}
	public Tool getTool() {
		return toolbox[currentTool];		
	}
	public void changeToolBoxState(int i) {
		toolbox[currentTool].toolSwap();
		currentTool = i;
		toolbox[currentTool].toolActive();
	}
	private void initTools() {
		toolbox = new Tool[5];
		toolbox[0] = new PanTool(this);
		toolbox[1] = new SquareTool(this);	
		toolbox[2] = new ChangeTool(this);
		toolbox[3] = new TextTool(this);
		toolbox[4] = new EllipseTool(this);
	}
}
