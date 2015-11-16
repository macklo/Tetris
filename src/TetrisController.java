import java.awt.event.*;
import javax.swing.*;

// The Controller coordinates interactions
// between the View and Model

public class TetrisController {
	
	private TetrisView theView;
	private TetrisModel theModel;
	
	public TetrisController(TetrisView theView, TetrisModel theModel) {
		this.theView = theView;
		this.theModel = theModel;
		
		this.theView.addKeyListener(new KeyboardListener());
		this.theView.addTimeListener(new TimeListener());
		
		//Timer timer = new Timer(1000, new TimeListener());
		Block block = new Block(theModel);
		theModel.setCurrentBlock(block);
	}
	
	
	class TimeListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			theModel.getCurrentBlock().moveBlock(1);
			theView.repaint();
		}
		
		
	}
	
	
	
	class KeyboardListener implements KeyListener{

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_LEFT){
				System.out.print("lewo \n");
				theModel.getCurrentBlock().moveBlock(0);
				//theView.drawBoard(theModel);
				theView.repaint();
			}
			
			if(e.getKeyCode()==KeyEvent.VK_RIGHT){
				System.out.print("prawo \n");
				theModel.getCurrentBlock().moveBlock(2);
				//theView.drawBoard(theModel);
				theView.repaint();
			}
			
			if(e.getKeyCode()==KeyEvent.VK_DOWN){
				System.out.print("prawo \n");
				theModel.getCurrentBlock().moveBlock(1);
				//theView.drawBoard(theModel);
				theView.repaint();
			}
			
			if(e.getKeyCode()==KeyEvent.VK_Q){
				System.out.print("obracam w lewo \n");
				theModel.getCurrentBlock().rotate(1);
				//theView.drawBoard(theModel);
				theView.repaint();
			}
			if(e.getKeyCode()==KeyEvent.VK_E){
				System.out.print("obracam w prawo \n");
				theModel.getCurrentBlock().rotate(0);
				//theView.drawBoard(theModel);
				theView.repaint();
			}
			
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

	
		
			
		
	}
	
}