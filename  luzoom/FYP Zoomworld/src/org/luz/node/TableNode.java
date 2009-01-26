package org.luz.node;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Vector;

import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.nodes.PPath;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolo.util.PPaintContext;

@SuppressWarnings("serial")
public class TableNode extends PNode {

	Vector<TableNode> relations;	
	String name;
	Font font;

	public TableNode(String nodeText,int x,int y,Font f) {
		super();	
		name = nodeText;
		font = f;
		setBounds(x, y,(name.length()*10 + f.getSize()), 15);
		setPaint(Color.BLACK);
		centerBoundsOnPoint(x, y);
	}
	public void addRelation(TableNode n) {
		if(relations == null) {
			relations = new Vector<TableNode>();
		}
		if(n.equals(this)){
			return;
		}
		if(n.containsRelation(this)){
			relations.add(n);
		} else {
			PPath rela = getRelationLine(n);
			if(rela!= null) {
				relations.add(n);
				rela.setPaint(Color.black);
				addChild(rela);
			}
		}
	}
	private boolean containsRelation(TableNode tableNode) {
		for(int i = 0;i<getRelationNumber();i++) {
			if(getRelation(i).equals(tableNode)) {
				return true;
			}
		}
		return false;
	}
	private PPath getRelationLine(TableNode relative) {		
		PBounds b = this.getBoundsReference();
		PBounds tb = relative.getBoundsReference();

		if(b.y + (b.height/2) > tb.y + (tb.height/2)) {
			System.out.println("Up");
			return PPath.createLine((float)(b.x + (b.width/2)),(float)(b.y),(float)(tb.x + (tb.width/2)),(float)(tb.y + tb.height));
		}else if(b.x + (b.width/2) < tb.x + (tb.width/2)){
			System.out.println("Right");
			return PPath.createLine((float)(tb.x ),(float)(tb.y + (tb.height/2)),(float)(b.x + b.width),(float)(b.y + (b.height/2)));
		}else if(b.y + (b.height/2) < tb.y +(tb.height/2)) {
			System.out.println("Down");
			return PPath.createLine((float)(b.x + (b.width/2)),(float)(b.y+b.height),(float)(tb.x + (tb.width/2)),(float)tb.y);
		}else if(b.x + (b.width/2) > tb.x + (tb.width/2)) {
			return PPath.createLine((float)b.x,(float)(b.y + (b.height/2)),(float)(tb.x + tb.width),(float)(tb.y + (tb.height/2)));
		}
		return null;
	}
	public TableNode getRelation(int index) {
		if(relations == null) {
			return null;
		}
		return relations.get(index);
	}
	public int getRelationNumber() {
		if(relations == null) {
			return 0;
		}
		return relations.size();
	}
	@Override
	protected void paint(PPaintContext paintcontext){
		
	}
	@Override
	protected void paintAfterChildren(PPaintContext paintContext) {
		if (getPaint() != null) {
			Graphics2D g2 = paintContext.getGraphics();
			g2.setPaint(getPaint());
			g2.setFont(font);
			PBounds b = getBoundsReference();

			g2.drawRoundRect((int)b.x, (int)b.y, (int)b.width, (int)b.height, 5, 5);
			g2.drawString(name, (int)b.x+(font.getSize()), (int)b.y + (font.getSize()-3));
		}
	}
}
