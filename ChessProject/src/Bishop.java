import java.util.ArrayList;

/**
 * 
 * A class that creates and keeps track of a Bishop object. This class keeps track
 * of a Bishop's row, column and team. This class also contains methods that move the Bishop, checks what
 * moves are available for the piece, and if that move is possible at this time. 
 * @author Matan Feldberg and Peter Scherzinger
 * 
 */
public class Bishop extends Piece
{
	private int move;

	/**
	 * Creates a Bishop piece for the board
	 * 
	 * @param row the row the piece is in
	 * @param col the column the piece is in
	 * @param team the team the piece is on
	 */
	public Bishop(int row, int col, int team)
	{
		super((team == 1 ? "White" : "Black") + "Bishop", row, col, team);
		this.move = team;

	}

	/**Gets the value of the piece for the board evaluator 
	 * @return the value of the piece
	 */
	public int getValue()
	{
		return 3;
	}

	/** Finds all valid moves for the bishop at its position
	 * @return an ArrayList of valid moves for the Bishop
	 */
	public ArrayList<Move> validMoves()
	{

		ArrayList<Move> bishopMoves = new ArrayList<Move>();

		int rowToMove = 1;
		int colToMove = 1;
		// Checks all available spaces diagonally right and down
		while (this.isInBounds(row + rowToMove, col + colToMove)
				&& board[row + rowToMove][col + colToMove] == null)
		{
			bishopMoves
					.add(new Move(row + rowToMove, col + colToMove, row, col));
			rowToMove++;
			colToMove++;
		}
		// if there is an enemy piece, adds it to the array of possible moves
		if (this.isInBounds(row + rowToMove, col + colToMove)
				&& board[row + rowToMove][col + colToMove] != null
				&& board[row + rowToMove][col + colToMove].team != team)
		{
			bishopMoves
					.add(new Move(row + rowToMove, col + colToMove, row, col));
		}
		rowToMove = 1;
		colToMove = 1;

		// Checks all available spaces diagonally left and down
		while (this.isInBounds(row - rowToMove, col + colToMove)
				&& board[row - rowToMove][col + colToMove] == null)
		{
			bishopMoves
					.add(new Move(row - rowToMove, col + colToMove, row, col));
			rowToMove++;
			colToMove++;
		}
		// If there is an enemy piece or the space is blank, add it to the array
		// of possible moves
		if (this.isInBounds(row - rowToMove, col + colToMove)
				&& (board[row - rowToMove][col + colToMove] != null || board[row
						- rowToMove][col + colToMove].team != team))
		{
			bishopMoves
					.add(new Move(row - rowToMove, col + colToMove, row, col));
		}
		rowToMove = 1;
		colToMove = 1;

		// Checks all available spaces diagonally right and up
		while (this.isInBounds(row + rowToMove, col - colToMove)
				&& board[row + rowToMove][col - colToMove] == null)
		{
			bishopMoves
					.add(new Move(row + rowToMove, col - colToMove, row, col));
			rowToMove++;
			colToMove++;

		}
		// If there is an enemy piece or the space is blank, add it to the array
		// of possible moves
		if (this.isInBounds(row + rowToMove, col - colToMove)
				&& board[row + rowToMove][col - colToMove] != null
				&& board[row + rowToMove][col - colToMove].team != team)
		{
			bishopMoves
					.add(new Move(row + rowToMove, col - colToMove, row, col));
		}
		rowToMove = 1;
		colToMove = 1;
		// Checks all available spaces diagonally left and up
		while (this.isInBounds(row - rowToMove, col - colToMove)
				&& board[row - rowToMove][col - colToMove] == null)
		{
			bishopMoves
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
			bishopMoves
					.add(new Move(row - rowToMove, col - colToMove, row, col));
		}
		rowToMove = 1;
		colToMove = 1;

		return bishopMoves;

	}

}
