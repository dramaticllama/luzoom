package org.luz.node;

import edu.umd.cs.piccolo.nodes.PText;

public class TextNode extends PText implements PersistentNode{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6213275921417362580L;

	public TextNode(String aText) {
		super(aText);
	}
	@Override
	public String saveNodeStart() {
		return "<TextNode text=\""+ getText() + "\",x=\""+ getX() + "\",y=\"" + getY() + "\",height=\"" + getHeight() + "\"width=\"" + getWidth() + "\"scale=\"" + getScale()+ "\">";
		
	}
	@Override
	public String saveNodeEnd() {
		return "</TextNode>";
	}

}
