import java.io.IOException;

public class Tetris {

	public static void main(String[] args) throws IOException {
		
		TetrisModel theModel = new TetrisModel();
		
		TetrisView theView = new TetrisView();
		
		TetrisController theController = new TetrisController(theModel, theView);
		//theController.setView(theView);
		theView.setController(theController);
		theView.createMainMenuScreen(theController);
		//theView.createGameScreen(theController);
		
		//theView.createInfoScreen(theController);
		//theModel.se
		//theView.drawView();
	}

}
