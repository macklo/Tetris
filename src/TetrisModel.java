import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.swing.JLabel;


public class TetrisModel {
	//TetrisController theController;
	FileReader highScoresFileReader;
	FileWriter highScoresFileWriter;
	
	
	
	/** Rozmiar klocka*/
	int blockSize;
	
	/**Ilosc wierszy planszy*/
	int verses;
	
	/**Ilosc kolumn planszy*/
	int columns;
	
	/** Ilosc punktow*/
	int points;
	
	/**Plansza */
	BlockPiece Board[][] = new BlockPiece[20][10];
	
	/** Obecny klocek*/
	Tetrimino currentTetrimino;
	
	/** Nastepny klocek*/
	Tetrimino nextTetrimino;
	
	/** Kolekcja przechowujaca wysokie wyniki*/
	NavigableMap<Integer, List<String>> highScores = new TreeMap<Integer, List<String>>(Collections.reverseOrder());
	
	
	void addHighScore(int points, String name)
	{
		if(highScores.containsKey(points))
		{
			highScores.get(points).add(name);
		}
		else
		{
			List<String> lista= new ArrayList();
			lista.add(name);
			highScores.put(points, lista);
		}
	}
	
	 /**Zwraca referencje na kolekcjê wysokich wyników*/
	 NavigableMap<Integer, List<String>> getHighScoresMap()
	 {
		 return highScores;
	 }
	 
	 /** Zapisuje wysokie wyniki do pliku
	 * @throws IOException */
	 public void saveHighScoresToFile() throws IOException
	 {
		 highScoresFileWriter = new FileWriter("HighScores.txt");
		 BufferedWriter bw = new BufferedWriter(highScoresFileWriter);
		 for(Entry<Integer, List<String>> entry : highScores.entrySet()) 
		 {
			 for (String temp : entry.getValue()) 
			 {
				 bw.write(entry.getKey() + " " + temp);
				 bw.newLine();
			 }
		 }
		 bw.close();
		 highScoresFileWriter.close();
	 }
	 
	
	
	/**Zwraca losowe tetrimino*/
	Tetrimino getRandomTetrimino()
	{
		Random rand = new Random(); 
		int value = rand.nextInt(7);
		
		switch(value){
		case(0): return new TetriminoI(this);
		case(1): return new TetriminoT(this);
		case(2): return new TetriminoO(this);	
		case(3): return new TetriminoL(this);
		case(4): return new TetriminoJ(this);
		case(5): return new TetriminoS(this);
		case(6): return new TetriminoZ(this);
		default: return new TetriminoI(this);
		}
	}
	
	/**Zwraca obecne tetrimino*/
	Tetrimino getCurrentTetrimino()
	{
		return currentTetrimino;
	}
	
	/**Zwraca nastêpne tetrimino*/
	Tetrimino getNextTetrimino()
	{
		return nextTetrimino;
	}
	
	/**Tworzy nowe tetrimino zwraca informacje czy sie powiodlo (czy nie wystapila kolizja)*/
	boolean createNewTetrimino()
	{
		if(nextTetrimino.checkSpawnCollision() == true) return false;
		currentTetrimino = nextTetrimino;
		nextTetrimino = this.getRandomTetrimino();
		return true;
	}
	
	/**Zwraca klocek w danych wspó³rzêdnych*/
	BlockPiece getBlockPiece(int verse, int column)
	{
		return Board[verse][column];
	}
	
	/**Zwacanie rozmiaru klocka*/
	int getBlockSize()
	{
		return blockSize;
	}
	
	/**Przymocowanie tetrimino do planszy*/
	void fixToBoard(){
		for(int i = 0; i<4; i++)
		{
			Board[currentTetrimino.originVerse + currentTetrimino.getVerse(currentTetrimino.getOrientation(), i)][currentTetrimino.originColumn + currentTetrimino.getColumn(currentTetrimino.getOrientation(),i)] = new BlockPiece(currentTetrimino.getColor());
		}
	}
	
	/**Zwraca liczbe punktow*/
	int getPoints()
	{
		return points;
	}
	
	/**Usuwa dany wiersz i przesuwa pozosta³e*/
	public void deleteVerse(int verse)
	{
		for (int v = verse - 1; v>=0; v--)
		{
			for (int c = 0; c<=9; c++)
			{
				Board[v+1] [c] = this.getBlockPiece(v, c);
				//points = points + 1;
			}
		}
	}
	
	/** Sprawdza pe³ne wiersze i usuwa je*/
	public void chceckAndDeleteFullVerses(){
		int counter = 0;
		for(int v = 19; v>=0; v--)
		{
			for (int c = 0; c<=9; c++)
			{
				if(this.getBlockPiece(v, c) == null) break;
				else
				{
					if (c == 9) 
					{
						counter++;
						this.deleteVerse(v);
						v=v+1;
					}
				}
			}
		}
		points = points + counter * counter * 100;
	}
	
	TetrisModel() throws IOException{
		blockSize = 40;
		verses = 20;
		columns = 10;
		points = 0;
		
		currentTetrimino = getRandomTetrimino();
		nextTetrimino = getRandomTetrimino();
		
		highScoresFileReader = new FileReader("HighScores.txt");
		StreamTokenizer st = new StreamTokenizer(highScoresFileReader);
		int points, stval;
		String name;
		while( (stval = st.nextToken()) != StreamTokenizer.TT_EOF ){
			points = (int) st.nval;
			stval = st.nextToken();
			name = st.sval;
			this.addHighScore(points, name);
       }
	}
}
