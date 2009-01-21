package org.luz.node;

import java.awt.BasicStroke;

import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolox.handles.PBoundsHandle;
import edu.umd.cs.piccolox.util.PBoundsLocator;

public class BoundsHandle extends PBoundsHandle {

	public BoundsHandle(PBoundsLocator locator,float scale) {
		super(locator);
		setStroke(new BasicStroke(scale));
		setScale(scale);
	}
	
	public static void addBoundsHandlesTo(PNode aNode,float scale) {
		aNode.addChild(new BoundsHandle(PBoundsLocator.createEastLocator(aNode),scale)); 
		aNode.addChild(new BoundsHandle(PBoundsLocator.createWestLocator(aNode),scale)); 
		aNode.addChild(new BoundsHandle(PBoundsLocator.createNorthLocator(aNode),scale)); 
		aNode.addChild(new BoundsHandle(PBoundsLocator.createSouthLocator(aNode),scale));
		aNode.addChild(new BoundsHandle(PBoundsLocator.createNorthEastLocator(aNode),scale)); 
		aNode.addChild(new BoundsHandle(PBoundsLocator.createNorthWestLocator(aNode),scale)); 
		aNode.addChild(new BoundsHandle(PBoundsLocator.createSouthEastLocator(aNode),scale)); 
		aNode.addChild(new BoundsHandle(PBoundsLocator.createSouthWestLocator(aNode),scale)); 	
	}

	public static void addStickyBoundsHandlesTo(PNode aNode, PCamera camera,float scale) {
		camera.addChild(new BoundsHandle(PBoundsLocator.createEastLocator(aNode),scale));
		camera.addChild(new BoundsHandle(PBoundsLocator.createWestLocator(aNode),scale));
		camera.addChild(new BoundsHandle(PBoundsLocator.createNorthLocator(aNode),scale));
		camera.addChild(new BoundsHandle(PBoundsLocator.createSouthLocator(aNode),scale));
		camera.addChild(new BoundsHandle(PBoundsLocator.createNorthEastLocator(aNode),scale));
		camera.addChild(new BoundsHandle(PBoundsLocator.createNorthWestLocator(aNode),scale));
		camera.addChild(new BoundsHandle(PBoundsLocator.createSouthEastLocator(aNode),scale));
		camera.addChild(new BoundsHandle(PBoundsLocator.createSouthWestLocator(aNode),scale));
	}

}
