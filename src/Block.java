
public class Block {
	TetrisModel theModel;
	
	int tablica[][][]=new int[][][]	{	{ 	{1,0,0,0},
											{1,0,0,0},
											{1,0,0,0},
											{1,0,0,0}
										},
										{	{0,0,0,0},
											{0,0,0,0},
											{0,0,0,0},
											{1,1,1,1}
										}
									};
	private int orientation;
	int verse;
	int column;
	int orientations = 2;
	int verseSize = 4;
	int columnSize = 4;
	
	public void sendToModel(){
		for(int v = 0; v<verseSize; v++)
		{
			for(int c = 0; c< columnSize;c++)
			{
				if(tablica[orientation][v][c] ==1)
				{
					theModel.setBoardValue(verse+v, column+c, 1);
				}
			}
		}
	}
	
	public void setToBoard(){
		for(int v = 0; v<verseSize; v++)
		{
			for(int c = 0; c< columnSize;c++)
			{
				if(tablica[orientation][v][c] ==1)
				{
					theModel.setBoardValue(verse+v, column+c, 2);
				}
			}
		}
	}
	
	public int getNextOrientation(int direction)
	{
		if(direction == 0){
			if(orientation == orientations -1) return 0;
			else
				return orientation+1;
		}
		if(direction == 1){
			if(orientation == 0) return orientations -1;
			else
				return orientation-1;
		}
		return orientation;
	}
	
	public int getRotationOffset(int direction){
		if(direction == 0){
			if(orientation == 0) return -2;
			else
				return 2;
		}
		if(direction == 1){
			if(orientation == 0) return -2;
			else
				return 2;
		}
		return 0;
	}
	
	public boolean checkRotateCollision (int direction, int offset){
		int nextOrientation = getNextOrientation(direction);

				for(int v = 0; v<verseSize; v++){
					for(int c = 0; c< columnSize;c++){
						if(tablica[nextOrientation][v][c] ==1){
							//System.out.print(Integer.toString(verse+v) + " " + Integer.toString(column+c + offset)+ "\n");
							if(column + c + offset < 0 ||column + c + offset > 9 || 
									theModel.getBoardValue(verse + v, column + c +offset) == 2){
								System.out.print("wykryta kolizja\n");
								return true;
							}
						}
					}
				}
				return false;

	}
	
	public void rotate(int direction){
		int offset = getRotationOffset(direction);
		if(checkRotateCollision(direction, offset) == true) return;
		
		for(int v = 0; v<verseSize; v++)
		{
			for(int c = 0; c< columnSize;c++)
			{
				if(tablica[orientation][v][c] ==1)
				{
					theModel.setBoardValue(verse+v, column+c, 0);
				}
			}
		}
		
		if(direction == 0){
			if(orientation == orientations -1) orientation = 0;
			else
				orientation = orientation+1;
			column = column + offset;
			sendToModel();
		}
		if(direction == 1){
			if(orientation == 0) orientation = orientations -1;
			else
				orientation = orientation-1;
			column = column + offset;
			sendToModel();
		}
		
		
	}
	
	/** Zwraca true jesli kolizja wystepuje i false jesli nie wystepuje*/
	public boolean checkMoveCollision(int direction){
		switch (direction) {
			
		case 0:
				for(int v = 0; v<verseSize; v++){
					for(int c = 0; c< columnSize;c++){
						if(tablica[orientation][v][c] ==1){
							if(column +c <= 0 || theModel.getBoardValue(verse+v, column + c - 1) == 2){
								return true;
							}
						}
					}
				}
				return false;
			
			case 1:
				for(int v = 0; v<verseSize; v++){
					for(int c = 0; c< columnSize;c++){
						if(tablica[orientation][v][c] ==1){
							if(verse + v + 1 > 19 || theModel.getBoardValue(verse+v+1, column + c) == 2){
								setToBoard();
								theModel.chceckAndDeleteFullVerses();
								
								theModel.newBlock();
								return true;
							}
						}
					}
				}
				return false;
				
			case 2:
				for(int v = 0; v<verseSize; v++){
					for(int c = 0; c< columnSize;c++){
						if(tablica[orientation][v][c] ==1){
							if(column + c >= 9 || theModel.getBoardValue(verse+v, column + c + 1) == 2){
								return true;
							}
						}
					}
				}
				return false;
				
			default:
				return true;
		}
	}
	
	public void moveBlock(int direction){
		
		if(checkMoveCollision(direction) == true) 
		{
			System.out.print("wykryta kolizja\n");
			return;
		}
		
		System.out.print("przesuwam \n");
		for(int v = 0; v<verseSize; v++)
		{
			for(int c = 0; c< columnSize;c++)
			{
				if(tablica[orientation][v][c] ==1)
				{
					theModel.setBoardValue(verse+v, column+c, 0);
				}
			}
		}
		
		switch(direction){
			case 0:
				column = column - 1;
				sendToModel();
				break;
			case 1:
				verse = verse + 1;
				sendToModel();
				break;
			case 2:
				column = column + 1;
				sendToModel();
				break;
				
		}
		
	}
	
	Block(TetrisModel theModel){
		this.theModel = theModel;
		verse = 0;
		column = 4;
		orientation = 0;
		sendToModel();
		
	}
	
}
