package com.ggl.balloongame;

import javax.swing.SwingUtilities;

import com.ggl.balloongame.model.BalloonGameModel;
import com.ggl.balloongame.view.BalloonGameFrame;

public class BalloonGame implements Runnable {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new BalloonGame());
	}

	@Override
	public void run() {
		new BalloonGameFrame(new BalloonGameModel());
	}

}
