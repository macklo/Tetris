import java.awt.Color;

public class TetriminoO extends Tetrimino{
	
	/** Wspolrzedne danych klockow dla kolejnych orientacji */
	int verse[][] = new int[][]
		{
				{-1,-1,0,0}
		};
	
	/** Wspolrzedne danych klockow dla kolejnych orientacji */
	int column[][] = new int[][]
		{
			{0,1,0,1},

		};
	
	/** Okreslenie koloru danego Tetrimino*/
	
	/** Zwraca wiersz kolejnych BlockPiece tworz�cych Tetrimino*/
	int getVerse(int orientation, int i)
	{
		return verse[orientation][i];
	}
	
	/** Zwraca kolumn� i-tego BlockPiece tworz�cego Tetrimino*/
	int getColumn(int orientation, int i)
	{
		return column[orientation][i];
	}
	
	TetriminoO(TetrisModel theModel)
	{
		this.theModel = theModel;
		orientationCount = 1;
		orientation = 0;
		originColumn = 4;
		originVerse = 1;
		color = Color.yellow;
	}
}
