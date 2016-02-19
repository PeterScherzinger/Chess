import java.awt.Image;
import java.util.ArrayList;

/** A class that creates and keeps track of a Rook object. This class keeps track
 * of a Rooks row, column and team. This class also contains methods that move the Rook, checks what
 * moves are available, and if that move is possible at this time. 
 * 
 * @author Peter Scherzinger and Matan Feldberg
 * 
 */
public class Rook extends Piece {
	private int move;
	private boolean isFirstMove;

	/** Creates a Rook piece for the board
	 * 
	 * @param row the row the piece is in
	 * @param col the column the piece is in
	 * @param team the team the piece is on
	 */
	public Rook(int row, int col, int team) {
		super((team == 1 ? "White" : "Black") + "Rook", row, col, team);
		this.move = team;
		this.isFirstMove=true;
	
	}
	
	/**Gets the value of the piece for the board evaluator 
	 * @return the value of the piece
	 */
	public int getValue()
	{
		return 5;
	}
	
	/**Finds all the valid moves for the rook at its location
	 * @return an ArrayList of valid moves for the piece
	 */
	public ArrayList<Move> validMoves() {
		
		ArrayList<Move> rookMoves = new ArrayList<Move>();
		int dRow=1;
		int dCol=1;
		
		int rowToMove=1;
		//Checks all available spaces to the right of the piece
		while(this.isInBounds(row+rowToMove, col)&& board[row+rowToMove][col]==null)
		{
		rookMoves.add(new Move(row+rowToMove, col,row,col));
			rowToMove++;
		}	
		//If there is an enemy piece or the space is blank, add it to the array of possible moves
		if(this.isInBounds(row+rowToMove, col)&&board[row+rowToMove][col]!=null &&board[row+rowToMove][col].team!=team)
		{
			rookMoves.add(new Move(row+rowToMove, col,row,col));
		}
		rowToMove=1;
		//Checks all available spaces to the left of the piece
		while(this.isInBounds(row-rowToMove, col)&& board[row-rowToMove][col]==null)
		{
			
		rookMoves.add(new Move(row-rowToMove, col,row,col));
			rowToMove++;
		}	
		//If there is an enemy piece or the space is blank, add it to the array of possible moves
		if(this.isInBounds(row-rowToMove, col)&&board[row-rowToMove][col]!=null &&board[row-rowToMove][col].team!=team)
		{
			rookMoves.add(new Move(row-rowToMove, col,row,col));
		}
		rowToMove=1;
		
		
		int colToMove=1;
		//Checks all available spaces above the piece
		while(this.isInBounds(row, col+colToMove)&& board[row][col+colToMove]==null)
		{
		rookMoves.add(new Move(row, col+colToMove,row,col));
			colToMove++;
		}	
		//If there is an enemy piece or the space is blank, add it to the array of possible moves
		if(this.isInBounds(row, col+colToMove) && board[row][col+colToMove]!=null && board[row][col+colToMove].team!=team)
		{
			rookMoves.add(new Move(row, col+colToMove,row,col));
		}
		
		colToMove=1;
		//Checks all available spaces below the piece
		while(this.isInBounds(row, col-colToMove)  && board[row][col-colToMove]==null)
		{
		rookMoves.add(new Move(row, col-colToMove,row,col));
			colToMove++;
		}	
		//If there is an enemy piece or the space is blank, add it to the array of possible moves
		if(this.isInBounds(row, col-colToMove)&&board[row][col-colToMove]!=null &&board[row][col-colToMove].team!=team)
		{
			rookMoves.add(new Move(row, col-colToMove,row,col));
		}
		colToMove=1;
		
		return rookMoves;
		
	}

	/**Moves a piece to a given row and column
	 * 
	 * @param moveRow the row the piece is moving to
	 * @param moveCol the column the piece is moving to
	 */
	public void movePiece(int moveRow, int moveCol) 
	{
		board[moveCol][moveRow]=board[row][col];
		board[row][col]=null;
	}

	
}
