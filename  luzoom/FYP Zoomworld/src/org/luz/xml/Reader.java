package org.luz.xml;

import java.io.IOException;
import org.luz.debug.DebugFactory;
import org.luz.node.Scenegraph;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.XMLReaderFactory;

public class Reader {
	
	XMLReader xr;

	public  Reader() {
		try {
			xr = XMLReaderFactory.createXMLReader();
			
		} catch (SAXException e) {
			DebugFactory.getDebugger().error("Error createing the XMLReader");
			DebugFactory.getDebugger().error(e);			
		}
	}	
	public Scenegraph startXMLParse(InputSource xmlFile,Handler eventHandler) {
		try {			
			xr.setContentHandler(eventHandler);
			xr.setErrorHandler(eventHandler);
			xr.parse(xmlFile);
		} catch (SAXException e) {
			DebugFactory.getDebugger().error("Error createing the XMLReader");
			DebugFactory.getDebugger().error(e);
		} catch (IOException e) {
			DebugFactory.getDebugger().error("Error with the XML source ");
			DebugFactory.getDebugger().error(e);
		}catch(Exception e) {
			DebugFactory.getDebugger().debug(e);
		}
		return eventHandler.getScenegraph();
	}


}

