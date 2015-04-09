
package org.luz.input;

import java.awt.event.KeyEvent;
import java.util.Collection;
import java.util.Iterator;

import org.luz.node.BoundsHandle;
import org.luz.node.GroupNode;
import org.luz.node.GroupableNode;
import org.luz.node.NodeGroup;
import org.luz.tools.ToolBelt;

import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.event.PInputEvent;
import edu.umd.cs.piccolox.event.PNotificationCenter;
import edu.umd.cs.piccolox.event.PSelectionEventHandler;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.event.PDragSequenceEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;
import edu.umd.cs.piccolo.nodes.PPath;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolo.util.PDimension;
import edu.umd.cs.piccolo.util.PNodeFilter;
import edu.umd.cs.piccolox.handles.PBoundsHandle;

public class SelectionHandler extends PDragSequenceEventHandler {



	public static final String SELECTION_CHANGED_NOTIFICATION = "SELECTION_CHANGED_NOTIFICATION";

	final static int DASH_WIDTH = 5;
	final static int NUM_STROKES = 10;

	private HashMap selection = null; 		// The current selection
	private List selectableParents = null;  // List of nodes whose children can be selected
	private PPath marquee = null;
	private PNode marqueeParent = null; 	 // Node that marquee is added to as a child
	private Point2D presspt = null;
	private Point2D canvasPressPt = null;
	private float strokeNum = 0;
	private Stroke[] strokes = null;
	private HashMap allItems = null;		// Used within drag handler temporarily
	private ArrayList unselectList = null;	// Used within drag handler temporarily
	private HashMap marqueeMap = null;
	private PNode pressNode = null; 		// Node pressed on (or null if none)
	private boolean deleteKeyActive = true; // True if DELETE key should delete selection
	private Paint marqueePaint;
	private float marqueePaintTransparency = 1.0f;

	ToolBelt data;

	@SuppressWarnings("unchecked")
	Collection copy;

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
				if(getSelection().isEmpty()){
					copy = getSelection();				
				}
			}			
		}else if(e.getKeyCode() == KeyEvent.VK_V){			
			if(e.getModifiersEx() == KeyEvent.CTRL_DOWN_MASK){	
				System.out.println("Ctl V");
				if(copy != null) {
					Iterator selectionEn = copy.iterator();
					unselectAll();
					while (selectionEn.hasNext()) {
						System.out.println("Print");
						PNode node = (PNode)selectionEn.next();	
						PNode newNode = (PNode)node.clone();
						newNode.setBounds(newNode.getX() + (10/newNode.getScale()), newNode.getY() + (10/newNode.getScale()), newNode.getWidth(), newNode.getHeight());
						data.getPaintPanel().layer.addChild(newNode);	
						select(newNode);
					}			

				}
			}
		}else if(e.getKeyCode() == KeyEvent.VK_G){
			if(!getSelection().isEmpty()){
				NodeGroup group = new NodeGroup(getSelection());		
				Iterator itor = getSelection().iterator();
				while (itor.hasNext()) {
					GroupableNode n = (GroupableNode)itor.next();
					n.setGroup(group);
				}
			}
		}
		super.keyPressed(e);
	}



	public SelectionHandler(PNode marqueeParent, PNode selectableParent, ToolBelt data) {
		this.data = data;
		this.marqueeParent = marqueeParent;
		this.selectableParents = new ArrayList();
		this.selectableParents.add(selectableParent);
		init();
	}

	protected void init() {
		float[] dash = { DASH_WIDTH, DASH_WIDTH };
		strokes = new Stroke[NUM_STROKES];
		for (int i = 0; i < NUM_STROKES; i++) {
			strokes[i] = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1, dash, i);
		}

		selection = new HashMap();
		allItems = new HashMap();
		unselectList = new ArrayList();
		marqueeMap = new HashMap();
	}

	///////////////////////////////////////////////////////
	// Public static methods for manipulating the selection
	///////////////////////////////////////////////////////

	public void select(Collection items) {
		boolean changes = false;
		Iterator itemIt = items.iterator();
		while (itemIt.hasNext()) {
			PNode node = (PNode)itemIt.next();
			changes |= internalSelect(node);
		}
		if (changes) {
			postSelectionChanged();
		}
	}

	public void select(Map items) {
		select( items.keySet() );
	}

	private boolean internalSelect( PNode node ) {
		if (isSelected(node)) {
			return false;
		}

		selection.put(node, Boolean.TRUE);
		decorateSelectedNode(node);
		return true;
	}

	private void postSelectionChanged()
	{
		PNotificationCenter.defaultCenter().postNotification(SELECTION_CHANGED_NOTIFICATION, this);
	}

	public void select(PNode node) {
		if (internalSelect(node)) {
			postSelectionChanged();
		}
	}

	public void unselect(Collection items) {
		boolean changes = false;
		Iterator itemIt = items.iterator();
		while (itemIt.hasNext()) {
			PNode node = (PNode)itemIt.next();
			changes |= internalUnselect(node);
		}
		if (changes) {
			postSelectionChanged();
		}
	}

	private boolean internalUnselect( PNode node ) {
		if (!isSelected(node)) {
			return false;
		}

		undecorateSelectedNode(node);
		selection.remove(node);
		return true;
	}

	public void unselect(PNode node) {
		if( internalUnselect(node) ) {
			postSelectionChanged();
		}
	}

	public void unselectAll() {
		//  Because unselect() removes from selection, we need to
		//  take a copy of it first so it isn't changed while we're iterating
		ArrayList sel = new ArrayList(selection.keySet());
		unselect( sel );
	}

	public boolean isSelected(PNode node) {
		if ((node != null) && (selection.containsKey(node))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns a copy of the currently selected nodes.
	 */
	public Collection getSelection() 
	{
		ArrayList sel = new ArrayList(selection.keySet());
		return sel;
	}

	/**
	 * Gets a reference to the currently selected nodes.  You should not modify or store
	 * this collection.
	 */
	public Collection getSelectionReference()
	{
		return Collections.unmodifiableCollection( selection.keySet() );
	}

	/**
	 * Determine if the specified node is selectable (i.e., if it is a child
	 * of the one the list of selectable parents.
	 */
	protected boolean isSelectable(PNode node) {
		boolean selectable = false;

		Iterator parentsIt = selectableParents.iterator();
		while (parentsIt.hasNext()) {
			PNode parent = (PNode)parentsIt.next();
			if (parent.getChildrenReference().contains(node)) {
				selectable = true;
				break;
			}
			else if (parent instanceof PCamera) {
				for(int i=0; i<((PCamera)parent).getLayerCount(); i++) {
					PLayer layer = ((PCamera)parent).getLayer(i);	
					if (layer.getChildrenReference().contains(node)) {
						selectable = true;
						break;	
					}
				}
			}
		}
		return selectable;
		
	}

	//////////////////////////////////////////////////////
	// Methods for modifying the set of selectable parents
	//////////////////////////////////////////////////////

	public void addSelectableParent(PNode node) {
		selectableParents.add(node);	
	}
	
	public void removeSelectableParent(PNode node) {		
		selectableParents.remove(node); 
	}
	
	public void setSelectableParent(PNode node) {
		selectableParents.clear();
		selectableParents.add(node);	
	}
	
	public void setSelectableParents(Collection c) {
		selectableParents.clear();
		selectableParents.addAll(c);	
	}

	public Collection getSelectableParents() {
		return new ArrayList(selectableParents);
	}

	////////////////////////////////////////////////////////
	// The overridden methods from PDragSequenceEventHandler
	////////////////////////////////////////////////////////

	protected void startDrag(PInputEvent e) {
		super.startDrag(e);

		initializeSelection(e); 			

		if (isMarqueeSelection(e)) {
			initializeMarquee(e);

			if (!isOptionSelection(e)) {
				startMarqueeSelection(e);
			}
			else {
				startOptionMarqueeSelection(e);
			}
		}
		else {					
			if (!isOptionSelection(e)) {
				startStandardSelection(e);
			} else {
				startStandardOptionSelection(e);
			}
		}
	}

	protected void drag(PInputEvent e) {
		super.drag(e);

		if (isMarqueeSelection(e)) {
			updateMarquee(e);	

			if (!isOptionSelection(e)) {
				computeMarqueeSelection(e);
			}
			else {
				computeOptionMarqueeSelection(e);
			}
		} else {
			dragStandardSelection(e);
		}
	}

	protected void endDrag(PInputEvent e) {
		super.endDrag(e);

		if (isMarqueeSelection(e)) {
			endMarqueeSelection(e); 
		}
		else {
			endStandardSelection(e);
		}		
	}

	////////////////////////////
	// Additional methods
	////////////////////////////

	public boolean isOptionSelection(PInputEvent pie) {
		return pie.isShiftDown();	
	}

	protected boolean isMarqueeSelection(PInputEvent pie) {
		return (pressNode == null); 
	}

	protected void initializeSelection(PInputEvent pie) {
		canvasPressPt = pie.getCanvasPosition();
		presspt = pie.getPosition();
		pressNode = pie.getPath().getPickedNode();
		if (pressNode instanceof PCamera) {
			pressNode = null;
		}		
	}

	protected void initializeMarquee(PInputEvent e) {
		marquee = PPath.createRectangle((float)presspt.getX(), (float)presspt.getY(), 0, 0);
		marquee.setPaint(marqueePaint);
		marquee.setTransparency(marqueePaintTransparency);
		marquee.setStrokePaint(Color.black);
		marquee.setStroke(strokes[0]);
		marqueeParent.addChild(marquee);			

		marqueeMap.clear();
	}

	protected void startOptionMarqueeSelection(PInputEvent e) { 
	}

	protected void startMarqueeSelection(PInputEvent e) {
		unselectAll();
	}

	protected void startStandardSelection(PInputEvent pie) {
		// Option indicator not down - clear selection, and start fresh
		if (!isSelected(pressNode)) {
			unselectAll();

			if (isSelectable(pressNode)) {
				select(pressNode);
			}
		}		
	}

	protected void startStandardOptionSelection(PInputEvent pie) {
		// Option indicator is down, toggle selection
		if (isSelectable(pressNode)) {
			if (isSelected(pressNode)) {
				unselect(pressNode);
			} else {
				select(pressNode);
			}
		}		
	}

	protected void updateMarquee(PInputEvent pie) {
		PBounds b = new PBounds();

		if (marqueeParent instanceof PCamera) {
			b.add(canvasPressPt);
			b.add(pie.getCanvasPosition());
		}
		else {
			b.add(presspt);
			b.add(pie.getPosition());
		}

		marquee.globalToLocal(b);
		marquee.setPathToRectangle((float) b.x, (float) b.y, (float) b.width, (float) b.height);				
		b.reset();
		b.add(presspt);
		b.add(pie.getPosition());

		allItems.clear();
		PNodeFilter filter = createNodeFilter(b);
		Iterator parentsIt = selectableParents.iterator();
		while (parentsIt.hasNext()) {
			PNode parent = (PNode) parentsIt.next();

			Collection items;
			if (parent instanceof PCamera) {
				items = new ArrayList();
				for(int i=0; i<((PCamera)parent).getLayerCount(); i++) {
					((PCamera)parent).getLayer(i).getAllNodes(filter,items);	
				}
			}
			else {
				items = parent.getAllNodes(filter, null);
			}

			Iterator itemsIt = items.iterator();
			while (itemsIt.hasNext()) {
				allItems.put(itemsIt.next(), Boolean.TRUE);
			}
		}
	}

	protected void computeMarqueeSelection(PInputEvent pie) {
		unselectList.clear();
		// Make just the items in the list selected
		// Do this efficiently by first unselecting things not in the list
		Iterator selectionEn = selection.keySet().iterator();
		while (selectionEn.hasNext()) {
			PNode node = (PNode) selectionEn.next();
			if (!allItems.containsKey(node)) {
				unselectList.add(node);
			}
		}
		unselect(unselectList);

		// Then select the rest
		selectionEn = allItems.keySet().iterator();
		while (selectionEn.hasNext()) {
			PNode node = (PNode) selectionEn.next();
			if (!selection.containsKey(node) && !marqueeMap.containsKey(node) && isSelectable(node)) {
				marqueeMap.put(node,Boolean.TRUE);				
			}
			else if (!isSelectable(node)) {
				selectionEn.remove();
			}
			GroupableNode nodeG = (GroupableNode)node;
			if(nodeG.isGrouped()){
				System.out.println("bear");
				Iterator<GroupableNode> group = nodeG.getNodeGroup().getGroup().iterator();
				while(group.hasNext()){
					allItems.put((PNode)group.next(),Boolean.TRUE);
				}					
			}
		}		
		select(allItems);		
	}

	protected void computeOptionMarqueeSelection(PInputEvent pie) {
		unselectList.clear();
		Iterator selectionEn = selection.keySet().iterator();
		while (selectionEn.hasNext()) {
			PNode node = (PNode) selectionEn.next();
			if (!allItems.containsKey(node) && marqueeMap.containsKey(node)) {
				marqueeMap.remove(node);
				unselectList.add(node);
			}
		}
		unselect(unselectList);


		// Then select the rest
		selectionEn = allItems.keySet().iterator();
		while (selectionEn.hasNext()) {
			PNode node = (PNode) selectionEn.next();
			if (!selection.containsKey(node) && !marqueeMap.containsKey(node) && isSelectable(node)) {
				marqueeMap.put(node,Boolean.TRUE);
			}
			else if (!isSelectable(node)) {
				selectionEn.remove();
			}
		}

		select(allItems);	
	}

	protected PNodeFilter createNodeFilter(PBounds bounds) {
		return new BoundsFilter(bounds);	
	}

	protected PBounds getMarqueeBounds() {
		if (marquee != null) {
			return marquee.getBounds();
		}	
		return new PBounds();
	}

	protected void dragStandardSelection(PInputEvent e) {
		// There was a press node, so drag selection
		PDimension d = e.getCanvasDelta();
		e.getTopCamera().localToView(d);

		PDimension gDist = new PDimension();
		Iterator selectionEn = getSelection().iterator();
		while (selectionEn.hasNext()) {
			PNode node = (PNode) selectionEn.next();

			gDist.setSize(d);
			node.getParent().globalToLocal(gDist);
			node.offset(gDist.getWidth(), gDist.getHeight());
		}		
	}

	protected void endMarqueeSelection(PInputEvent e) {
		// Remove marquee
		marquee.removeFromParent();
		marquee = null; 		
	}

	protected void endStandardSelection(PInputEvent e) {
		pressNode = null;		
	}

	/**
	 * This gets called continuously during the drag, and is used to animate the marquee
	 */
	protected void dragActivityStep(PInputEvent aEvent) {
		if (marquee != null) {
			float origStrokeNum = strokeNum;
			strokeNum = (strokeNum + 0.5f) % NUM_STROKES;	// Increment by partial steps to slow down animation
			if ((int)strokeNum != (int)origStrokeNum) {
				marquee.setStroke(strokes[(int)strokeNum]);
			}
		}
	}

	public boolean getSupportDeleteKey() {
		return deleteKeyActive;
	}

	public boolean isDeleteKeyActive() {
		return deleteKeyActive;
	}

	/**
	 * Specifies if the DELETE key should delete the selection
	 */
	public void setDeleteKeyActive(boolean deleteKeyActive) {
		this.deleteKeyActive = deleteKeyActive;
	}

	//////////////////////
	// Inner classes
	//////////////////////

	protected class BoundsFilter implements PNodeFilter {
		PBounds localBounds = new PBounds();
		PBounds bounds;

		protected BoundsFilter(PBounds bounds) {
			this.bounds = bounds;
		}

		public boolean accept(PNode node) {
			localBounds.setRect(bounds);
			node.globalToLocal(localBounds);

			boolean boundsIntersects = node.intersects(localBounds);
			boolean isMarquee = (node == marquee);
			return (node.getPickable() && boundsIntersects && !isMarquee && !selectableParents.contains(node) && !isCameraLayer(node));
		}

		public boolean acceptChildrenOf(PNode node) {
			return selectableParents.contains(node) || isCameraLayer(node);
		}

		public boolean isCameraLayer(PNode node) {
			if (node instanceof PLayer) {
				for(Iterator i=selectableParents.iterator(); i.hasNext();) {
					PNode parent = (PNode)i.next();
					if (parent instanceof PCamera) {
						if (((PCamera)parent).indexOfLayer((PLayer)node) != -1) {
							return true;	
						}
					}
				}	
			}
			return false;
			
		}
	}

	/**
	 * Indicates the color used to paint the marquee.
	 * @return the paint for interior of the marquee
	 */
	public Paint getMarqueePaint() 
	{
		return marqueePaint;
	}

	/**
	 * Sets the color used to paint the marquee.
	 * @param paint the paint color
	 */
	public void setMarqueePaint(Paint paint)
	{
		this.marqueePaint = paint;
	}

	/**
	 * Indicates the transparency level for the interior of the marquee.
	 * @return Returns the marquee paint transparency, zero to one
	 */
	public float getMarqueePaintTransparency()
	{
		return marqueePaintTransparency;
	}

	/**
	 * Sets the transparency level for the interior of the marquee.
	 * @param marqueePaintTransparency The marquee paint transparency to set.
	 */
	public void setMarqueePaintTransparency(float marqueePaintTransparency) 
	{
		this.marqueePaintTransparency = marqueePaintTransparency;
	}
}