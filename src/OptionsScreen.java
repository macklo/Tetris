import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

public class OptionsScreen extends JPanel{
	/**Odleg³oœæ od krawêdzi okna*/
	int offset = 5;
	
	JButton BackButton;

	int blockSize;
	
	
	TetrisController theController;
	public OptionsScreen(TetrisController theController) 
	{
		this.theController = theController;
		JCheckBox chckGhost = new JCheckBox("Show ghost");
		JCheckBox chckDifficulty = new JCheckBox("Increase difficulty with points");
		BackButton = new JButton("Return to main menu");
		
		chckGhost.setSelected(theController.isGhostEnabled());
		chckDifficulty.setSelected(theController.isDifficultyScalingEnabled());
		chckGhost.addItemListener(new ItemListener() {
			@Override
	         public void itemStateChanged(ItemEvent e) {         
	            if(e.getStateChange()==1)
	            {
	            	theController.setGhostEnabled(true);
	            }
	            else theController.setGhostEnabled(false);
	         }
			         
	      });
		
		chckDifficulty.addItemListener(new ItemListener() {
			@Override
	         public void itemStateChanged(ItemEvent e) {         
	            if(e.getStateChange()==1)
	            {
	            	theController.setDifficultyScaling(true);
	            }
	            else theController.setDifficultyScaling(false);
	         }
			         
	      });
		
		BackButton.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e) {
				 try {
					theController.returnToMainMenu();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			 }});
		
		setLayout(new FlowLayout(FlowLayout.CENTER));
		add(chckGhost);
		add(chckDifficulty);
		add(BackButton);
		
		setBorder(BorderFactory.createLineBorder(Color.black));
		//setPreferredSize(new Dimension(800, 600));
	}
}