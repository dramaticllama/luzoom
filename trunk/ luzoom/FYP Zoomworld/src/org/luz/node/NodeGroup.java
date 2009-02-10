package org.luz.node;

import java.util.ArrayList;
import java.util.Collection;

public class NodeGroup {

	private Collection<GroupableNode> group;

	public NodeGroup(Collection<GroupableNode> g){
		group = g;
	}
	public Collection<GroupableNode> getGroup() {
		return group;
	}
	public void addNode(GroupableNode node){
		group.add(node);
	}
}
