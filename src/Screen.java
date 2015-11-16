import java.awt.*;
import java.awt.geom.*;

import javax.swing.*;

public class Screen extends JPanel{
	int offset = 5;
	int blockSize = 30;
	TetrisModel theModel;
	
	public Screen(TetrisModel theModel) {
		setPreferredSize(new Dimension((2*offset + 10*blockSize), (2*offset + 20*blockSize)));
		this.theModel = theModel;
	}
	/*
	public void paintBlock(int verse, int column){
		Graphics g = null;
		Graphics2D g2d = (Graphics2D) g;
		Rectangle2D rectangle = new Rectangle2D.Double(offset + blockSize, offset, blockSize, blockSize);
		g2d.draw(rectangle);
	}
	*/
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		

		for(int i = 0; i<20;  i++){
			for(int j = 0; j<10; j++){
				if(theModel.getBoardValue(i, j)!=0){
					Rectangle2D rectangle = new Rectangle2D.Double(	offset + j *blockSize, 
																	offset + i *blockSize, 
																	blockSize, 
																	blockSize );
					g2d.draw(rectangle);
				}
			}
		}
		
		/*
		// prostokat
		Rectangle2D rectangle2 = new Rectangle2D.Double(offset, offset, blockSize, blockSize);
		Rectangle2D rectangle1 = new Rectangle2D.Double(offset +blockSize, offset, blockSize, blockSize);
		
		// kolo
		//Ellipse2D circle = new Ellipse2D.Double(10, 10, 380, 380);

		g2d.draw(rectangle2);
		g2d.draw(rectangle1);
		//g2d.draw(circle);*/
	}
}
