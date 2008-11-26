package org.luz.output.visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;

import edu.umd.cs.piccolo.*;

public class LuzPanel extends PCanvas {
	
	private static final long serialVersionUID = 1L;	
	public PLayer layer;
	
	public LuzPanel() {		
		setPreferredSize(new Dimension(400,400));
		setBackground(Color.LIGHT_GRAY);
		setPanEventHandler(null);		
		layer = getLayer();
	}	
	public boolean createNewNode(Point2D startRect,double d,double e,PNode parent,Color c) {
		PNode aNode = new PNode();
		aNode.setBounds(startRect.getX(), startRect.getY(),d, e);
		aNode.setPaint(c);		
		parent.addChild(aNode);
		return true;
	}
}