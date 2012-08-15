
public class GameMain
{
	public static void main(String[] args)
	{
		//Get the file name as the first argument
		GameBoard board = new GameBoard(args[0]);
		
		/*
		 * If the argument is given for the number
		 * of generations then operate that many
		 * generations, otherwise just do a single generation
		 */
		if(args.length == 2)
		{
			int generations = Integer.parseInt(args[1]);
			
			System.out.println("Generation: 1");
			board.print();
			System.out.println();
			
			for(int i = 2; i < generations + 1; i++)
			{
				System.out.println("Generation: " + i);
				board.nextGeneration();
				board.print();
				System.out.println();
			}
		}
		else
		{
			board.print();
			System.out.println();
			board.nextGeneration();
			board.print();
		}
	}
}
