package com.hilogame.ui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JWindow;

import com.hilogame.model.card.Card;
import com.hilogame.model.card.Rank;
import com.hilogame.model.card.Suit;

public class HiLoGameUI {
	
	public HiLoGameUI() {
		
		JFrame frame = new JFrame();  //make sure the program exits when the frame closes 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setTitle("HiLo Game UI"); 
		frame.setSize(300,250);
		
		JPanel canvas = new JPanel();
		canvas.setLayout(new BorderLayout());
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(new JButton("New Game"));
		buttonPanel.add(new JButton("End Game"));
		
		canvas.add(buttonPanel,BorderLayout.NORTH);
		
		Card card1 = new Card(Rank.Ace,Suit.Clubs);
		CardPanel cardPanel1 = new CardPanel(card1);
		
		Card card2 = new Card(Rank.Ace,Suit.Clubs);
		CardPanel cardPanel2 = new CardPanel(card2);
		
		canvas.add(cardPanel1,BorderLayout.WEST);
		canvas.add(cardPanel2,BorderLayout.EAST);
		
		frame.getContentPane().add(canvas);
		frame.setVisible(true);
		
	}
	
	public static void main (String[] args){
		new HiLoGameUI();
	}
}
