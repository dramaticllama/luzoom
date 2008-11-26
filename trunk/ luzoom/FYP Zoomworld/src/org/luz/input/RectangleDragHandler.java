package org.luz.input;

import java.awt.BasicStroke;
import java.awt.event.InputEvent;
import java.awt.geom.Point2D;

import org.luz.node.PathNode;
import org.luz.tools.ToolBelt;

import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.event.PBasicInputEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;
import edu.umd.cs.piccolo.event.PInputEventFilter;
import edu.umd.cs.piccolo.util.PBounds;

public class RectangleDragHandler extends  PBasicInputEventHandler {

	// The rectangle that is currently getting created.
	protected PathNode rectangle;

	// The mouse press location for the current pressed, drag, release sequence.
	protected Point2D pressPoint;

	// The current drag location.
	protected Point2D dragPoint;

	// The Toolbelt store
	public ToolBelt data;
	public PLayer layer; 

	public RectangleDragHandler(ToolBelt data) {
		super();
		this.data = data;
		layer = data.getPaintPanel().layer;
		setEventFilter(new PInputEventFilter(InputEvent.BUTTON1_MASK));
	}

	public void mousePressed(PInputEvent e) {
		super.mousePressed(e);			

		// Initialize the locations.
		pressPoint = e.getPosition();
		dragPoint = pressPoint; 			

		// create a new rectangle and add it to the canvas layer so that
		// we can see it.
		rectangle = new PathNode();

		rectangle.setStroke(new BasicStroke((float)(1/ e.getCamera().getViewScale())));
		layer.addChild(rectangle);

		// update the rectangle shape.
		updateRectangle();

	}

	public void mouseDragged(PInputEvent e) {
		super.mouseDragged(e);
			// update the drag point location.
			dragPoint = e.getPosition();	

			// update the rectangle shape.
			updateRectangle();
	}

	public void mouseReleased(PInputEvent e) {
		super.mouseReleased(e);
			// update the rectangle shape.
			updateRectangle();
			rectangle.saveNode();
			rectangle = null;		
	}	

	public void updateRectangle() {
		// create a new bounds that contains both the press and current
		// drag point.
		PBounds b = new PBounds();
		b.add(pressPoint);
		b.add(dragPoint);

		// Set the rectangles bounds.
		rectangle.setPathTo(b);

	}
}

