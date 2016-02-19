import java.util.ArrayList;

/**
 * A class that creates and keeps track of a Knight object. This class keeps track
 * of a Knight's row, column and team. This class also contains methods that move the Knight, checks what
 * moves are available for the piece, and if that move is possible at this time. 
 * 
 * @author Mitchell Garber, Matan Feldberg and Peter Scherzinger
 *
 */
public class Knight extends Piece
{
	private int move;

	/**
	 * Creates a Knight piece for the board
	 * 
	 * @param row the row the piece is in
	 * @param col the column the piece is in
	 * @param team the team the piece is on
	 */
	public Knight(int row, int col, int team)
	{
		super((team == 1 ? "White" : "Black") + "Knight", row, col, team);
		this.move = team;

	}

	/**Gets the value of the piece for the board evaluator 
	 * @return the value of the piece
	 */
	public int getValue()
	{
		return 3;
	}

	public ArrayList<Move> validMoves()
	{
		ArrayList<Move> knightMoves = new ArrayList<Move>();
		
		//Finds valid moves for white pieces
		if (team == 1)
		{
			
			//Checks horizontally and vertically up and down two spaces to check for available moves
			for (int rowMove = -2*move; rowMove < 3*move; rowMove +=move)
			{
				for (int colMove = -2*move; colMove < 3*move; colMove +=move)
				{
					//Checks to the left and right one space when moving vertically
					if (this.isInBounds(row + rowMove, col + colMove))
						if ((rowMove == 2 || rowMove == -2)
								&& (colMove == 1 || colMove == -1))
						{
							//If the space is empty or there is an enemy piece
							//Adds the move to list of valid moves
							if (board[row + rowMove][col + colMove] == null
									|| board[row + rowMove][col + colMove].team != team)
								knightMoves.add(new Move(row + rowMove, col
										+ colMove, row, col));
						}
					//Checks up and down one space when moving horizontally
					if (this.isInBounds(row + rowMove, col + colMove))
						if ((rowMove == 1 || rowMove == -1)
								&& (colMove == 2 || colMove == -2))
						{
							//If the space is empty or there is an enemy piece
							//Adds the move to list of valid moves
							if (board[row + rowMove][col + colMove] == null
									|| board[row + rowMove][col + colMove].team != team)
								knightMoves.add(new Move(row + rowMove, col
										+ colMove, row, col));
						}
				}

			}
		}
		//Finds valid moves for black knights
		else
		{
			//Checks horizontally and vertically up and down two spaces to check for available moves
			for (int rowMove = -2 * move; rowMove > 3 * move; rowMove += move)
			{
				for (int colMove = -2 * move; colMove > 3 * move; colMove += move)
				{
					//Checks to the left and right one space when moving vertically
					if (this.isInBounds(row + rowMove, col + colMove))
						if ((rowMove == 2 || rowMove == -2)
								&& (colMove == 1 || colMove == -1))
						{
							//If the space is empty or there is an enemy piece
							//Adds the move to list of valid moves
							if (board[row + rowMove][col + colMove] == null
									|| board[row + rowMove][col + colMove].team != team)
								knightMoves.add(new Move(row + rowMove, col
										+ colMove, row, col));
						}
					
					//Checks up and down one space when moving horizontally
					if (this.isInBounds(row + rowMove, col + colMove))
						if ((rowMove == 1 || rowMove == -1)
								&& (colMove == 2 || colMove == -2))
						{
							
							//If the space is empty or there is an enemy piece
							//Adds the move to list of valid moves
							if (board[row + rowMove][col + colMove] == null
									|| board[row + rowMove][col + colMove].team != team)
								knightMoves.add(new Move(row + rowMove, col
										+ colMove, row, col));
						}
				}

			}
		}
		return knightMoves;
	}

}
