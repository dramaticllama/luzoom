package org.luz.editor.input;

import java.awt.BasicStroke;
import java.awt.geom.Point2D;

import org.luz.editor.nodetools.ToolBelt;
import org.luz.node.PathNode;

import edu.umd.cs.piccolo.event.PDragSequenceEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;
import edu.umd.cs.piccolo.nodes.PPath;

public class SquiggleHandler extends PDragSequenceEventHandler {

	protected PPath squiggle;
	private ToolBelt data;

	public SquiggleHandler(ToolBelt data){
		this.data = data;
	}

	public void startDrag(PInputEvent e) {
		super.startDrag(e); 		

		Point2D p = e.getPosition();

		squiggle = new PathNode();
		squiggle.moveTo((float)p.getX(), (float)p.getY());
		squiggle.setStroke(new BasicStroke((float)(1/ e.getCamera().getViewScale())));
		data.getPaintPanel().layer.addChild(squiggle);
	}

	public void drag(PInputEvent e) {
		super.drag(e);				
		updateSquiggle(e);
	}

	public void endDrag(PInputEvent e) {
		super.endDrag(e);
		updateSquiggle(e);
		squiggle = null;
	}	

	public void updateSquiggle(PInputEvent aEvent) {
		Point2D p = aEvent.getPosition();
		squiggle.lineTo((float)p.getX(), (float)p.getY());
	}
}


