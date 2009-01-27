package org.luz.output.visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;

import edu.umd.cs.piccolo.*;
import edu.umd.cs.piccolo.event.PInputEvent;
import edu.umd.cs.piccolo.event.PZoomEventHandler;

@SuppressWarnings("serial")
public class LuzPanel extends PCanvas {		

	public PLayer layer;

	public LuzPanel() {				
		setPreferredSize(new Dimension(400,400));
		setBackground(Color.LIGHT_GRAY);
		setPanEventHandler(null);		
		layer = getLayer();
		setZoomEventHandler(new PZoomEventHandler() {
			public void processEvent(final PInputEvent evt, final int i) {
				if (evt.isMouseWheelEvent()) {
					final double s = 1D - 0.25 * evt.getWheelRotation();
					final Point2D p = evt.getPosition();
					evt.getCamera().scaleViewAboutPoint(s, p.getX(),p.getY());	      
				}
			}
		});
	}	
}
