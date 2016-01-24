import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainMenuScreen extends JPanel{
	/**Odleg³oœæ od krawêdzi okna*/
	int offset = 5;
	
	JButton newGameButton;
	JButton optionsButton;
	JPanel leaderBoards;

	int blockSize;
	
	
	TetrisController theController;
	public MainMenuScreen(TetrisController theController) 
	{
		this.theController = theController;
		newGameButton = new JButton("New Game");
		optionsButton = new JButton("Options");
		try {
			leaderBoards = new HighScorePanel(theController);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		newGameButton.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e) {
				 try {
					theController.startNewGame();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			 }});
		
		optionsButton.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e) {
				 theController.showOptionsScreen();
			 }});
		
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		add(newGameButton);
		add(optionsButton);
		add(leaderBoards);
		setBorder(BorderFactory.createLineBorder(Color.black));
		//setPreferredSize(new Dimension(800, 600));
	}
}
