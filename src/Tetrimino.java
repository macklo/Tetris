import java.awt.Color;

public abstract class Tetrimino {
	//enum orientation {DEG0, DEG90, DEG180, DEG270};
	enum Rotation {CLOCKWISE, COUNTER_CLOCKWISE};
	enum Direction {LEFT, RIGHT, DOWN};
	
	int verse[][];
	int column[][];
	
	TetrisModel theModel;
	
	/** Informacja o ilosci orientacji klocka*/
	int orientationCount;
	
	/**Obecna orientacja klocka, liczenie orientacji zaczyna siê od 0*/
	int orientation;
	
	/** Kolumna punktu zaczepienia*/
	int originColumn;
	
	/** Wiersz punktu zaczepienia*/
	int originVerse;
	
	/**Kolor tetrimino*/
	Color color;
	
	/**Zwraca wiersz ducha*/
	void setOriginVerse(int verse)
	{
		this.originVerse = verse;
	}
	
	/**Zwraca obecna orientacje*/
	int getOrientation()
	{
		return orientation;
	}
	
	/**Zwraca wiersz punktu zaczepienia*/
	int getOriginVerse()
	{
		return originVerse;
	}
	
	/** Zwraca kolumne punktu zaczepienia*/
	int getOriginColumn()
	{
		return originColumn;
	}
	
	/**Zwraca wiersz punktu zaczepienia ducha Tetrimino, który wskazuje gdzie on spadnie*/
	int getGhostVerse()
	{
		int i = 0;
		while(this.checkMoveCollision(i, TetrisController.Direction.DOWN) != true)
		{
			i++;
		}
		return i + this.getOriginVerse();
	}
	
	
	abstract int getColumn(int orientation, int i);
	abstract int getVerse(int orientation, int i);
	
	/** Zwraca nastepna orientacje*/
	int getNextOrientation(TetrisController.Rotation rotation)
	{
		switch(rotation){
		case CLOCKWISE:
			if (orientation == orientationCount - 1){
				return 0;
			}
			else
				return orientation + 1;
		case COUNTER_CLOCKWISE:
			if (orientation == 0){
				return orientationCount - 1;
			}
			else
				return orientation - 1;
			default: return 0;
	}
	}
	
	/** Obracanie klocka*/
	void rotate(TetrisController.Rotation rotation)
	{
		switch(rotation){
			case CLOCKWISE:
				if (orientation == orientationCount - 1){
					orientation = 0;
				}
				else
					orientation = orientation + 1;
				break;
			case COUNTER_CLOCKWISE:
				if (orientation == 0){
					orientation = orientationCount - 1;
				}
				else
					orientation = orientation - 1;
				break;
		}
	}
	
	/**Przemieszczenie Tetrimino*/
	void move(TetrisController.Direction direction)
	{
		switch(direction)
		{
			case RIGHT:
				originColumn++;
				break;
			case LEFT:
				originColumn--;
				break;
			case DOWN:
				originVerse++;
			
		}
	}
	
	/** Funkcja sprawdza czy w nastêpnym ruchu nie wystepuje kolizja z plansz¹ lub bandami*/
	public boolean checkMoveCollision(TetrisController.Direction direction)
	{
		switch(direction)
		{
			case RIGHT:
				for(int i = 0; i<4; i++)
				{
					if(this.originColumn + this.getColumn(this.getOrientation(), i) + 1 > 9 || 
							theModel.getBlockPiece(this.originVerse + this.getVerse(this.getOrientation(), i), this.originColumn + this.getColumn(this.getOrientation(), i) + 1) != null)
						return true;
				}
				return false;
				
			case LEFT:
				for(int i = 0; i<4; i++)
				{
					if(this.originColumn + this.getColumn(this.getOrientation(), i) - 1 < 0 || 
							theModel.getBlockPiece(this.originVerse + this.getVerse(this.getOrientation(), i), this.originColumn + this.getColumn(this.getOrientation(), i) - 1) != null)
						return true;
				}
				return false;
				
			case DOWN:
				for(int i = 0; i<4; i++)
				{
					if(this.originVerse + this.getVerse(this.getOrientation(), i) + 1 > 19 || 
							theModel.getBlockPiece(this.originVerse + this.getVerse(this.getOrientation(), i) + 1, this.originColumn + this.getColumn(this.getOrientation(), i)) != null)
						return true;
				}
				return false;
			default: return false;
		}
	}
	
	
	public boolean checkMoveCollision(int verseOffset, TetrisController.Direction direction)
	{
		switch(direction)
		{
			case RIGHT:
				for(int i = 0; i<4; i++)
				{
					if(this.originColumn + this.getColumn(this.getOrientation(), i) + 1 > 9 || 
							theModel.getBlockPiece(this.originVerse + this.getVerse(this.getOrientation(), i), this.originColumn + this.getColumn(this.getOrientation(), i) + 1) != null)
						return true;
				}
				return false;
				
			case LEFT:
				for(int i = 0; i<4; i++)
				{
					if(this.originColumn + this.getColumn(this.getOrientation(), i) - 1 < 0 || 
							theModel.getBlockPiece(this.originVerse + this.getVerse(this.getOrientation(), i), this.originColumn + this.getColumn(this.getOrientation(), i) - 1) != null)
						return true;
				}
				return false;
				
			case DOWN:
				for(int i = 0; i<4; i++)
				{
					if(verseOffset + this.originVerse + this.getVerse(this.getOrientation(), i) + 1 > 19 || 
							theModel.getBlockPiece(verseOffset + this.originVerse + this.getVerse(this.getOrientation(), i) + 1, this.originColumn + this.getColumn(this.getOrientation(), i)) != null)
						return true;
				}
				return false;
			default: return false;
		}
	}
	
	/** Funkcja sprawdza czy w nastêpnym ruchu nie wystepuje kolizja z plansz¹ lub bandami*/
	public boolean checkRotateCollision(TetrisController.Rotation rotation)
	{
		for(int i = 0; i<4; i++)
		{
			if(this.originColumn + this.getColumn(this.getNextOrientation(rotation), i) > 9 || this.originColumn + this.getColumn(this.getNextOrientation(rotation), i) <0 || this.originVerse + this.getVerse(this.getNextOrientation(rotation), i) + 1 > 19 ||
					theModel.getBlockPiece(this.originVerse + this.getVerse(this.getNextOrientation(rotation), i), this.originColumn + this.getColumn(this.getNextOrientation(rotation), i)) != null)
				return true;
		}
		return false;
		/*
		switch(rotation)
		{
			case CLOCKWISE:
				for(int i = 0; i<4; i++)
				{
					if(this.originColumn + this.getColumn(this.getOrientation(), i) + 1 > 9 || 
							theModel.getBlockPiece(this.originVerse + this.getVerse(this.getOrientation(), i), this.originColumn + this.getColumn(this.getOrientation(), i) + 1) != null)
						return true;
				}
				return false;
				
			case COUNTER_CLOCKWISE:
				for(int i = 0; i<4; i++)
				{
					if(this.originColumn + this.getColumn(this.getOrientation(), i) - 1 < 0 || 
							theModel.getBlockPiece(this.originVerse + this.getVerse(this.getNextOrientation(), i), this.originColumn + this.getColumn(this.getOrientation(), i) - 1) != null)
						return true;
				}
				return false;
				
			default: return false;
		}*/
	}
	
	/** Funkcja sprawdza czy w nastêpnym ruchu nie wystepuje kolizja z plansz¹ lub bandami*/
	public boolean checkSpawnCollision()
	{
		for(int i = 0; i<4; i++)
		{
			if(theModel.getBlockPiece(this.originVerse + this.getVerse(this.getOrientation(), i), this.originColumn + this.getColumn(this.getOrientation(), i)) != null)
				return true;
		}
		return false;
		/*
		switch(rotation)
		{
			case CLOCKWISE:
				for(int i = 0; i<4; i++)
				{
					if(this.originColumn + this.getColumn(this.getOrientation(), i) + 1 > 9 || 
							theModel.getBlockPiece(this.originVerse + this.getVerse(this.getOrientation(), i), this.originColumn + this.getColumn(this.getOrientation(), i) + 1) != null)
						return true;
				}
				return false;
				
			case COUNTER_CLOCKWISE:
				for(int i = 0; i<4; i++)
				{
					if(this.originColumn + this.getColumn(this.getOrientation(), i) - 1 < 0 || 
							theModel.getBlockPiece(this.originVerse + this.getVerse(this.getNextOrientation(), i), this.originColumn + this.getColumn(this.getOrientation(), i) - 1) != null)
						return true;
				}
				return false;
				
			default: return false;
		}*/
	}
	
	/**Zwraca kolor danego Tetrimino*/
	Color getColor()
	{
		return color;
	}
}
