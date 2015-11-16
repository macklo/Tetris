import java.awt.*;

public class TetrisGame {
	public static void main(String args[]){
		
		TetrisModel theModel = new TetrisModel();
		TetrisView theView = new TetrisView(theModel);
		//Block theBlock = new Block(theModel);
		TetrisController theController = new TetrisController(theView, theModel);
		theView.setVisible(true);

		//theView.drawBoard(theModel);
	}
}
