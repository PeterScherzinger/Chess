import java.util.ArrayList;

public class BoardEval
{
	private int square;
	private int row;
	private int col;
	private int kMoves;
	private int maxMoves;
	private ArrayList<Move> legalMoves;
	private int team;
	private Move move;
	private Piece pieceInStartSquare;
	private Piece pieceInEndSquare;

	public BoardEval(int team)
	{
		this.pieceInStartSquare = null;
		this.pieceInEndSquare = null;
		this.team = team;
		this.reset();
	}

	public Move nextMove(Piece[][] board)
	{
		for (int searchSquare = square; searchSquare <= 63; searchSquare++)
		{
			int searchRow = searchSquare / 8;
			int searchCol = searchSquare % 8;

			if (board[searchRow][searchCol] != null
					&& board[searchRow][searchCol].team == team)
			{
				// if (searchSquare == 7)
				// {
				// System.out.print("STOP");
				// }

				legalMoves = board[searchRow][searchCol].validMoves();
				maxMoves = legalMoves.size();

				if (maxMoves > 0)
				{
					kMoves++;
					if (kMoves < maxMoves)
					{
						move = legalMoves.get(kMoves);
						square = searchSquare;
						row = searchRow;
						col = searchCol;

						this.movePiece(board);

						return move;
					}
					else
						kMoves = -1;
				}

			}
		}
		Move emptyMove = new Move(row, col, row, col);

		return emptyMove;

	}

	public boolean moreMoves(Piece[][] board)
	{
		if (0 <= kMoves && kMoves < maxMoves - 1)
		{
			// More moves from current piece

			return true;
		}
		else if (square == 63)
			return false;
		else
		{
			// Need to find next piece

			square++;

			kMoves = -1;

			for (int searchSquare = square; searchSquare <= 63; searchSquare++)
			{
				int searchRow = searchSquare / 8;
				int searchCol = searchSquare % 8;

				if (board[searchRow][searchCol] != null
						&& board[searchRow][searchCol].team == team)
				{
					square = searchSquare;
					row = searchRow;
					col = searchCol;
					return true;
				}

			}
			return false;

		}

	}

	public void reset()
	{
		square = 0;
		row = 0;
		col = 0;
		kMoves = -1;
		maxMoves = 0;
	}

	private void movePiece(Piece[][] board)
	{
		int oldRow = move.getStartRow();
		int oldCol = move.getStartCol();
		int newRow = move.getRow();
		int newCol = move.getCol();

		pieceInStartSquare = board[oldRow][oldCol];
		pieceInEndSquare = board[newRow][newCol];

		if (newRow != oldRow || newCol != oldCol)
		{
			board[newRow][newCol] = board[oldRow][oldCol];
			board[oldRow][oldCol] = null;
		}
	}

	public void undoMove(Piece[][] board)
	{
		int oldRow = move.getStartRow();
		int oldCol = move.getStartCol();
		int newRow = move.getRow();
		int newCol = move.getCol();

		board[oldRow][oldCol] = pieceInStartSquare;
		board[newRow][newCol] = pieceInEndSquare;
	}
}
