
public class TetrisModel {
	int verses = 20;
	int clumns = 10;
	int points = 0;
	int Board[][] = new int [20][10];
	Block currentBlock;
	
	
	/**Zwraca wartosc Board w danych wspolrzednych*/
	public int getBoardValue(int verse, int column){
		return Board[verse][column];
	}
	
	public void setBoardValue(int verse, int column, int value){
		Board[verse][column] = value;
	}
	
	public Block getCurrentBlock(){
		return currentBlock;
	}
	
	public void setCurrentBlock(Block b){
		currentBlock = b;
	}
	public void newBlock(){
		currentBlock = new Block(this);
	}
	
	public void deleteVerse(int verse)
	{
		for (int v = verse - 1; v>=0; v--)
		{
			for (int c = 0; c<=9; c++)
			{
				this.setBoardValue(v+1, c, this.getBoardValue(v, c));
				points = points + 1;
			}
		}
	}
	
	public int getFullVerseCount(){
		int count = 0;
		for(int v = 19; v>+0; v--)
		{
			for (int c = 0; c<=9; c++)
			{
				if(this.getBoardValue(v, c) == 0) break;
				else
				{
					if (c == 9) count = count +1;
				}
			}
		}
		return count;
	}
	
	public void chceckAndDeleteFullVerses(){
		for(int v = 19; v>=0; v--)
		{
			for (int c = 0; c<=9; c++)
			{
				if(this.getBoardValue(v, c) == 0) break;
				else
				{
					if (c == 9) 
					{
						this.deleteVerse(v);
						v=v+1;
					}
				}
			}
		}
	}
	
	public void addPoints(int number){
		points = points +number;
	}
	
	/**Konstruktor zeruje tablicê planszy */
	TetrisModel(){
		for(int i = 0; i<20;  i++){
			for(int j = 0; j<10; j++){
				Board[i][j] = 0;
			}
		}
	}
}
