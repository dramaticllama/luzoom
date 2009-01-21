package org.luz.node;

import java.awt.BasicStroke;

import edu.umd.cs.piccolo.nodes.PText;

public class TextNode extends PText implements PersistentNode{

	
	private double scale;
	
	@Override
	public void scale(double scale){
		this.scale = scale;
		super.scale(scale);
	}
	
	public TextNode(String aText) {
		super(aText);
	}
	@Override
	public String saveNodeStart() {
		return "<TextNode text=\""+ getText() + "\",x=\""+ getX() + "\",y=\"" + getY() + "\",height=\"" + getHeight() + "\"width=\"" + getWidth() + "\"scale=\"" + scale + "\">";
		
	}
	@Override
	public String saveNodeEnd() {
		return "</TextNode>";
	}

}
