package org.luz.editor.input;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class Reader {
	
	public void startXMLParse(File xmlFile,DefaultHandler eventHandler) {
		try {
			XMLReader xr = XMLReaderFactory.createXMLReader();
			xr.setContentHandler(eventHandler);
			xr.setErrorHandler(eventHandler);
			FileReader inputStream = new FileReader(xmlFile);
			InputSource b = new InputSource(inputStream);
			xr.parse(b);
		} catch (SAXException e) {
			System.out.println("Error createing the XMLReader");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error with the XML source ");
			e.printStackTrace();
		}
	}
}

