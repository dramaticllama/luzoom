package org.luz.input;

import java.awt.geom.Point2D;

import org.luz.node.TextNode;
import org.luz.tools.ToolBelt;
import edu.umd.cs.piccolo.event.PBasicInputEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;
import edu.umd.cs.piccolo.util.PBounds;

public class TextHandler extends PBasicInputEventHandler {	
	
	private ToolBelt data;
	
	public TextHandler(ToolBelt data){
		this.data = data;
	}	
	public void mousePressed(PInputEvent event) {
		
		Point2D startPoint = event.getPosition();		
		TextNode p = new TextNode("Hello");
		double scale = data.getPaintPanel().getCamera().getViewScale();
		
		p.startResizeBounds();		
		p.scale(1/scale);
		p.setBounds(new PBounds((startPoint.getX()*scale), (startPoint.getY()*scale), p.getFullBoundsReference().width*scale, p.getFullBoundsReference().height*scale));
		p.signalBoundsChanged();
		p.endResizeBounds();
		p.saveNodeStart();
		data.getPaintPanel().layer.addChild(p);		
	}
}
