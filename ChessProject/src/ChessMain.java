import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

public class ChessMain extends JFrame implements ActionListener
{
	private JMenuItem newOption, exitOption, rulesMenuItem, aboutMenuItem;
	private ChessBoard gameBoard;
	private File help = new File("H:\\12 Comp Sci\\ChessProject\\Help.pdf");
	/**
	 * Constructs a new ChessMain frame (sets up the Game)
	 */
	public ChessMain()
	{
		// Sets up the frame for the game
		super("Chess");
		setResizable(false);

		// Load up the icon image
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				"Images/WhiteKnight.png"));

		// Sets up the Chess board that plays most of the game
		// and add it to the centre of this frame
		gameBoard = new ChessBoard();
		add(gameBoard, BorderLayout.CENTER);

		// Centre the frame in the middle (almost) of the screen
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screen.width - gameBoard.BOARD_SIZE.width) / 2,
				(screen.height - gameBoard.BOARD_SIZE.height) / 2 - 100);

		// Adds the menu and menu items to the frame (see below for code)
		// Set up the Game MenuItems
		newOption = new JMenuItem("New");
		newOption.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				InputEvent.CTRL_MASK));
		newOption.addActionListener(this);

		exitOption = new JMenuItem("Exit");
		exitOption.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				InputEvent.CTRL_MASK));
		exitOption.addActionListener(this);

		// Set up the Help Menu
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic('H');
		rulesMenuItem = new JMenuItem("Rules...", 'R');
		rulesMenuItem.addActionListener(this);
		helpMenu.add(rulesMenuItem);
		aboutMenuItem = new JMenuItem("About...", 'A');
		aboutMenuItem.addActionListener(this);
		helpMenu.add(aboutMenuItem);

		// Add each MenuItem to the Game Menu (with a separator)
		JMenu gameMenu = new JMenu("Game");
		gameMenu.add(newOption);
		gameMenu.addSeparator();
		gameMenu.add(exitOption);
		JMenuBar mainMenu = new JMenuBar();
		mainMenu.add(gameMenu);
		mainMenu.add(helpMenu);
		// Set the menu bar for this frame to mainMenu
		setJMenuBar(mainMenu);
	} // Constructor

	/**
	 * Responds to a Menu Event. This method is needed since our Chess
	 * frame implements ActionListener
	 * 
	 * @param event the event that triggered this method
	 */
	public void actionPerformed(ActionEvent event)
	{
		if (event.getSource() == newOption) // Selected "New"
		{
			gameBoard.newGame();
			if (JOptionPane.showConfirmDialog(this,
					"Would you like to play against the computer?",
					"Game Mode", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			{
				gameBoard.setComputerGame();
			}

		}
		else if (event.getSource() == exitOption) // Selected "Exit"
		{
			hide();
			System.exit(0);
		}
		else if (event.getSource() == rulesMenuItem) // Selected "Rules"
		{
			try {
				   Desktop.getDesktop().open(help);
				} catch(Exception e) {
				   System.out.println(e);
				}
			
		}
		else if (event.getSource() == aboutMenuItem) // Selected "About"
		{
			JOptionPane.showMessageDialog(this, "by Mitchell Matan Peter"
					+ "\n\u00a9 2014", "About Chess",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * Starts up the Chess frame
	 * 
	 * @param args An array of Strings (ignored)
	 */
	public static void main(String[] args)
	{
		// Starts up the Chess frame
		ChessMain frame = new ChessMain();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	} // main method
}
