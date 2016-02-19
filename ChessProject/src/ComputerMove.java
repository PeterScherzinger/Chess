import java.util.ArrayList;

/**
 * Makes a move when player selects to play against a computer. Chooses move by
 * searching through all possible moves, then looks at moves the human player
 * can make based off that. It selects the move that has that gives the computer
 * the best board value after four moves
 * 
 * @author Peter Scherzinger
 * @version January 22nd, 2014
 * 
 */
public class ComputerMove
{

        private Move computerMove;
        private Move bestComputerMove;
        private BoardEval firstHumanMoves;
        private BoardEval firstComputerMoves;
        private BoardEval secondHumanMoves;
        private BoardEval secondComputerMoves;
        private ArrayList<Move> computerOpener;
        //private ArrayList<Piece> computerTakenPieces;
        private Piece computerPiece;
        private int maxBoardValue;
        private int moveCounter;

        /**
         * Makes a new computer player. Creates an array of opening moves for the
         * computer to make
         */
        public ComputerMove()
        {
                firstHumanMoves = new BoardEval(1);
                firstComputerMoves = new BoardEval(-1);
                secondHumanMoves = new BoardEval(1);
                secondComputerMoves = new BoardEval(-1);
                computerOpener = new ArrayList<Move>();
                computerOpener.add(new Move(3, 2, 1, 2));
                computerOpener.add(new Move(2, 5, 0, 6));
                computerOpener.add(new Move(2, 3, 1, 3));
                computerOpener.add(new Move(2, 0, 1, 0));
                computerOpener.add(new Move(2, 2, 0, 1));
                moveCounter = 0;
               // computerTakenPieces=new ArrayList <Piece>(15);
                
        }

        /**
         * Computer evaluates all its possible moves through four moves and
         * evaluates the best move
         * 
         * @param whiteCaptured the array of captured pieces
         * @param board the board in the state right after the player has made move
         */
        public void run(Piece[][] board, ArrayList<Piece> whiteCaptured)
        {
                reset();

                if (moveCounter < computerOpener.size())
                {
                        // Do opening moves while they are valid
                        // An invalid opening move ends the opening move sequence

                        int computerOpenerRow = computerOpener.get(moveCounter).getRow();
                        int computerOpenerCol = computerOpener.get(moveCounter).getCol();
                        int computerOpenerStartRow = computerOpener.get(moveCounter)
                                        .getStartRow();
                        int computerOpenerStartCol = computerOpener.get(moveCounter)
                                        .getStartCol();

                        if (board[computerOpenerStartRow][computerOpenerStartCol]
                                        .validMoves().contains(computerOpener.get(moveCounter)))
                        {
                                board[computerOpenerStartRow][computerOpenerStartCol]
                                                .dropPiece(computerOpenerRow, computerOpenerCol);
                                board[computerOpenerStartRow][computerOpenerStartCol] = null;
                        }
                        else
                                moveCounter = computerOpener.size();
                }

                if (moveCounter++ >= computerOpener.size())
                {
                        // Main game player after opening moves
                        // Scan through 1st moves by computer

                        do
                        {
                                computerMove = firstComputerMoves.nextMove(board);

                                // Scan through second moves by player
                                firstHumanMoves.reset();

                                do
                                {
                                        firstHumanMoves.nextMove(board);

                                        // Scan through third move by computer
                                        secondComputerMoves.reset();

                                        do
                                        {
                                                secondComputerMoves.nextMove(board);

                                                // Scan through fourth move by player
                                                secondHumanMoves.reset();

                                                do
                                                {
                                                        secondHumanMoves.nextMove(board);

                                                        // Evaluate the board and identify a best score and
                                                        // best first move
                                                        int boardValue = boardValue(board);

                                                        if (boardValue > maxBoardValue)
                                                        {
                                                                bestComputerMove = computerMove;
                                                                maxBoardValue = boardValue;
                                                        }

                                                        secondHumanMoves.undoMove(board);

                                                }
                                                while (secondHumanMoves.moreMoves(board));

                                                secondComputerMoves.undoMove(board);

                                        }
                                        while (secondComputerMoves.moreMoves(board));

                                        firstHumanMoves.undoMove(board);

                                }
                                while (firstHumanMoves.moreMoves(board));

                                firstComputerMoves.undoMove(board);

                        }
                        while (firstComputerMoves.moreMoves(board));

                        // Makes the best move based on the board value after four moves
                         computerPiece=board[bestComputerMove.getStartRow()][bestComputerMove.getStartCol()];

                         //If the AI is about to take a piece, add it to the
                         //Captured Pieces AI
            if(board[bestComputerMove.getRow()][bestComputerMove.getCol()]!=null&&
                        computerPiece.team!=board[bestComputerMove.getRow()][bestComputerMove.getCol()].team)
            	
                whiteCaptured.add(board[bestComputerMove.getRow()][bestComputerMove.getCol()]); 
            
                        board[bestComputerMove.getStartRow()][bestComputerMove
                                        .getStartCol()].dropPiece(bestComputerMove.getRow(),
                                        bestComputerMove.getCol());
                        
                        //Promote AI pawns to queen
                        if (computerPiece!=null && computerPiece instanceof Pawn)
                 {
                         Pawn checkPawn= (Pawn)computerPiece;
                         if(checkPawn.endOfBoard())
                         {
                                 checkPawn.promote();
                         }
                 }
                        
                        board[bestComputerMove.getStartRow()][bestComputerMove
                                        .getStartCol()] = null;

                }
        }
                
        /**
         * Sets values needed to evaluate the board back to their initial values
         */
        private void reset()
        {
                maxBoardValue = -75;
                firstHumanMoves.reset();
                firstComputerMoves.reset();
        }

        /**
         * makes a rating on the board
         * 
         * @param board the board that will be evaluated
         * @param team the team that the evaluation will be made on
         * @return the rating on the board that the computer uses to value which
         *         move is the best
         */
        private int boardValue(Piece[][] board, int team)
        {
                int value = 0;
//Goes through board and adds up values of all pieces
                for (int row = 0; row <= 7; row++)
                {
                        for (int col = 0; col <= 7; col++)
                        {
                                if (board[row][col] != null)
                                {
                                        if (board[row][col].team == team)
                                        {
                                                value += board[row][col].getValue();
                                        }
                                }
                        }
                }

                return value;
        }

        /**
         * Overloaded version of boardValue, returns the difference between the
         * black and the white teams board values
         * 
         * @param board The board that will be evaluated
         * @return the difference between the black teams board value and the white
         *         teams board value
         */
        private int boardValue(Piece[][] board)
        {
                return boardValue(board, -1) - boardValue(board, 1);
        }

}
