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
		return "<TextNode>\n" +
		"\t<Text t=\"" + getText() + "\"></Text>\n" +
		"\t<Xval x=\""+ getX() + "\"></Xval>\n"  +
		"\t<Yval y=\"" + getY() + "\"></Yval>\n" +
		"\t<Height h=\"" + getHeight() + "\"></Height>\n" +
		"\t<Width w=\"" + getWidth() + "\"></Width>\n" +
		"\t<Scale s=\"" + scale + "\"></Scale>\n";
	}
	@Override
	public String saveNodeEnd() {
		return "</TextNode>";
	}

}
