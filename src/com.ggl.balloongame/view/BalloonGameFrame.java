package com.ggl.balloongame.view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import com.ggl.balloongame.model.BalloonGameModel;

public class BalloonGameFrame {
	
	private final BalloonGameModel model;
	
	private final DrawingPanel drawingPanel;
	
	private final JFrame frame;
	
	private JSlider slider;
	
	private JTextField highScoreField, scoreField;

	public BalloonGameFrame(BalloonGameModel model) {
		this.model = model;
		this.drawingPanel = new DrawingPanel(this, model);
		this.frame = createAndShowGUI();
	}
	
	private JFrame createAndShowGUI() {
		JFrame frame = new JFrame("Balloon Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(drawingPanel, BorderLayout.CENTER);
		frame.add(createControlPanel(), BorderLayout.EAST);
		
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
		
		return frame;
	}
	
	private JPanel createControlPanel() {
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(0, 5, 5, 5);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		JLabel label = new JLabel("Current score:");
		panel.add(label, gbc);
		
		gbc.gridx++;
		scoreField = new JTextField(10);
		scoreField.setEditable(false);
		scoreField.setHorizontalAlignment(JTextField.TRAILING);
		panel.add(scoreField, gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.insets = new Insets(0, 5, 20, 5);
		label = new JLabel("High score:");
		panel.add(label, gbc);
		
		gbc.gridx++;
		highScoreField = new JTextField(10);
		highScoreField.setEditable(false);
		highScoreField.setHorizontalAlignment(JTextField.TRAILING);
		panel.add(highScoreField, gbc);
		
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.insets = new Insets(0, 5, 5, 5);
		JButton newGameButton = new JButton("New Game");
		newGameButton.addActionListener(event -> {
			model.initialize();
			int count = slider.getValue();
			model.createBalloons(count);
			model.calculateScore();
			updateControlPanel();
			repaint();
		});
		panel.add(newGameButton, gbc);
		
		gbc.insets = new Insets(0, 5, 20, 5);
		gbc.gridy++;
		JButton lockScoreButton = new JButton("Lock Score");
		lockScoreButton.addActionListener(event -> {
			model.setGameOver(true);
			model.calculateScore();
			model.updateHighScore();
			updateControlPanel();
			repaint();
		});
		panel.add(lockScoreButton, gbc);
		
		gbc.insets = new Insets(0, 5, 5, 5);
		gbc.gridy++;
		label = new JLabel("Number of balloons:");
		panel.add(label, gbc);
		
		gbc.insets = new Insets(0, 5, 20, 5);
		gbc.gridy++;
		slider = new JSlider(JSlider.HORIZONTAL, 0, 40, 10);
		slider.setMajorTickSpacing(10);
		slider.setMinorTickSpacing(5);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		panel.add(slider, gbc);
		
		gbc.insets = new Insets(0, 5, 5, 5);
		gbc.gridy++;
		JButton quitButton = new JButton("Quit");
		quitButton.addActionListener(event -> {
			frame.dispose();
			System.exit(0);
		});
		panel.add(quitButton, gbc);
		
		updateControlPanel();
		
		return panel;
	}
	
	public void updateControlPanel() {
		scoreField.setText(String.format("%,d", model.getScore()));
		highScoreField.setText(String.format("%,d", model.getHighScore()));
	}
	
	public void repaint() {
		drawingPanel.repaint();
	}

}
