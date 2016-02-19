import java.util.ArrayList;

/**
 *
 * @author Peter Scherzinger
 *
 */
public class King extends Piece
{
	private int move;
	private boolean isFirstMove;

	/**
	 * Creates a King piece for the board
	 * 
	 * @param row the row the piece is in
	 * @param col the column the piece is in
	 * @param team the team the piece is on
	 */
	public King(int row, int col, int team)
	{
		super((team == 1 ? "White" : "Black") + "King", row, col, team);
		this.isFirstMove = true;
	}
	
	/**Gets the value of the piece for the board evaluator 
	 * @return the value of the piece
	 */
	public int getValue()
	{
		return 38;
	}

	/** Finds all the valid moves for the pawn at its position
	 * @return the ArrayList of valid moves for the Pawn
	 */
	public ArrayList<Move> validMoves()
	{
		ArrayList<Move> kingMoves = new ArrayList<Move>();

		// Starts checking in the 8 directions around the piece
		for (int rowMove = -1; rowMove <= 1; rowMove++)
		{
			for (int colMove = -1; colMove <= 1; colMove++)
			{
				// If there is an enemy piece or the space is blank, add it to
				// the array of possible moves
				if (this.isInBounds(rowMove + row, colMove + col))
				{
					if (board[row + rowMove][col + colMove] == null
							|| board[row + rowMove][col + colMove].team != team)
					{
						kingMoves.add(new Move(row + rowMove, col + colMove,row,col));
					}
				}
			}
		}
		return kingMoves;

	}

	/**Checks to see if the King is in check
	 * Puts every type of piece on top of the kings position, and if  that
	 * Piece can attack an enemy piece of the same type, then the King is in check
	 * 
	 * @return whether or not the King is in check
	 */
	public boolean checkForCheck()
	{
		
		//Checks to see if a knight could attack any knights from the kings position
		Piece next = new Knight(row, col, team);
		for (Move move : next.validMoves())
		{
			//If it can, then the king is in check
			if (board[move.getRow()][move.getCol()] instanceof Knight&&board[move.getRow()][move.getCol()].team!=team)
				return true;
			
		}

		//Checks to see if a pawn could attack any pawns from the kings position
		next = new Pawn(row, col, team);
		for (Move move : next.validMoves())
			//If it can, then the king is in check
			if (board[move.getRow()][move.getCol()] instanceof Pawn&&board[move.getRow()][move.getCol()].team!=team)
				return true;

		//Checks to see if a bishop could attack any bishops from the kings position
		next = new Bishop(row, col, team);
		for (Move move : next.validMoves())

			if (board[move.getRow()][move.getCol()] instanceof Bishop&&board[move.getRow()][move.getCol()].team!=team)
			{
				//If it can, then the king is in check
				return true;
			}
		
		//Checks to see if a queen could attack any queens from the kings position
		next = new Queen(row, col, team);
		for (Move move : next.validMoves())
			//If it can, then the king is in check
			if (board[move.getRow()][move.getCol()] instanceof Queen&&board[move.getRow()][move.getCol()].team!=team)
				return true;

		//Checks to see if a rook could attack any rooks from the kings position
		next = new Rook(row, col, team);
		for (Move move : next.validMoves())
		{
			//If it can, then the king is in check
			if (board[move.getRow()][move.getCol()] instanceof Rook&&board[move.getRow()][move.getCol()].team!=team)
				return true;
		}

		return false;
	}
	
/** Checks to see if the king is in checkmate
 * 	
 * @param kingRow
 * @param kingCol
 * @return
 */
  public boolean checkForCheckmate(int kingRow, int kingCol)
  {
         for(int checkRow=kingRow-1;checkRow<=kingRow+1;checkRow++)
         {
                 for(int checkCol=kingCol-1;checkCol<=kingCol+1;checkCol++)
                 {
                        if(board[kingRow][kingCol].validMoves().size()==0)
                                return true;
                }
        }
         return false;
  }
}

	

