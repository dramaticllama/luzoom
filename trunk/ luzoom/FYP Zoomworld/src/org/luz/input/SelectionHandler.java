
package org.luz.input;

import java.awt.event.KeyEvent;
import java.util.Collection;
import java.util.Iterator;

import org.luz.node.BoundsHandle;
import org.luz.tools.ToolBelt;

import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.event.PInputEvent;
import edu.umd.cs.piccolox.event.PSelectionEventHandler;
import edu.umd.cs.piccolox.handles.PBoundsHandle;



public class SelectionHandler extends PSelectionEventHandler {

	ToolBelt data;

	@SuppressWarnings("unchecked")
	Collection copy;

	public SelectionHandler(PNode marqueeParent, PNode selectableParent,ToolBelt data) {
		super(marqueeParent, selectableParent);
		this.data = data;
	}

	public void decorateSelectedNode(PNode node) {
		BoundsHandle.addBoundsHandlesTo(node,(float)(1 / data.getPaintPanel().getCamera().getViewScale()));
	}
	public void undecorateSelectedNode(PNode node) {
		BoundsHandle.removeBoundsHandlesFrom(node);
	}
	/**
	 * Delete selection when delete key is pressed (if enabled)
	 */
	@SuppressWarnings("unchecked")
	public void keyPressed(PInputEvent e) {

		if(e.getKeyCode() == KeyEvent.VK_C){
			if(e.getModifiersEx() == KeyEvent.CTRL_DOWN_MASK){				
				if(!super.getSelection().isEmpty()){
					System.out.println("Collection is not empty");
					copy = getSelection();				
				}
			}			
		}
		if(e.getKeyCode() == KeyEvent.VK_V){
			
			if(e.getModifiersEx() == KeyEvent.CTRL_DOWN_MASK){	
				System.out.println("Ctl V");
				if(copy != null) {
					Iterator selectionEn = copy.iterator();
					unselectAll();
					while (selectionEn.hasNext()) {
						System.out.println("Print");
						PNode node = (PNode)selectionEn.next();	
						PNode newNode = (PNode)node.clone();
						data.getPaintPanel().layer.addChild(newNode);	
						select(newNode);
					}					
				}
			}
		}
		super.keyPressed(e);
	}
}

