package com.ggl.balloongame.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

import com.ggl.balloongame.controller.BalloonListener;
import com.ggl.balloongame.model.Balloon;
import com.ggl.balloongame.model.BalloonGameModel;

public class DrawingPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final BalloonGameModel model;

	public DrawingPanel(BalloonGameFrame view, BalloonGameModel model) {
		this.model = model;
		this.setPreferredSize(model.getDrawingPanelDimension());
		this.addMouseListener(new BalloonListener(view, model));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (Balloon balloon : model.getValidBalloons()) {
			g.setColor(balloon.getColor());
			Point p = balloon.getCenterPoint();
			int radius = balloon.getRadius();
			int diameter = radius + radius;
			g.fillOval(p.x - radius, p.y - radius, diameter, diameter);
		}
		
		for (Balloon balloon : model.getPoppedBalloons()) {
			g.setColor(Color.LIGHT_GRAY);
			Point p = balloon.getCenterPoint();
			int radius = balloon.getRadius();
			int diameter = radius + radius;
			g.fillOval(p.x - radius, p.y - radius, diameter, diameter);
		}
	}

}
