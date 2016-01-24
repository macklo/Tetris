import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class GameScreen extends JPanel{
	/**Odleg³oœæ od krawêdzi okna*/
	int offset = 5;
	
	/**Rozmiar klocka pobierany z modelu*/
	int blockSize;
	
	/***/
	TetrisController theController;
	
	/**Konstruktor*/
	public GameScreen(TetrisController theController) 
	{
		this.theController = theController;
		blockSize = theController.getModel().getBlockSize();
		setPreferredSize(new Dimension((2*offset + 10*blockSize), (2*offset + 20*blockSize)));
		setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		

		for(int i = 0; i<20;  i++){
			for(int j = 0; j<10; j++){
				if(theController.getModel().getBlockPiece(i, j) != null){
					g2d.setColor(theController.getModel().getBlockPiece(i, j).getColor());
					g2d.fill3DRect(		offset + j *blockSize, 
										offset + i *blockSize,
										blockSize, blockSize, true);
				}
			}
		}
		
		for(int i = 0; i<4; i++){
			if(theController.getModel().getCurrentTetrimino() != null)
			{
				g2d.setColor(theController.getModel().getCurrentTetrimino().getColor());
				g2d.fill3DRect(		offset + (theController.getModel().getCurrentTetrimino().getOriginColumn() + theController.getModel().getCurrentTetrimino().getColumn(theController.getModel().getCurrentTetrimino().getOrientation(), i)) *blockSize, 
									offset + (theController.getModel().getCurrentTetrimino().getOriginVerse() + theController.getModel().getCurrentTetrimino().getVerse(theController.getModel().getCurrentTetrimino().getOrientation(), i)) *blockSize,
									blockSize, blockSize, true);
				//theController.getModel().getCurrentTetrimino().getColor();
			}
		}
		
		//Rysowanie ducha
		for(int i = 0; i<4; i++){
			if(theController.getModel().getCurrentTetrimino() != null && theController.isGhostEnabled())
			{
				g2d.setColor(new Color(theController.getModel().getCurrentTetrimino().getColor().getRed(), theController.getModel().getCurrentTetrimino().getColor().getGreen(), theController.getModel().getCurrentTetrimino().getColor().getBlue(), 100));
				g2d.fill3DRect(		offset + (theController.getModel().getCurrentTetrimino().getOriginColumn() + theController.getModel().getCurrentTetrimino().getColumn(theController.getModel().getCurrentTetrimino().getOrientation(), i)) *blockSize, 
									offset + (theController.getModel().getCurrentTetrimino().getGhostVerse() + theController.getModel().getCurrentTetrimino().getVerse(theController.getModel().getCurrentTetrimino().getOrientation(), i)) *blockSize,
									blockSize, blockSize, true);
				//theController.getModel().getCurrentTetrimino().getColor();
			}
		}
	}
}
