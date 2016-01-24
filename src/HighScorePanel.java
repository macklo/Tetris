import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.*;
import java.util.Map.Entry;

import javax.swing.*;
import javax.swing.JPanel;


public class HighScorePanel extends JPanel
{
	
	TetrisController theController;
	int offset = 5;
	
	
	HighScorePanel(TetrisController theController) throws IOException
	{
		this.theController = theController;
		
		JLabel label = new JLabel("Najlepsze wyniki:");
		label.setPreferredSize(new Dimension(2*offset + 6*theController.getModel().getBlockSize(), 20));
		add(label);
		
		for(Entry<Integer, List<String>> entry : theController.getModel().getHighScoresMap().entrySet()) 
		 {
			 for (String temp : entry.getValue()) 
			 {
				 label = new JLabel(entry.getKey() + " " + temp);
				 label.setPreferredSize(new Dimension(2*offset + 6*theController.getModel().getBlockSize(), 20));
				 add(label);
			 }
		 }
		
		setPreferredSize(new Dimension((7*theController.getModel().getBlockSize()), (2*offset + 6*theController.getModel().getBlockSize())));
		//setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		
		
	}
}

