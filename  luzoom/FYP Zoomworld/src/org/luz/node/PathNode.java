package org.luz.node;

import java.awt.BasicStroke;
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
		@Override
		public String saveNodeEnd() {
			return "</PathNode>\n";		
		}
		@Override
		public String saveNodeStart() {
			
			return "<PathNode>\n" +
					"\t<Xval x=\""+ getX() + "\"></Xval>\n"  +
					"\t<Yval y=\"" + getY() + "\"></Yval>\n" +
					"\t<Height h=\"" + getHeight() + "\"></Height>\n" +
					"\t<Width w=\"" + getWidth() + "\"></Width>\n" +
					"\t<Scale s=\"" + ((BasicStroke)getStroke()).getLineWidth() + "\"></Scale>";
		}      
}

