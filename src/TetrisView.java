import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
/*
import TetrisController.Direction;
import TetrisController.Rotation;
*/
public class TetrisView {
	/**G³ówne okno*/
	JFrame mainFrame;
	Timer timer;
	
	TetrisController theController;
	
	JPanel gamePanel;
	InfoScreen infoPanel;
	MainMenuScreen menuPanel;
	OptionsScreen optionsPanel;
	
	TetrisView()
	{
		mainFrame = new JFrame();
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLayout(new FlowLayout());
		mainFrame.pack();
		mainFrame.setSize(1200, 1024);
		mainFrame.setVisible(true);
		//mainFrame.setAlwaysOnTop(true); 
	}
	
	void addDispatcher(KeyEventDispatcher dispatcher)
	{
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(dispatcher);
	}
	
	/** Ustawienie kontrolera*/
	public void setController(TetrisController theController)
	{
		this.theController = theController;
	}
	
	/** Ustawienie kontrolera
	 * @return */
	public TetrisController getController()
	{
		return this.theController;
	}
	
	/** Tworzy ekran gry
	 * @throws IOException */
	public void createGameScreen(TetrisController theController) throws IOException
	{
		
		gamePanel = new GameScreen(theController);
		infoPanel = new InfoScreen(theController);
		//mainFrame = new JFrame();
		mainFrame.add(gamePanel);
		mainFrame.add(infoPanel);
		mainFrame.pack();
		//gamePanel.requestFocusInWindow();
		mainFrame.repaint();
		
	}
	
	/** Tworzy ekran informacyjny
	 * @throws IOException */
	public void createInfoScreen(TetrisController theController) throws IOException
	{
		infoPanel = new InfoScreen(theController);
		mainFrame.add(infoPanel);
		mainFrame.pack();
		mainFrame.setSize(1200, 1024);
		mainFrame.repaint();
		
	}
	
	/** Usuwa ekran informacyjny*/
	public void removeInfoScreen()
	{
		mainFrame.remove(infoPanel);
		mainFrame.pack();
		mainFrame.setSize(1200, 1024);
		mainFrame.repaint();
		
	}
	
	/**Tworzy ekran menu g³ównego */
	public void createMainMenuScreen(TetrisController theController)
	{
		menuPanel = new MainMenuScreen(theController);
		mainFrame.add(menuPanel);
		mainFrame.pack();
		//mainFrame.setSize(1200, 1024);
		mainFrame.repaint();
		
	}
	
	/**Usuwa ekran menu g³ównego*/
	public void removeMainMenuScreen()
	{
		mainFrame.remove(menuPanel);
		mainFrame.pack();
		//mainFrame.setSize(1200, 1024);
		mainFrame.repaint();
		
	}
	
	/**Tworzy ekran opcji */
	public void createOptionsScreen(TetrisController theController)
	{
		optionsPanel = new OptionsScreen(theController);
		mainFrame.add(optionsPanel);
		mainFrame.pack();
		//mainFrame.setSize(1200, 1024);
		mainFrame.repaint();
		
	}
	
	/**Usuwa ekran opcji*/
	public void removeOptionsScreen()
	{
		mainFrame.remove(optionsPanel);
		mainFrame.pack();
		//mainFrame.setSize(1200, 1024);
		mainFrame.repaint();
		
	}
	
	/**Aktualizuje zdobyte punkty wyœwietlane w InfoSreen*/
	void updatePoints()
	{
		infoPanel.getPointsLabel().setText("Punkty: " + theController.getModel().getPoints());
	}
	
	
	void drawView(){
		if(theController.isGameRunning()) updatePoints();
		mainFrame.repaint();
	}
	
	void addTimeListener(ActionListener listenForTime, int seconds){
		System.out.print(seconds + " \n");
		timer = new Timer(seconds, listenForTime);
		timer.start();
	}
	
	void stopTimer()
	{
		timer.stop();
	}
	
	void addKeyListener(KeyListener listenForKey){
		mainFrame.addKeyListener(listenForKey);
	}

	public void removeGameScreen() {
		mainFrame.remove(gamePanel);
		mainFrame.pack();
		//mainFrame.setSize(1200, 1024);
		mainFrame.repaint();
		
	}
	
	/**Pokazuje dialog dla skoñczonej gry*/
	public void showGameOverDialog()
	{
		JOptionPane.showMessageDialog(mainFrame, "GAME OVER, you didn't manage to get on the High Scores Board.");
	}
	
	/**Pokazuje dialog do wpisania wysokiego wyniku*/
	public String showHighScoreDialog()
	{
		return JOptionPane.showInputDialog(mainFrame, "CONGRATULATIONS, you managed to get on the High Scores Board. Please insert your name");
	}
	
}
