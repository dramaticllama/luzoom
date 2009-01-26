package org.luz.node;

import java.util.Collection;

import edu.umd.cs.piccolo.PNode;

public class GroupNode extends PNode implements PersistentNode{

	public GroupNode(Collection nodes) {
		
	}
	
	@Override
	public String saveNodeEnd() {
		return "</GroupNode>";
	}

	@Override
	public String saveNodeStart() {
		return "<GroupNode>";
		
	}

}
