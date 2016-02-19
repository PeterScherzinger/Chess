import java.util.ArrayList;

/**
 * 
 * @author Peter Scherzinger and Matan Feldberg
 * 
 */
public class Queen extends Piece
{
	private int move;

	/**
	 * Creates a Queen piece for the board
	 * 
	 * @param row the row the piece is in
	 * @param col the column the piece is in
	 * @param team the team the piece is on
	 */
	public Queen(int row, int col, int team)
	{
		super((team == 1 ? "White" : "Black") + "Queen", row, col, team);

	}

	/**Gets the value of the piece for the board evaluator 
	 * @return the value of the piece
	 */
	public int getValue()
	{
		return 9;
	}

	public ArrayList<Move> validMoves()
	{
		ArrayList<Move> queenMoves = new ArrayList<Move>();

		int rowToMove = 1;
		int colToMove = 1;
		// Checks all available spaces diagonally to the right and down
		while (this.isInBounds(row + rowToMove, col + colToMove)
				&& board[row + rowToMove][col + colToMove] == null)
		{
			queenMoves
					.add(new Move(row + rowToMove, col + colToMove, row, col));
			rowToMove++;
			colToMove++;
		}
		// if there is an enemy piece or the space is blank, add it to the array
		// of possible moves
		if (this.isInBounds(row + rowToMove, col + colToMove)
				&& board[row + rowToMove][col + colToMove] != null
				&& board[row + rowToMove][col + colToMove].team != team)
		{
			queenMoves
					.add(new Move(row + rowToMove, col + colToMove, row, col));
		}
		rowToMove = 1;
		colToMove = 1;

		// Checks all available spaces in the diagonally left and down
		while (this.isInBounds(row - rowToMove, col + colToMove)
				&& board[row - rowToMove][col + colToMove] == null)
		{
			queenMoves
					.add(new Move(row - rowToMove, col + colToMove, row, col));
			rowToMove++;
			colToMove++;
		}
		// If there is an enemy piece or the space is blank, add it to the array
		// of possible moves
		if (this.isInBounds(row - rowToMove, col + colToMove)
				&& board[row - rowToMove][col + colToMove] != null
				&& board[row - rowToMove][col + colToMove].team != team)
		{
			queenMoves
					.add(new Move(row - rowToMove, col + colToMove, row, col));
		}
		rowToMove = 1;
		colToMove = 1;

		// Checks all available spaces diagonally right and up
		while (this.isInBounds(row + rowToMove, col - colToMove)
				&& board[row + rowToMove][col - colToMove] == null)
		{
			queenMoves
					.add(new Move(row + rowToMove, col - colToMove, row, col));
			rowToMove++;
			colToMove++;

		}
		// If there is an enemy piece, add it to the array of possible moves
		if (this.isInBounds(row + rowToMove, col - colToMove)
				&& board[row + rowToMove][col - colToMove] != null
				&& board[row + rowToMove][col - colToMove].team != team)
		{
			queenMoves
					.add(new Move(row + rowToMove, col - colToMove, row, col));
		}
		rowToMove = 1;
		colToMove = 1;
		// Checks all available spaces diagonally left and up
		while (this.isInBounds(row - rowToMove, col - colToMove)
				&& board[row - rowToMove][col - colToMove] == null)
		{
			queenMoves
					.add(new Move(row - rowToMove, col - colToMove, row, col));
			rowToMove++;
			colToMove++;
		}
		// If there is an enemy piece or the space is blank, add it to the array
		// of possible moves
		if (this.isInBounds(row - rowToMove, col - colToMove)
				&& board[row - rowToMove][col - colToMove] != null
				&& board[row - rowToMove][col - colToMove].team != team)
		{
			queenMoves
					.add(new Move(row - rowToMove, col - colToMove, row, col));
		}
		rowToMove = 1;
		colToMove = 1;

		rowToMove = 1;
		// Checks all available spaces to the right
		while (this.isInBounds(row + rowToMove, col)
				&& board[row + rowToMove][col] == null)
		{
			queenMoves.add(new Move(row + rowToMove, col, row, col));
			rowToMove++;
		}
		// If there is an enemy piece or the space is blank, add it to the array
		// of possible moves
		if (this.isInBounds(row + rowToMove, col)
				&& board[row + rowToMove][col] != null
				&& board[row + rowToMove][col].team != team)
		{
			queenMoves.add(new Move(row + rowToMove, col, row, col));
		}
		rowToMove = 1;
		// Checks all available spaces to the left
		while (this.isInBounds(row - rowToMove, col)
				&& board[row - rowToMove][col] == null)
		{

			queenMoves.add(new Move(row - rowToMove, col, row, col));
			rowToMove++;
		}
		// If there is an enemy piece or the space is blank, add it to the array
		// of possible moves
		if (this.isInBounds(row - rowToMove, col)
				&& board[row - rowToMove][col] != null
				&& board[row - rowToMove][col].team != team)
		{
			queenMoves.add(new Move(row - rowToMove, col, row, col));
		}
		rowToMove = 1;

		colToMove = 1;
		// Checks all available spaces below the piece
		while (this.isInBounds(row, col + colToMove)
				&& board[row][col + colToMove] == null)
		{
			queenMoves.add(new Move(row, col + colToMove, row, col));
			colToMove++;
		}
		// If there is an enemy piece or the space is blank, add it to the array
		// of possible moves
		if (this.isInBounds(row, col + colToMove)
				&& board[row][col + colToMove] != null
				&& board[row][col + colToMove].team != team)
		{
			queenMoves.add(new Move(row, col + colToMove, row, col));
		}

		colToMove = 1;
		// Checks all available spaces above the piece
		while (this.isInBounds(row, col - colToMove)
				&& this.isInBounds(row, col - colToMove)
				&& board[row][col - colToMove] == null)
		{
			queenMoves.add(new Move(row, col - colToMove, row, col));
			colToMove++;
		}
		// If there is an enemy piece or the space is blank, add it to the array
		// of possible moves
		if (this.isInBounds(row, col - colToMove)
				&& board[row][col - colToMove] != null
				&& board[row][col - colToMove].team != team)
		{
			queenMoves.add(new Move(row, col - colToMove, row, col));
		}
		colToMove = 1;

		return queenMoves;
	}

}
