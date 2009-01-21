package org.luz.output.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.luz.node.PersistentNode;

import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.PNode;


public class LuzIO {

	public static void writeStringLn(String input,FileWriter outputStream) throws IOException {					
		outputStream.write(input + "\n");		
	}
	private static void saveNode(FileWriter outputStream ,PNode p) throws IOException{
		writeStringLn(((PersistentNode)p).saveNodeStart(),outputStream);
		for(int i = 0;i <p.getChildrenCount();i++){
			saveNode(outputStream,p.getChild(i));
		}
		writeStringLn(((PersistentNode)p).saveNodeEnd(),outputStream);
	}
	public static void save(PLayer layer, File selectedFile) throws IOException {
		FileWriter outputStream = null;

		outputStream = new FileWriter(selectedFile);	
		for(int i = 0;i <layer.getChildrenCount();i++){
			saveNode(outputStream,layer.getChild(i));
		}
		if (outputStream != null) {				
			outputStream.close();
		}
	}
}

