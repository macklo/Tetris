import java.awt.Color;

public class TetriminoI extends Tetrimino{
	
	/** Wspolrzedne danych klockow dla kolejnych orientacji */
	int verse[][] = new int[][]
		{
				{0,0,0,0},
				{-1,0,1,2}
		};
	
	/** Wspolrzedne danych klockow dla kolejnych orientacji */
	int column[][] = new int[][]
		{
				{-2,-1,0,1},
				{0,0,0,0}
		};
	
	/** Okreslenie koloru danego Tetrimino*/
	
	/** Zwraca wiersz kolejnych BlockPiece tworz¹cych Tetrimino*/
	int getVerse(int orientation, int i)
	{
		return verse[orientation][i];
	}
	
	/** Zwraca kolumnê i-tego BlockPiece tworz¹cego Tetrimino*/
	int getColumn(int orientation, int i)
	{
		return column[orientation][i];
	}
	
	TetriminoI(TetrisModel theModel)
	{
		this.theModel = theModel;
		orientationCount = 2;
		orientation = 1;
		originColumn = 5;
		originVerse = 1;
		color = Color.CYAN;
	}
}
