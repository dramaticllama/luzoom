package org.luz.editor.input;
import java.awt.BasicStroke;
import java.awt.Point;
import java.awt.geom.Point2D;

import org.luz.editor.output.visual.LuzPanel;
import org.luz.node.PathNode;
import org.luz.node.TextNode;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import edu.umd.cs.piccolo.util.PBounds;


public class Handler extends DefaultHandler {

	private LuzPanel p;
	
	private double x;
	private double y;
	private double h;
	private double w;
	private float s;
	private String t;
	
	public Handler (LuzPanel panel) {
		super();
		p = panel;
	}

	public void startDocument(){}
	public void endDocument(){}


	public void startElement (String uri, String name, String qName, Attributes atts) {
		if(name.equals("Xval")) {
			x = Double.parseDouble(atts.getValue(0));
		}else if(name.equals("Yval")) {
			y = Double.parseDouble(atts.getValue(0));
		}else if(name.equals("Height")) {
			h = Double.parseDouble(atts.getValue(0));
		}else if(name.equals("Width")) {
			w = Double.parseDouble(atts.getValue(0));
		}else if(name.equals("Scale")) {
			s = Float.parseFloat(atts.getValue(0));
		}else if(name.equals("Text")) {
			t = atts.getValue(0);
		}
	}


	public void endElement (String uri, String name, String qName) {
		if(name.equals("PathNode")) {
			PathNode n = new PathNode();
			
			PBounds b = new PBounds();
			b.add(new Point((int)x,(int)y));
			b.add(new Point((int)(x+w),(int)(y+h)));
			n.setPathTo(b);
			
			n.setStroke(new BasicStroke(s));
			p.layer.addChild(n);
		}else if(name.equals("TextNode")){
			TextNode n = new TextNode(t);
			n.setBounds(x, y, w, h);
			n.scale(s);
			p.layer.addChild(n);
		}
	}


	public void characters (char ch[], int start, int length) {
	}

}
