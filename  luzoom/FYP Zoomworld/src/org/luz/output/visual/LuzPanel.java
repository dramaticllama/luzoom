package org.luz.output.visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;

import org.luz.input.InputEventHandler;

import edu.umd.cs.piccolo.*;
import edu.umd.cs.piccolo.event.PInputEvent;
import edu.umd.cs.piccolo.event.PInputEventListener;
import edu.umd.cs.piccolo.event.PZoomEventHandler;

public class LuzPanel extends PCanvas {
	
	private static final long serialVersionUID = 1L;	
	
	public PLayer layer;
	InputEventHandler defaultHandler;
	
	public LuzPanel() {		
		defaultHandler = new InputEventHandler(this);
		
		setPreferredSize(new Dimension(400,400));
		setBackground(Color.LIGHT_GRAY);
		setPanEventHandler(null);		
		layer = getLayer();
		setZoomEventHandler(new PZoomEventHandler() {
	        public void processEvent(final PInputEvent evt, final int i) {
	                if (evt.isMouseWheelEvent()) {
	                        final double s = 1D - 0.25 * evt.getWheelRotation();
	                        final Point2D p = evt.getPosition();
	                        evt.getCamera().scaleViewAboutPoint(s, p.getX(),p.getY());
	                }
	        }
		});
		addInputEventListener(defaultHandler);
	}	
	public boolean createNewNode(Point2D startRect,double d,double e,PNode parent,Color c) {
		PNode aNode = new PNode();
		aNode.setBounds(startRect.getX(), startRect.getY(),d, e);
		aNode.setPaint(c);		
		parent.addChild(aNode);
		return true;
	}
	public void save(){
		System.out.println("Save");
	}
}
