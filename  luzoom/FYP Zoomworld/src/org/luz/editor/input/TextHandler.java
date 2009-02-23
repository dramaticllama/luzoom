package org.luz.editor.input;

import java.awt.geom.Point2D;

import javax.swing.JOptionPane;

import org.luz.editor.nodetools.ToolBelt;
import org.luz.node.TextNode;
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
		
		String text = JOptionPane.showInputDialog("Enter your text", null);
		if ((text != null) && (text.length() > 0)) {
			TextNode p = new TextNode(text);
			double scale = data.getPaintPanel().getCamera().getViewScale();			
			p.startResizeBounds();		
			p.scale(1/scale);
			p.setBounds(new PBounds((startPoint.getX()*scale), (startPoint.getY()*scale), p.getFullBoundsReference().width*scale, p.getFullBoundsReference().height*scale));
			p.signalBoundsChanged();
			p.endResizeBounds();
			p.saveNodeStart();
			data.getPaintPanel().layer.addChild(p);		
		    return;
		}		
	}
}
