package org.luz.input;


import java.awt.geom.Point2D;
import org.luz.tools.ToolBelt;
import edu.umd.cs.piccolo.event.PBasicInputEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;
import edu.umd.cs.piccolo.nodes.PText;
import edu.umd.cs.piccolo.util.PBounds;



public class TextHandler extends PBasicInputEventHandler {
	
	
	private ToolBelt data;
	
	public TextHandler(ToolBelt data){
		this.data = data;
	}
	
	public void mousePressed(PInputEvent event) {
		// Where the mouse was clicked
		Point2D startPoint = event.getPosition();
		System.out.println(startPoint);
		// Text to add
		PText p = new PText("Hello");
		
		// Signal piccolo to that the bounds are going to change
		p.startResizeBounds();
		// Scale the text
		double scale = data.getPaintPanel().getCamera().getViewScale();
		
		// Change the location of the text to where the mouse was clicked
		p.setBounds(new PBounds(startPoint.getX(), startPoint.getY(), p.getFullBoundsReference().width, p.getFullBoundsReference().height));
		p.scale(1/scale);
		System.out.println(p.getBounds());
		p.signalBoundsChanged();
		p.endResizeBounds();
		// Finally add the node so we don't see a flicker in the top left
		data.getPaintPanel().layer.addChild(p);
		
	}
}
