package org.luz.node;

import java.awt.Shape;
import java.awt.Stroke;

import edu.umd.cs.piccolo.nodes.PPath;

@SuppressWarnings("serial")
public class PathNode extends PPath implements PersistentNode {

	public PathNode() {
		super();
	}
	public PathNode(Shape aShape) {
		super(aShape);
	}
	public PathNode(Shape aShape, Stroke aStroke) {
		super(aShape, aStroke);
	}
	public void saveNode() {
		System.out.println("X = "+ getX() + " Y = " + getY() + " Height = " + getHeight() + " Width = " + getWidth() + " Scale = " + getScale());
		
	}	
}
