package org.luz.input;

import java.awt.BasicStroke;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import org.luz.node.PathNode;
import org.luz.tools.ToolBelt;

import edu.umd.cs.piccolo.event.PBasicInputEventHandler;
import edu.umd.cs.piccolo.event.PDragSequenceEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;
import edu.umd.cs.piccolo.nodes.PPath;

public class PolyLineHandler extends PDragSequenceEventHandler {

	protected PPath line;
	private ToolBelt data;
	private Point2D pressPoint;
	private Point2D dragPoint;
	private ArrayList<Point2D> points;
	
	public PolyLineHandler(ToolBelt data) {
		this.data = data;
	}
	public void startDrag(PInputEvent e) {
		super.startDrag(e); 		

		pressPoint = e.getPosition();
		dragPoint = e.getPosition();
		
		if(e.getButton() == MouseEvent.BUTTON1){
			if(line == null) {
				line = new PPath();
				points = new ArrayList<Point2D>();
				data.getPaintPanel().layer.addChild(line);
				line.setStroke(new BasicStroke((float)(1/ e.getCamera().getViewScale())));
				line.moveTo((float)pressPoint.getX(), (float)pressPoint.getY());
				points.add(e.getPosition());
				points.add(e.getPosition());
			}else {
				points.add(e.getPosition());
				updateLine(e);
			}
		}else if(e.getButton() == MouseEvent.BUTTON3){
			line = null;
		}
	}

	public void drag(PInputEvent e) {
		super.drag(e);				
		if(line != null) {
			updateLine(e);
		}
	}

	public void endDrag(PInputEvent e) {
		super.endDrag(e);
		if(line != null) {
			updateLine(e);
		}
	}	
	public void updateLine(PInputEvent aEvent) {	
		dragPoint = aEvent.getPosition();
		points.get(points.size()-1).setLocation(dragPoint);
		Point2D[] ret = new Point2D[points.size()];
		for (int i = 0; i < points.size();i++) {
			ret[i] = points.get(i);			
		}
		line.setPathToPolyline(ret);
	}
}
