import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.security.KeyStore.Entry;
import java.util.*;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

//import TetrisController.TimeListener;

//import TetrisController.KeyboardListener;

public class TetrisController {
	
	enum Rotation {CLOCKWISE, COUNTER_CLOCKWISE};
	enum Direction {LEFT, RIGHT, DOWN};
	
	/**Determinuje wyœwietlanie ducha*/
	boolean ghostEnabled = true;
	
	/**Okreœla czy skalowanie trudnoœci jest w³¹czone*/
	boolean difficultyScalingEnabled = true;
	
	/**Okresla jak szybko wzrasta trudnosc (im wieksza tym trudnosc wzrasta wolniej)*/
	int difficultyProportion = 50;
	
	/**Referencja na model*/
	TetrisModel theModel;
	
	/**Referencja na widok*/
	TetrisView theView;
	
	/** True jesli trwa gra*/
	boolean gameRunning = false;
	
	/**Zwraca referencje do modelu*/
	TetrisModel getModel()
	{
		return theModel;
	}
	
	/** Zwraca informacje o tym czy gra chodzi*/
	boolean isGameRunning()
	{
		return gameRunning;
	}
	
	/** Ustawia stan gry*/
	void setGameRunning(boolean b)
	{
		gameRunning = b;
	}
	
	/** Zwraca informacjê czy wyœwietlaæ ducha*/
	boolean isGhostEnabled()
	{
		return ghostEnabled;
	}
	
	/** Ustawia wyœlwietlanie ducha*/
	void setGhostEnabled(boolean b)
	{
		ghostEnabled = b;
	}
	
	/** Zwraca iformacje czy skalowanie trudnoœci jest w³¹czone*/
	boolean isDifficultyScalingEnabled()
	{
		return difficultyScalingEnabled;
	}
	
	/**Ustawia skalowanie trudnoœci*/
	public void setDifficultyScaling(boolean b)
	{
		difficultyScalingEnabled = b;
	}
	
	/**Konstruktor z okresleniem modelu i widoku*/
	public TetrisController(TetrisModel theModel, TetrisView theView) {
		this.theModel = theModel;
		this.theView = theView;
		
		//this.theView.addKeyListener(new KeyboardListener());
		//this.theView.addTimeListener(new TimeListener());
		//this.theView.addKeyListener(new KeyboardListener());
	}
	

	class TimeListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(!isGameRunning()) return;
			if(!theModel.getCurrentTetrimino().checkMoveCollision(Direction.DOWN)) theModel.getCurrentTetrimino().move(Direction.DOWN);
			else
			{
				theModel.fixToBoard();
				theModel.chceckAndDeleteFullVerses();
				if(difficultyScalingEnabled)
				{
					theView.stopTimer();
					theView.addTimeListener(new TimeListener(), 1000 - (theModel.getPoints()/difficultyProportion));
				}
				if(theModel.createNewTetrimino() == false)
				{
					gameRunning = false;
					try {
						showEndGameDialog();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					theView.removeGameScreen();
					theView.removeInfoScreen();
					theView.createMainMenuScreen(theView.getController());
				}
			}
			theView.drawView();
		}
	}
	

	class MyDispatcher implements KeyEventDispatcher {
        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
        	if (!gameRunning) return false;
        	if(e.getKeyCode()==KeyEvent.VK_LEFT && e.getID() == KeyEvent.KEY_PRESSED && e.getModifiers() == 0)
        	{
				if(!theModel.getCurrentTetrimino().checkMoveCollision(Direction.LEFT)) theModel.getCurrentTetrimino().move(Direction.LEFT);
				theView.drawView();
			}
			
			if(e.getKeyCode()==KeyEvent.VK_RIGHT && e.getID() == KeyEvent.KEY_PRESSED && e.getModifiers() == 0)
			{
				if(!theModel.getCurrentTetrimino().checkMoveCollision(Direction.RIGHT)) theModel.getCurrentTetrimino().move(Direction.RIGHT);
				theView.drawView();
			}
			
			if(e.getKeyCode()==KeyEvent.VK_DOWN && e.getID() == KeyEvent.KEY_PRESSED && e.getModifiers() == 0)
			{
				if(!theModel.getCurrentTetrimino().checkMoveCollision(Direction.DOWN)) theModel.getCurrentTetrimino().move(Direction.DOWN);
				else
				{
					theModel.fixToBoard();
					theModel.chceckAndDeleteFullVerses();
					if(difficultyScalingEnabled)
					{
						theView.stopTimer();
						theView.addTimeListener(new TimeListener(), 1000 - (theModel.getPoints()/difficultyProportion));
					}
					if(theModel.createNewTetrimino() == false)
					{
						gameRunning = false;
						try {
							showEndGameDialog();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						theView.removeGameScreen();
						theView.removeInfoScreen();
						theView.createMainMenuScreen(theView.getController());
						theView.stopTimer();
						
					}
				}
				theView.drawView();
			}
			
			if(e.getKeyCode()==KeyEvent.VK_UP&& e.getID() == KeyEvent.KEY_PRESSED && e.getModifiers() == 0){
				System.out.print("obracam \n");
				if(!theModel.getCurrentTetrimino().checkRotateCollision(Rotation.CLOCKWISE)) theModel.getCurrentTetrimino().rotate(Rotation.CLOCKWISE);
				theView.drawView();
			}
			
			if(e.getKeyCode()==KeyEvent.VK_SPACE&& e.getID() == KeyEvent.KEY_PRESSED && e.getModifiers() == 0){
				theModel.getCurrentTetrimino().setOriginVerse(theModel.getCurrentTetrimino().getGhostVerse());
				theModel.fixToBoard();
				theModel.chceckAndDeleteFullVerses();
				if(difficultyScalingEnabled)
				{
					theView.stopTimer();
					theView.addTimeListener(new TimeListener(), 1000 - (theModel.getPoints())/difficultyProportion);
				}
				if(theModel.createNewTetrimino() == false)
				{
					gameRunning = false;
					try {
						showEndGameDialog();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					theView.removeGameScreen();
					theView.removeInfoScreen();
					theView.createMainMenuScreen(theView.getController());
					theView.stopTimer();
					
				}
				theView.drawView();
			}
			return true;
        }
    }
	
	void showEndGameDialog() throws IOException
	{
		theView.stopTimer();
		if(theModel.getHighScoresMap().lastEntry().getKey() < theModel.getPoints())
		{
			String name = theView.showHighScoreDialog();
			theModel.getHighScoresMap().remove(theModel.getHighScoresMap().lastEntry().getKey());
			theModel.addHighScore(theModel.getPoints(), name);
			theModel.saveHighScoresToFile();
		}
		else theView.showGameOverDialog();
		
	}
	
	public void setModel(TetrisModel theModel)
	{
		this.theModel = theModel;
	}
	
	public void setView(TetrisView theView) 
	{
		this.theView = theView;
	}


	public void startNewGame() throws IOException 
	{
		theModel = new TetrisModel();
		gameRunning = true;
		theView.createGameScreen(this);
		this.theView.removeMainMenuScreen();
		this.theView.addTimeListener(new TimeListener(), 1000);
		theView.addDispatcher(new MyDispatcher());
		
	}
	
	public void returnToMainMenu() throws IOException 
	{
		theView.removeOptionsScreen();
		theView.createMainMenuScreen(theView.getController());
	}
	
	public void showOptionsScreen()
	{
		theView.removeMainMenuScreen();
		theView.createOptionsScreen(this);
	}
	

}
