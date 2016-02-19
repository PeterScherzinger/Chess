import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;

/**
 * 
 * @author Peter Scherzinger and Matan Feldberg
 * 
 */
public abstract class Piece extends Rectangle
{
	protected int col;
	protected int row;
	protected Image image;
	protected int team;
	protected static Piece[][] board;

	public Piece(String imageFileName, int row, int col, int team)
	{
		super(0, 0, 0, 0);
		this.row = row;
		this.col = col;
		this.team = team;

		// Load up the appropriate image file for this Piece
		imageFileName = "Images\\" + imageFileName + ".png";
		this.image = new ImageIcon(imageFileName).getImage();
		setSize(image.getWidth(null), image.getHeight(null));

		setLocation(col * 64 + 6, row * 64 + 6);

	}

	public boolean isWhite()
	{
		return (team == 1);
	}

	public boolean isBlack()
	{
		return (team == -1);
	}

	public abstract ArrayList<Move> validMoves();

	public Image getImage()
	{
		return image;
	}

	public abstract int getValue();

	public static void setBoard(Piece[][] board)
	{
		Piece.board = board;
	}

	public void draw(Graphics g)
	{
		g.drawImage(image, x, y, null);
	}

	public void drawSmall(Graphics g)
	{
		g.drawImage(image, x, y, width / 2, height / 2, null);
	}

	public void dropPiece(int row, int col)
	{
		this.row = row;
		this.col = col;
		setLocation(col * 64 + 6, row * 64 + 6);
		board[row][col] = this;
	}

	public boolean isMovePossible(int row, int col)
	{
		return (validMoves().indexOf(new Move(row, col)) != -1);

	}

	public boolean isInBounds(int row, int col)
	{
		if (row >= 0 && col >= 0 && row <= 7 && col <= 7)
			return true;

		return false;

	}

}
