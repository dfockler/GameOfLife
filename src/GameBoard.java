import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;


/*
 * This controls all the functionality for the gameboard
 */
public class GameBoard
{
	int[][] board;
	int xDim;
	int yDim;
	
	public GameBoard(String filename)
	{
		this.board = fill(filename);
		this.yDim = this.board.length;
		this.xDim = this.board[0].length;
	}
	
	/*
	 * Here lies the meat and potatoes...
	 * Goes through each cell in the array,
	 * It checks all of it's neighbors, and increments the neighbor count
	 * if a neighbor is alive. This would change if I wanted to do multiple states.
	 * The cell counter determines which cell is being checked.
	 * The alive array is a flattened (single array) version of the board, used to tell the
	 * updateBoard function which cells are alive.
	 */
	public void nextGeneration()
	{
		int neighbors = 0;
		int cell = 0;
		int sideLeft = xDim - 1;
		int sideBottom = yDim - 1;
		boolean[] alive = new boolean[xDim*yDim];
		for(int vert = 0; vert < yDim; vert++)
		{
			for(int horizontal = 0; horizontal < xDim; horizontal++, cell++, neighbors = 0)
			{
				//Check all neighbors to see if they are alive or not
				//Also checks if the sides of the array have been reached
				//I could make it so it wrapped around the array, that might be cool
				
				//SE
				if(horizontal != sideLeft && vert != sideBottom && board[vert+1][horizontal+1] == 1)
					neighbors++;
				
				//SW
				if(vert != sideBottom && horizontal != 0 && board[vert+1][horizontal-1] == 1)
					neighbors++;
				
				//S
				if(vert != sideBottom && board[vert+1][horizontal] == 1)
					neighbors++;
				
				//E
				if(horizontal != sideLeft && board[vert][horizontal+1] == 1)
					neighbors++;
				
				//W
				if(horizontal != 0 && board[vert][horizontal-1] == 1)
					neighbors++;
				
				//NE
				if(vert != 0 && horizontal != sideLeft && board[vert-1][horizontal+1] == 1)
					neighbors++;
				
				//N
				if(vert != 0 && board[vert-1][horizontal] == 1)
					neighbors++;
				
				//NW
				if(vert != 0 && horizontal != 0 && board[vert-1][horizontal-1] == 1)
					neighbors++;
				
				if(board[vert][horizontal] == 1) //logic rules here
				{	
					//if cell has less than 2 neighbors or greater than 3 it dies
					if(neighbors < 2 || neighbors > 3) 
						alive[cell] = false;
					//if cell has 2 or 3 neighbors it lives
					else 
						alive[cell] = true;
				}
				
				// if cell is dead and has 3 neighbors it wakes up
				else if((neighbors == 3) && board[vert][horizontal] == 0) 
				{
					alive[cell] = true;
				}
			}
		}
		update(alive);
	}
	
	/*
	 * From the alive array, this updates the board and
	 * determines which cells are alive and which are not
	 */
	public void update(boolean[] alive)
	{
		int count = 0;
		for(int j = 0; j < this.yDim; j++)
		{
			for(int k = 0; k < this.xDim; k++, count++)
			{
				if(alive[count])
					board[j][k] = 1;
				else
					board[j][k] = 0;
			}
		}
	}
	
	/*
	 * Just prints out the current state of the board
	 */
	public void print()
	{
		for(int i = 0; i < this.yDim; i++)
		{
			for(int j = 0; j < this.xDim; j++)
			{
				System.out.print(board[i][j] + " "); 
			}
			System.out.println();
		}
	}
	
	/*
	 * Fills the board from a given filename
	 * assuming the board is not a jagged array
	 * Returns the filled array or returns
	 * null if the array wasn't filled correctly
	 */
	public int[][] fill(String filename)
	{
		try
		{
			//Setup the file handler and the scanner
			File fileHandle = new File(filename);
			Scanner reader = new Scanner(fileHandle);
			
			int y = 0;
			int x = 0;
			
			String line = "";
			
			//Determine the y dimension
			while(reader.hasNextLine())
			{
				line = reader.nextLine();
				y++;
			}
			
			reader = new Scanner(line);
			
			//Determine the x dimension
			while(reader.hasNextInt())
			{
				reader.nextInt();
				x++;
			}
			
			//initialize the array
			int[][] array = new int[y][x];
			
			reader = new Scanner(fileHandle);
			
			//read the integers into the array
			for(int i = 0; i < y; i++)
			{
				for(int j = 0; j < x; j++)
				{
					array[i][j] = reader.nextInt();
				}
			}
			
			return array;
			
		} 
		catch (FileNotFoundException e)
		{
			System.out.println("File Not Found");
		}
		catch (NoSuchElementException e)
		{
			System.out.println("Jagged Arrays Are Not Supported");
		}
		
		return null;
	}
}
