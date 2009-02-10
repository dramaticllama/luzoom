package org.luz.node;

public interface GroupableNode {
	
	public abstract NodeGroup getNodeGroup(); 
	public abstract boolean isGrouped();
	public abstract void setGroup(NodeGroup g);
}
