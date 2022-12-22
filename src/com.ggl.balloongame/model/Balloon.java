package com.ggl.balloongame.model;

import java.awt.Color;
import java.awt.Point;

public class Balloon {
	
	private int radius;
	
	private Color color;
	
	private final Point centerPoint;

	public Balloon(Point centerPoint, Color color) {
		this.centerPoint = centerPoint;
		this.color = color;
		this.radius = 10;
	}
	
	public void expand() {
		this.radius += (int) (Math.random() * 6 + 4);
	}
	
	public boolean isTouching(Balloon other) {
		Point otherP = other.getCenterPoint();
		Point thisP = getCenterPoint();
		double dx = otherP.x - thisP.x;
		double dy = otherP.y - thisP.y;
		double touchingDist = other.getRadius() + this.getRadius();
		return (Math.hypot(dx, dy) < touchingDist);
	}
	
	public int getSize() {
		return (int) ((this.radius * this.radius * Math.PI) / 100.0);
	}

	public int getRadius() {
		return radius;
	}

	public Color getColor() {
		return color;
	}

	public Point getCenterPoint() {
		return centerPoint;
	}

}
