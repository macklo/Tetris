import java.awt.*;
import javax.swing.*;

public class NextTetriminoPanel extends JPanel
{
	TetrisController theController;
	int offset = 5;
	
	NextTetriminoPanel(TetrisController theController)
	{
		this.theController = theController;
		setPreferredSize(new Dimension((2*offset + 6*theController.getModel().getBlockSize()), (2*offset + 6*theController.getModel().getBlockSize())));
		//setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		for(int i = 0; i<4; i++){
			if(theController.getModel().getNextTetrimino() != null && theController.isGameRunning())
			{
				g2d.setColor(theController.getModel().getNextTetrimino().getColor());
				g2d.fill3DRect(		offset + (2 + theController.getModel().getNextTetrimino().getColumn(theController.getModel().getNextTetrimino().getOrientation(), i)) * theController.getModel().getBlockSize(), 
									offset + (2 + theController.getModel().getNextTetrimino().getVerse(theController.getModel().getNextTetrimino().getOrientation(), i)) * theController.getModel().getBlockSize(),
									theController.getModel().getBlockSize(), theController.getModel().getBlockSize(), true);
				//theController.getModel().getCurrentTetrimino().getColor();
			}
		}
	}
}
