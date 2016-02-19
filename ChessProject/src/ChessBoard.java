import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.event.*;

/**
 * The "ChessBoard" class. Handles the board play for a game of Chess. Includes
 * methods to draw the board, draw taken pieces, set up the board, reset the
 * board, and deal with mouse events.
 * 
 * @author Peter Scherzinger, Matan Feldberg and Mitchell Garber
 * @version January 2014
 */
public class ChessBoard extends JPanel implements MouseListener, KeyListener
{
	// Program constants (declared at the top, these can be used by any method)

	private final int SQUARE_SIZE = 64;
	private final int NO_OF_ROWS = 8;
	private final int NO_OF_COLUMNS = 8;
	private final int TOP_OFFSET = 6;
	private final int LEFT_OFFSET = 6;
	private static final int ANIMATION_FRAMES = 6;
	private static final boolean ANIMATION_ON = true;
	private int currentTurn;
	private Image chessBoard;
	private Image whiteKnight;
	private Image blackKnight;
	private Image whitePawn;
	private Image blackPawn;
	private Image whiteRook;
	private Image blackRook;
	private Image whiteBishop;
	private Image blackBishop;
	private Image whiteQueen;
	private Image blackQueen;
	private Image whiteKing;
	private Image blackKing;
	private Point lastPoint;
	private int fromRow;
	private int fromCol;
	private King blackCheckKing;
	private King whiteCheckKing;
	private Piece selectedPiece;
	private ComputerMove computerPlayer;
	private ArrayList<Piece> whiteCaptured;
	private ArrayList<Piece> blackCaptured;

	private boolean isWhiteCheck;
	private boolean isBlackCheck;
	public final Dimension BOARD_SIZE = new Dimension(712, 512);

	// Program variables (declared at the top, these can be
	// used or changed by any method)
	private Piece[][] board;

	private boolean gameOver;
	protected boolean versusComputer;

	/**
	 * Constructs a new ChessBoard object
	 */
	public ChessBoard()
	{

		// Sets up the board area, loads in piece images and starts a new game
		setPreferredSize(BOARD_SIZE);

		// Add mouse listeners and Key Listeners to the game board
		addMouseListener(this);
		setFocusable(true);
		addKeyListener(this);
		requestFocusInWindow();
		this.addMouseMotionListener(new MouseMotionHandler());

		// Add in all the images for the board
		chessBoard = new ImageIcon("Images\\ChessBoard.png").getImage();
		whiteKnight = new ImageIcon("Images\\whiteKnight.png").getImage();
		blackKnight = new ImageIcon("Images\\blackKnight.png").getImage();
		whiteRook = new ImageIcon("Images\\whiteRook.png").getImage();
		blackRook = new ImageIcon("Images\\blackRook.png").getImage();
		whiteBishop = new ImageIcon("Images\\whiteBishop.png").getImage();
		blackBishop = new ImageIcon("Images\\blackBishop.png").getImage();
		whiteQueen = new ImageIcon("Images\\whiteQueen.png").getImage();
		blackQueen = new ImageIcon("Images\\blackQueen.png").getImage();
		whiteKing = new ImageIcon("Images\\whiteKing.png").getImage();
		blackKing = new ImageIcon("Images\\blackKing.png").getImage();
		whitePawn = new ImageIcon("Images\\whitePawn.png").getImage();
		blackPawn = new ImageIcon("Images\\blackPawn.png").getImage();

		gameOver = false;
		// Sets up the board array and starts a new game
		board = new Piece[NO_OF_ROWS][NO_OF_COLUMNS];
		Piece.setBoard(board);
		newGame();
		if (JOptionPane.showConfirmDialog(this,
				"Would you like to play against the computer?", "Game Mode",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
		{
			this.setComputerGame();
		}

	}

	/**
	 * Starts a new game
	 */
	public void newGame()
	{
		resetBoard();
		gameOver = false;
		versusComputer = false;
		repaint();
		whiteCaptured = new ArrayList<Piece>(15);
		blackCaptured = new ArrayList<Piece>(15);
		currentTurn = 1;
	}

	/**
	 * Sets up the AI to play against it
	 * 
	 */
	public void setComputerGame()
	{
		computerPlayer = new ComputerMove();
		versusComputer = true;
	}

	/**
	 * Resets the board by clearing the board and putting all pieces in Their
	 * starting positions
	 * 
	 */
	private void resetBoard()
	{

		// Clears out board so pieces can be redrawn in their starting place
		for (int row = 0; row < NO_OF_ROWS; row++)
		{
			for (int column = 0; column < NO_OF_COLUMNS; column++)
			{
				board[row][column] = null;
			}

		}
		// Put all of the pawns into their starting position
		for (int col = 0; col < NO_OF_COLUMNS; col++)
		{
			board[6][col] = new Pawn(6, col, 1);
			board[1][col] = new Pawn(1, col, -1);

		}
		// Put white pieces into their starting position
		board[7][0] = new Rook(7, 0, 1);
		board[7][7] = new Rook(7, 7, 1);
		board[7][1] = new Knight(7, 1, 1);
		board[7][6] = new Knight(7, 6, 1);
		board[7][2] = new Bishop(7, 2, 1);
		board[7][5] = new Bishop(7, 5, 1);
		board[7][3] = new Queen(7, 3, 1);
		board[7][4] = new King(7, 4, 1);

		// put black pieces into their starting position
		board[0][0] = new Rook(0, 0, -1);
		board[0][7] = new Rook(0, 7, -1);
		board[0][1] = new Knight(0, 1, -1);
		board[0][6] = new Knight(0, 6, -1);
		board[0][2] = new Bishop(0, 2, -1);
		board[0][5] = new Bishop(0, 5, -1);
		board[0][3] = new Queen(0, 3, -1);
		board[0][4] = new King(0, 4, -1);
	}

	/**
	 * Creates a computer player to play against
	 * 
	 */
	private void computerPlayer()
	{
		Piece currentPiece = null;
	}

	/**
	 * Draws all the graphics for the board
	 * 
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		g.drawImage(chessBoard, 0, 0, this);

		// Redraw the board with current pieces
		for (int row = 0; row < NO_OF_ROWS; row++)
			for (int column = 0; column < NO_OF_COLUMNS; column++)
				if (board[row][column] != null)
					board[row][column].draw(g);
		if (selectedPiece != null)
			selectedPiece.draw(g);

		// Draws Text on the sidebar
		setFont(new Font("Arial", Font.BOLD, 15));
		g.drawString("Current Turn", 540, 40);
		if (currentTurn == 1)
			g.drawString("White", 540, 60);
		else
			g.drawString("Black", 540, 60);

		g.drawString("White Pieces Lost", 540, 100);
		g.drawString("Black Pieces Lost", 540, 306);

		// Warns player their king is in check
		if (isWhiteCheck)
			g.drawString("White team is in check", 540, 415);

		else if (isBlackCheck)
			g.drawString("Black team is in check", 540, 415);

		int whiteX = 520;
		int whiteY = 120;

		int blackX = 520;
		int blackY = 320;

		// Draw the taken white pieces on the side of the screen
		for (Piece next : whiteCaptured)
		{
			next.setLocation(whiteX, whiteY);
			next.drawSmall(g);
			whiteX += 30;
			if (whiteX == 670)
			{
				whiteY += 30;
				whiteX = 520;
			}
		}

		// Draw the taken black pieces on the side of the screen
		for (Piece next : blackCaptured)
		{
			next.setLocation(blackX, blackY);
			next.drawSmall(g);
			blackX += 30;
			if (blackX == 670)
			{
				blackY += 30;
				blackX = 520;
			}
		}

	}

	// Keyboard events you can listen for since this JPanel is a KeyListener

	/**
	 * Responds to a keyPressed event
	 * 
	 * @param event information about the key pressed event
	 */
	public void keyPressed(KeyEvent event)
	{

	}

	// Extra methods needed since this game board is a KeyListener
	public void keyReleased(KeyEvent event)
	{
	}

	public void keyTyped(KeyEvent event)
	{
	}

	// Mouse events you can listen for since this JPanel is a MouseListener

	/**
	 * Responds to a mousePressed event
	 * 
	 * @param eventinformation about the mouse pressed event
	 */
	public void mousePressed(MouseEvent event)
	{

		lastPoint = new Point(event.getPoint());
		// /Ignore clicks on the sidebar
		if (lastPoint.x > 512)
			return;
		// Gets initial position
		int selectedColumn = (int) (lastPoint.getX() / SQUARE_SIZE);
		int selectedRow = (int) (lastPoint.getY() / SQUARE_SIZE);
		fromRow = lastPoint.y / SQUARE_SIZE;
		fromCol = lastPoint.x / SQUARE_SIZE;
		selectedPiece = board[fromRow][fromCol];
		board[fromRow][fromCol] = null;

	}

	// Extra methods needed since this game board is a MouseListener

	public void mouseReleased(MouseEvent event)
	{

		if (selectedPiece == null)
			return;
		int row = event.getPoint().y / SQUARE_SIZE;
		int col = event.getPoint().x / SQUARE_SIZE;

		// If the piece cannot be moved, it is not the players turn
		// Or the player tries to move to the position they are already
		// In, ignore the move
		if (!selectedPiece.isMovePossible(row, col)
				|| selectedPiece.team != currentTurn
				|| (fromRow == row && fromCol == col))

		{
			selectedPiece.dropPiece(fromRow, fromCol);
			selectedPiece = null;
			repaint();
			return;
		}

		// Adds captured piece
		Piece pieceOnBoard = board[row][col];

		board[row][col] = selectedPiece;

		selectedPiece.dropPiece(row, col);

		// Checks to see if the black king is in check
		for (int checkRow = 0; checkRow <= 7; checkRow++)
		{
			for (int checkCol = 0; checkCol <= 7; checkCol++)
			{
				if (board[checkRow][checkCol] instanceof King
						&& board[checkRow][checkCol].team == -1)
				{
					blackCheckKing = (King) board[checkRow][checkCol];
					if (blackCheckKing.checkForCheck()
							&& board[row][col].team == blackCheckKing.team)
					{
						isBlackCheck = true;
						board[row][col] = pieceOnBoard;
						selectedPiece.dropPiece(fromRow, fromCol);
						selectedPiece = null;
						repaint();
						return;

					}
				}
			}
		}

		// Checks to see if the white king is in check
		for (int checkRow = 7; checkRow >= 0; checkRow--)
		{
			for (int checkCol = 7; checkCol >= 0; checkCol--)
				if (board[checkRow][checkCol] instanceof King
						&& board[checkRow][checkCol].team == 1)
				{
					whiteCheckKing = (King) board[checkRow][checkCol];
					if (whiteCheckKing.checkForCheck()
							&& board[row][col].team == whiteCheckKing.team)
					{
						isWhiteCheck = true;
						board[row][col] = pieceOnBoard;
						selectedPiece.dropPiece(fromRow, fromCol);
						selectedPiece = null;
						repaint();
						return;

					}
				}
		}

		// Adds white and black captured pieces to their respective ArrayLists
		if (pieceOnBoard != null)
		{
			if (pieceOnBoard.isWhite())
				whiteCaptured.add(pieceOnBoard);
			else
				blackCaptured.add(pieceOnBoard);
		}

		// Promotes pawns to Queen
		if (selectedPiece instanceof Pawn)
		{
			Pawn checkPawn = (Pawn) selectedPiece;
			if (checkPawn.endOfBoard())
				checkPawn.promote();
			// If add code, set to null
		}

		selectedPiece = null;

		currentTurn *= -1;
		repaint();

		if (versusComputer)
		{
			computerPlayer.run(board, whiteCaptured);

			currentTurn *= -1;
			repaint();
		}

		isBlackCheck = false;
		isWhiteCheck = false;

	}

	public void mouseClicked(MouseEvent event)
	{
	}

	public void mouseEntered(MouseEvent event)
	{
	}

	public void mouseExited(MouseEvent event)
	{
	}

	private class MouseMotionHandler implements MouseMotionListener
	{
		public void mouseMoved(MouseEvent event)
		{

			// Figure out if we are on a piece or not
			boolean onAPiece = false;
			Point clickPoint = event.getPoint();
			if (clickPoint.x > 512)
				// Check all pieces on the board
				if (clickPoint.x < 512)
					for (int row = 0; row < NO_OF_ROWS; row++)
						for (int column = 0; column < NO_OF_COLUMNS; column++)
						{
							if (board[(int) (clickPoint.getX() / 64)][(int) (clickPoint
									.getY() / 64)] != null)
								onAPiece = true;
							//
							// }

							// Show either on a piece or the default
							// cursor
							if (onAPiece)
								setCursor(Cursor
										.getPredefinedCursor(Cursor.HAND_CURSOR));
							else
								setCursor(Cursor.getDefaultCursor());
						}
		}

		public void mouseDragged(MouseEvent event)
		{
			Point currentPoint = event.getPoint();

			if (selectedPiece != null)
				// Make it so you cannot pick up pieces when it isn't their turn
				if (selectedPiece.team != currentTurn)
					return;
				else
				{
					// We use the difference between the lastPoint and the
					// currentPoint to move the piece so that the position of
					// the mouse on the piece doesn't matter.
					// i.e. we can drag the piece from any point on the piece
					// image
					selectedPiece.translate(currentPoint.x - lastPoint.x,
							currentPoint.y - lastPoint.y);
					lastPoint = currentPoint;
					repaint();

				}

		}
	}

	/**
	 * Delays the given number of milliseconds
	 * 
	 * @param msec the number of milliseconds to delay
	 */
	private void delay(int msec)
	{
		try
		{
			Thread.sleep(msec);
		}
		catch (Exception e)
		{
		}
	}

}
