import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

/** Klasa reprezentuj¹ca jeden element klocka lub planszy */
public class BlockPiece extends JComponent
{
	/**Wiersz danego klocka*/
	int verse;
	
	/**Kolumna danego klocka*/
	int column;
	
	/**Rozmiar klocka*/
	int blockSize = 20;
	
	/** Kolor danego elementu*/
	Color color;
	
	
	/** Zwraca kolor danego klocka*/
	public Color getColor(){
		return color;
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	
	/** Konstruktor z nadaniem koloru*/
	BlockPiece(Color c){
		color = c;

	}
	

	
	public BlockPiece(int i, int j) {
		verse = i;
		column = j;
	}

	/**Rysowanie*/
	protected void paintComponent(Graphics g)
	{/*
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		Rectangle2D rectangle = new Rectangle2D.Double(	verse*blockSize, 
														column*blockSize, 
														blockSize, 
														blockSize );
		g2d.draw(rectangle);
		*/
	}
}
