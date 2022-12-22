package com.ggl.balloongame.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class BalloonGameModel {
	
	private boolean isGameOver;
	
	private int highScore, score;
	
	private final Dimension drawingPanelDimension;
	
	private final List<Balloon> validBalloons;
	private final List<Balloon> poppedBalloons;

	public BalloonGameModel() {
		this.highScore = 0;
		this.score = 0;
		this.validBalloons = new ArrayList<>();
		this.poppedBalloons = new ArrayList<>();
		this.drawingPanelDimension = new Dimension(800, 640);
		this.isGameOver = false;
	}
	
	public void initialize() {
		this.validBalloons.clear();
		this.poppedBalloons.clear();
		updateHighScore();
		this.score = 0;
		this.isGameOver = false;
	}
	
	public void createBalloons(int count) {
		Color[] colors = { Color.RED, Color.BLUE, Color.MAGENTA, Color.CYAN };
		int border = 150;
		int doubleBorder = border + border;

		int index = 0;
		while (index < count) {
			int colorIndex = (int) (Math.random() * colors.length);
			int x = (int) (Math.random()
					* (drawingPanelDimension.width - doubleBorder) + border);
			int y = (int) (Math.random()
					* (drawingPanelDimension.height - doubleBorder) + border);
			Balloon balloon = new Balloon(new Point(x, y), colors[colorIndex]);
			
			if (!isTouching(balloon)) {
				validBalloons.add(balloon);
				index++;
			}
		}
	}
	
	private boolean isTouching(Balloon balloon) {
		for (Balloon existing : validBalloons) {
			if (existing.isTouching(balloon)) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isTouching() {
		boolean isTouching = false;
		
		int i = validBalloons.size() - 1;
		while (i >= 0) {
			
			int j = validBalloons.size() - 1;
			while (j >= 0) {
				if (i != j) {
					Balloon balloon1 = validBalloons.get(i);
					Balloon balloon2 = validBalloons.get(j);
					if (balloon1.isTouching(balloon2)) {
						isGameOver = true;
						isTouching = true;
						poppedBalloons.add(balloon1);
						poppedBalloons.add(balloon2);
						validBalloons.remove(i);
						validBalloons.remove(j);
					}
				}
				j--;
			}
			i--;
		}

		return isTouching;
	}
	
	public void calculateScore() {
		int increment = 0;
		for (Balloon balloon : validBalloons) {
			increment += balloon.getSize();
		}
		
		int decrement = 0;
		for (Balloon balloon : poppedBalloons) {
			decrement += balloon.getSize();
		}
		
		this.score = increment - decrement;
	}
	
	public void updateHighScore() {
		this.highScore = Math.max(score, highScore);
	}

	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}
	
	public boolean isGameOver() {
		return isGameOver;
	}

	public Dimension getDrawingPanelDimension() {
		return drawingPanelDimension;
	}

	public int getHighScore() {
		return highScore;
	}

	public int getScore() {
		return score;
	}
	
	public Balloon getValidBalloon(Point point) {
		for (Balloon balloon : validBalloons) {
			int radius = balloon.getRadius();
			Point centerPoint = balloon.getCenterPoint();
			double distance = Point.distance(point.x, point.y, centerPoint.x,
					centerPoint.y);
			if (distance <= radius) {
				return balloon;
			}
		}
		
		return null;
	}

	public List<Balloon> getValidBalloons() {
		return validBalloons;
	}

	public List<Balloon> getPoppedBalloons() {
		return poppedBalloons;
	}
	
}
