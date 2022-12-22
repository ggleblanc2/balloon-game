package com.ggl.balloongame.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.ggl.balloongame.model.Balloon;
import com.ggl.balloongame.model.BalloonGameModel;
import com.ggl.balloongame.view.BalloonGameFrame;

public class BalloonListener extends MouseAdapter {
	
	private final BalloonGameFrame view;
	
	private final BalloonGameModel model;

	public BalloonListener(BalloonGameFrame view, BalloonGameModel model) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void mousePressed(MouseEvent event) {
		if (MouseEvent.BUTTON1 == event.getButton()) {
			if (!model.isGameOver()) {
				Balloon balloon = model.getValidBalloon(event.getPoint());
				if (balloon != null) {
					balloon.expand();
					model.isTouching();
					model.calculateScore();
					view.updateControlPanel();
					view.repaint();
				}
			}
		}
	}

}
