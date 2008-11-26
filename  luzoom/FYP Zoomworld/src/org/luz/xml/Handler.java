package org.luz.xml;
import org.luz.debug.DebugFactory;
import org.luz.node.Node;
import org.luz.node.Scenegraph;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class Handler extends DefaultHandler {

	private Scenegraph parseData;
	private Node currentNode;
	private Node parentNode;

	public Handler () {
		super();
		parseData = new Scenegraph();
	}
	public void startDocument ()  {
		DebugFactory.getDebugger().debug("Document Started");
	}
	public void endDocument () {
		DebugFactory.getDebugger().debug("End document");
	}
	public void startElement (String uri, String name, String qName, Attributes atts) {
		if(name.equals("luzFile")) {
			parseData.setName(atts.getValue(2));
			parseData.setDate(atts.getValue(1));
			parseData.setAuthor(atts.getValue(0));
		}else if(name.equals("Node")) {
			if(currentNode == null) {
				currentNode = new Node(atts.getValue(0));
				parentNode = currentNode;
				parseData.setRootNode(currentNode);
			}else {
				currentNode = new Node(atts.getValue(0),parentNode);
				parentNode.addChild(currentNode);
				parentNode = currentNode;

			}
		}
	}
	public void endElement (String uri, String name, String qName) {
		if(name.equals("Node")) {
			currentNode = currentNode.getParent();
			parentNode = currentNode;
		}
	}
	public Scenegraph getScenegraph() {		
		return parseData;
	}

}
