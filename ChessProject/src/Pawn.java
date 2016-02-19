/**
 * A class that creates and keeps track of a Pawn object. This class keeps track
 * of a pawns row and column. This class also contains methods that move the pawn, checks what
 * moves are available for that kind of piece, and if that move is possible at this time. 
 * @author Mitchell and Peter and Matan
 * @version January 2014
 */
import java.util.ArrayList;

public class Pawn extends Piece
{
	private boolean isFirstMove;
	private int moveDirection;
	
	/**
	 * Creates a Pawn piece for the board
	 * 
	 * @param row the row the piece is in
	 * @param col the column the piece is in
	 * @param team the team the piece is on
	 */
	public Pawn(int row, int col, int team)
	{
		super((team == 1 ? "White" : "Black")+ "Pawn", row, col, team);
		this.isFirstMove = true;
		this.moveDirection = team;
		
	}
	
	/**Gets the value of the piece for the board evaluator 
	 * @return the value of the piece
	 */
	public int getValue()
	{
		return 1;
	}

	/** Finds all the valid moves for the pawn at its position
	 * @return the ArrayList of valid moves for the Pawn
	 */
	public ArrayList<Move> validMoves()
	{
	
		//Checks to see if the pawn is in its starting position 
		if(row==1||row==6)
			isFirstMove=true;
		ArrayList<Move> validMoves = new ArrayList<Move>();
		
		//If it is the pawns first move and no piece is in front of it
		//Then it can move two spaces forward.
		if (this.isInBounds(row-moveDirection*2,col)&&isFirstMove &&board[row-moveDirection*2][col]==null)
		{
			//Add the move to the list of valid moves
			validMoves.add(new Move(row-moveDirection*2,col,row,col));
			isFirstMove=false;			
		}
		
		//Checks If there is no piece in front of it and 
		//Checks if the pawn will be in bounds if it moves forwards one space
		if (this.isInBounds(row-moveDirection,col)&&board[row-moveDirection][col]==null)
		{
			//Add the move to the list of valid moves
			validMoves.add(new Move(row-moveDirection,col,row,col));
		}
		
		//If there is a piece diagonally up and to the right of the piece
		//The pawn can attack it diagonally
		if(this.isInBounds(row-moveDirection,col+1)&&board[row-moveDirection][col+1]!=null)
			
		{
			//Add the move to the list of valid moves
			if(board[row-moveDirection][col+1].team!=team)
			validMoves.add(new Move(row-moveDirection,col+1,row,col));
		}
		//If there is a piece diagonally up and to the left of the piece
		//The pawn can attack it diagonally
		if(this.isInBounds(row-moveDirection,col-1)&&board[row-moveDirection][col-1]!=null)
			
		{
			//Add the move to the list of valid moves
			if(board[row-moveDirection][col-1].team!=team)
			validMoves.add(new Move(row-moveDirection,col-1,row,col));
		}
		
					
		
		return validMoves;
	
	}
	
	/** Promotes a Pawn to a Queen
	 */
	public void promote()
	{
			board[row][col]=(new Queen(row,col,team));		
	}
	
	/** Checks if a pawn is at the end of the board
	 * 
	 * @return whether or not the pawn is at the end of the board
	 */
    public boolean endOfBoard()
    {
    	return(row==7||row==0);
                   
                   
    }
	
}