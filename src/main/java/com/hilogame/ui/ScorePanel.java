package com.hilogame.ui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.hilogame.model.card.Card;

public class ScorePanel extends JPanel {
	JLabel wonLabel;
	JLabel lostLabel;
	
	public ScorePanel() {
		add(new JLabel("Won"));
		 wonLabel = new JLabel("2");
		add(wonLabel);
		
		add(new JLabel("Lost"));
		lostLabel = new JLabel("0");
		add(lostLabel);
		reset();
	}
	public void updateScores(int wins,int losses) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				wonLabel.setText(wins + "");
				wonLabel.revalidate();
				
				lostLabel.setText(losses + "");
				lostLabel.revalidate();
			}
		});
	}

	public void reset(){
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				wonLabel.setText("0");
				wonLabel.revalidate();
				
				lostLabel.setText("0");
				lostLabel.revalidate();
			}
		});
	}
}
