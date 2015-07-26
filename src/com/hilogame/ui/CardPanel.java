package com.hilogame.ui;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.hilogame.model.card.Card;

public class CardPanel extends JPanel {
	public CardPanel(Card card) {
		String imageName = "images/" + card.getImageName() + ".gif";
		Image image;
		try {
			image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(imageName));
			ImageIcon imageIcon = new ImageIcon(image);
			JLabel cardLabel =  new JLabel(imageIcon);
			add(cardLabel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
