import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.*;

public class InfoScreen extends JPanel{
	/**Odleg³oœæ od krawêdzi okna*/
	int offset = 5;
	
	int blockSize;
	
	JLabel pointsLabel = new JLabel("Punkty: 0");
	
	JLabel getPointsLabel()
	{
		return pointsLabel;
	}
	
	TetrisController theController;
	public InfoScreen(TetrisController theController) throws IOException 
	{
		this.theController = theController;
		blockSize = theController.getModel().getBlockSize();
		//setPreferredSize(new Dimension((2*offset + 8*theController.getModel().getBlockSize()), (2*offset + 20*theController.getModel().getBlockSize())));
		setBorder(BorderFactory.createLineBorder(Color.black));
		setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel label = new JLabel("Nastêpny klocek:");
		add(label);
		add(new NextTetriminoPanel(theController));
		add(pointsLabel);
		add(new HighScorePanel(theController));
		setPreferredSize(new Dimension((2*offset + 7*theController.getModel().getBlockSize()), (2*offset + 20*theController.getModel().getBlockSize())));
	}
	
	
}
