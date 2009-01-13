package org.luz.input;

import java.awt.event.KeyEvent;
import org.luz.output.visual.LuzPanel;
import edu.umd.cs.piccolo.event.PBasicInputEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;

public class InputEventHandler extends PBasicInputEventHandler{

	private LuzPanel pane;
	
	public InputEventHandler(LuzPanel pane) {
		this.pane = pane;
		System.out.println("Event Handler");
	}
	
	public void keyPressed(PInputEvent event) {
		System.out.println("Press");
		if(event.getKeyCode() == KeyEvent.VK_S){
			if(event.getModifiersEx() == KeyEvent.CTRL_DOWN_MASK){
				pane.save();
			}
		}
	}
}


