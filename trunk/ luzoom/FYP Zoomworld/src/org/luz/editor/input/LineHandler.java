package org.luz.editor.input;

import java.awt.BasicStroke;
import java.awt.geom.Point2D;

import org.luz.editor.nodetools.ToolBelt;
import org.luz.node.PathNode;

import edu.umd.cs.piccolo.event.PBasicInputEventHandler;
import edu.umd.cs.piccolo.event.PDragSequenceEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;
import edu.umd.cs.piccolo.nodes.PPath;
import edu.umd.cs.piccolo.util.PBounds;

public class LineHandler extends PDragSequenceEventHandler {

	protected PPath line;
	private ToolBelt data;
	private Point2D pressPoint;
	private Point2D dragPoint;
	
	public LineHandler(ToolBelt data){
		this.data = data;
	}
	
	public void startDrag(PInputEvent e) {
		super.startDrag(e); 		

		pressPoint = e.getPosition();
		dragPoint = e.getPosition();
		line = new PathNode();
		line.moveTo((float)pressPoint.getX(), (float)pressPoint.getY());
		line.setStroke(new BasicStroke((float)(1/ e.getCamera().getViewScale())));
		data.getPaintPanel().layer.addChild(line);
	}

	public void drag(PInputEvent e) {
		super.drag(e);				
		updateLine(e);
	}

	public void endDrag(PInputEvent e) {
		super.endDrag(e);
		updateLine(e);
		line = null;
	}	
	public void updateLine(PInputEvent aEvent) {	
		dragPoint = aEvent.getPosition();
		line.setPathToPolyline(new Point2D[]{pressPoint,dragPoint});
	}
	
}
