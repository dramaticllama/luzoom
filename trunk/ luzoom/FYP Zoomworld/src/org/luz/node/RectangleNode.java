package org.luz.node;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;

import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolo.util.PPaintContext;

public class RectangleNode extends PathNode {

	private double x,y;
	private double width,height;

	private Stroke line;

	public RectangleNode() {
		super();
	}
	public void setStroke(Stroke s) {
		line = s;
	}
	@Override
	public String saveNodeEnd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String saveNodeStart() {
		// TODO Auto-generated method stub
		return null;
	}
	protected void paint(PPaintContext paintContext) {
		Paint p = getPaint();
		Graphics2D g2 = paintContext.getGraphics();
		g2.setPaint(Color.BLACK);
		g2.setStroke(line);
		g2.draw(getBoundsReference());

	}	


}
