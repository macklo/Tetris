import java.awt.Color;

public class TetriminoJ extends Tetrimino{
	
	/** Wspolrzedne danych klockow dla kolejnych orientacji */
	int verse[][] = new int[][]
		{
				{-1,0,1,1},
				{0,1,1,1},
				{-1,-1,0,1},
				{0,0,0,1}
		};
	
	/** Wspolrzedne danych klockow dla kolejnych orientacji */
	int column[][] = new int[][]
		{
			{0,0,-1,0},
			{-1,-1,0,1},
			{-1,0,-1,-1},
			{-1,0,1,1}
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
	
	TetriminoJ(TetrisModel theModel)
	{
		this.theModel = theModel;
		orientationCount = 4;
		orientation = 0;
		originColumn = 5;
		originVerse = 1;
		color = Color.BLUE;
	}
}