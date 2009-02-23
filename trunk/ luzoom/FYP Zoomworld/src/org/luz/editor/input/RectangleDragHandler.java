package org.luz.editor.input;

import java.awt.BasicStroke;
import java.awt.event.InputEvent;
import java.awt.geom.Point2D;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import org.luz.editor.nodetools.ToolBelt;
import org.luz.node.PathNode;
import org.luz.node.RectangleNode;

import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.event.PBasicInputEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;
import edu.umd.cs.piccolo.event.PInputEventFilter;
import edu.umd.cs.piccolo.util.PBounds;

public class RectangleDragHandler extends  PBasicInputEventHandler {

	protected RectangleNode rectangle;
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
		System.out.println(e.getPickedNode());
		rectangle = new RectangleNode();
		rectangle.setStroke(new BasicStroke((float)(1/ e.getCamera().getViewScale())));
		if (e.getPickedNode() instanceof PCamera) {
			layer.addChild(rectangle);
		}else {
			e.getPickedNode().addChild(rectangle);
		}	
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
	}	
	public void updateRectangle() {
		PBounds b = new PBounds();
		b.add(pressPoint);
		b.add(dragPoint);
		rectangle.setPathTo(b);
	}
}