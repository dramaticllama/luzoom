package org.luz.editor.input;

import java.awt.BasicStroke;
import java.awt.event.InputEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import org.luz.editor.nodetools.ToolBelt;
import org.luz.node.PathNode;


import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.event.PBasicInputEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;
import edu.umd.cs.piccolo.event.PInputEventFilter;


public class EllipseDragHandler extends  PBasicInputEventHandler {
	protected PathNode ellipse;	
	protected Point2D pressPoint;
	protected Point2D dragPoint;

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
		pressPoint = e.getPosition();
		dragPoint = pressPoint; 			
		
		ellipse = new PathNode(new Ellipse2D.Float());
		ellipse.setStroke(new BasicStroke((float)(1/ e.getCamera().getViewScale())));	
		layer.addChild(ellipse);
		updateEllipse();
	}
	public void mouseDragged(PInputEvent e) {
		super.mouseDragged(e);
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