import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TetrisView extends JFrame  {
	TetrisModel theModel;
	Timer timer;
	
	TetrisView(TetrisModel theModel){
		this.theModel = theModel;
		JPanel tetrisPanel = new Screen(theModel);
		add(tetrisPanel);
		/*
		timer = new Timer(1000, new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent ae) {
		        theModel.getCurrentBlock().moveBlock(1);
		        repaint();
		    }
		});
		
		timer.start();*/
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);
		pack();
	}
	
	void addKeyListener(ActionListener listenForKey){
		this.addKeyListener(listenForKey);
		
	}
	
	void addTimeListener(ActionListener listenForTime){
		timer = new Timer(1000, listenForTime);
		timer.start();
	}
	
	public void drawBoard(TetrisModel theModel){
		for(int i = 0; i<20;  i++){
			for(int j = 0; j<10; j++){
				System.out.print(Integer.toString(theModel.getBoardValue(i, j)));
			}
			System.out.print("\n");
		}
	}
}
