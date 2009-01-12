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

	protected PathNode rectangle;
	protected Point2D pressPoint;
	protected Point2D dragPoint;

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
		pressPoint = e.getPosition();
		dragPoint = pressPoint; 

		rectangle = new PathNode();
		rectangle.setStroke(new BasicStroke((float)(1/ e.getCamera().getViewScale())));
		layer.addChild(rectangle);
		updateRectangle();
	}
	public void mouseDragged(PInputEvent e) {
		super.mouseDragged(e);
		dragPoint = e.getPosition();	
		updateRectangle();
	}
	public void mouseReleased(PInputEvent e) {
		super.mouseReleased(e);
		updateRectangle();
		rectangle.saveNodeStart();
		rectangle = null;		
	}	
	public void updateRectangle() {
		PBounds b = new PBounds();
		b.add(pressPoint);
		b.add(dragPoint);
		rectangle.setPathTo(b);
	}
}