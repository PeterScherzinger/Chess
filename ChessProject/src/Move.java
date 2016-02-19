/** Creates a move object that keeps track of a row and column
 * on a 2D array of pieces. Contains methods to get rows and columns
 * of spots on the board for the AI to move to.
 * 
 * @author Peter Scherzinger
 *
 */
public class Move
{
	private int startRow;
	private int startCol;
	private int endRow;
	private int endCol;

	
	/** Creates a move object for the Piece class
	 * 
	 * @param row the row the move is going to
	 * @param col the column the move is going to
	 */
	public Move(int row, int col)
	{
		this.endRow = row;
		this.endCol = col;
	}
	
	/** Creates a move object ComputerMove class
	 * 
	 * @param row the row the AI is moving to
	 * @param col the column the AI is moving to
	 * @param startRow the row the AI is starting at
	 * @param startCol the column the AI is starting at
	 */
	public Move(int row, int col,int startRow,int startCol)
	{
		this.endRow = row;
		this.endCol = col;
		this.startRow=startRow;
		this.startCol=startCol;
	}

	/** Gets the row of the space the AI is moving to
	 * 
	 * @return the row the AI is moving to
	 */
	public int getRow()
	{
		return endRow;
	}

	/** Gets the column of the space the AI is moving to
	 * 
	 * @return the column the AI is moving to
	 */
	public int getCol()
	{
		return endCol;
	}

	/**Gets the starting row of the AI piece before it moves
	 * 
	 * @return the starting row of the AI piece
	 */
	public int getStartRow()
	{
		return startRow;
	}

	/**Gets the starting column of the AI piece before it moves
	 * 
	 * @return the starting column of the AI piece
	 */
	public int getStartCol()
	{
		return startCol;
	}

	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + endCol;
		result = prime * result + endRow;
		return result;
	}

	/** Compares two moves to see if they are equal
	 * @return whether or not they are equal
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Move other = (Move) obj;
		if (endCol != other.endCol)
			return false;
		if (endRow != other.endRow)
			return false;
		return true;
	}

}
