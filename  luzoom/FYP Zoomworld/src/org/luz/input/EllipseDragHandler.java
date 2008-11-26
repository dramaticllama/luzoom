package org.luz.input;


import java.awt.event.InputEvent;
import java.awt.geom.Point2D;

import org.luz.node.SimpleEllipseNode;
import org.luz.tools.ToolBelt;

import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.event.PBasicInputEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;
import edu.umd.cs.piccolo.event.PInputEventFilter;


public class EllipseDragHandler extends  PBasicInputEventHandler {

	// The rectangle that is currently getting created.
	protected SimpleEllipseNode ellipse;

	// The mouse press location for the current pressed, drag, release sequence.
	protected Point2D pressPoint;

	// The current drag location.
	protected Point2D dragPoint;

	// The Toolbelt store
	public ToolBelt data;
	public PLayer layer; 

	public EllipseDragHandler(ToolBelt data) {
		super();
		this.data = data;
		layer = data.getPaintPanel().layer;
		setEventFilter(new PInputEventFilter(InputEvent.BUTTON1_MASK));
	}

	public void mousePressed(PInputEvent e) {
		super.mousePressed(e);		
		System.out.println("Ellipse mouse pressed");
		// Initialize the locations.
		pressPoint = e.getPosition();
		dragPoint = pressPoint; 			

		// create a new Ellipse and add it to the canvas layer so that
		// we can see it.
		ellipse = new SimpleEllipseNode();
			
		//ellipse.setStroke(new BasicStroke((float)(1/ e.getCamera().getViewScale())));
		layer.addChild(ellipse);
		updateEllipse();
	}

	public void mouseDragged(PInputEvent e) {
		super.mouseDragged(e);
		// update the drag point location.
		dragPoint = e.getPosition();	
		updateEllipse();
	}

	public void mouseReleased(PInputEvent e) {
		super.mouseReleased(e);
		updateEllipse();
		//ellipse.saveNode();
		ellipse = null;
	}	

	public void updateEllipse() {
		ellipse.setBounds(pressPoint.getX(), pressPoint.getY(), dragPoint.getX()-pressPoint.getX(), dragPoint.getY()-pressPoint.getY());
	}
}