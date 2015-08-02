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

import com.hilogame.model.Card;

public class CardPanel extends JPanel {
	
	public CardPanel() {
	reset();
	}
	public void setCard(Card card) {
		String imageName = "images/" + card.getImageName() + ".gif";
		setImage(imageName);
	}

public void reset(){
		String imageName = "images/b2fv.gif";
		setImage(imageName);
	}
	private void setImage(String imageName) {
		Image image;
		try { 
			image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(imageName));
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					ImageIcon imageIcon = new ImageIcon(image);
					JLabel cardLabel =  new JLabel(imageIcon);
					removeAll();
					add(cardLabel);
					revalidate();
				}
			});
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
