package org.luz.node;

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
			return "<PathNode x=\""+ getX() + "\",y=\"" + getY() + "\",height=\"" + getHeight() + "\"width=\"" + getWidth() + "\"scale=\"" + getScale()+ "\">";
			
		}
		@Override
		public String saveNodeStart() {
			return "</PathNode>";
		}      
}

