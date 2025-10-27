/**
 * Main game controller for Tic Tac Toe.
 * Coordinates game flow and logic.
 */
public class TicTacToe {

    private TTTBoard board;
    private String currentPlayer;
    private boolean gameOver;
    private TicTacToeFrame frame;

    /**
     * Constructor initializes a new game.
     */
    public TicTacToe() {
        board = new TTTBoard();
        currentPlayer = "X";
        gameOver = false;
    }

    /**
     * Sets the frame reference (called by frame after construction).
     * @param frame The game frame
     */
    public void setFrame(TicTacToeFrame frame) {
        this.frame = frame;
    }

    /**
     * Attempts to make a move at the specified position.
     * @param row The row index
     * @param col The column index
     * @return true if move was successful, false otherwise
     */
    public boolean makeMove(int row, int col) {
        if (gameOver || !board.isValidMove(row, col)) {
            return false;
        }

        board.setValue(row, col, currentPlayer);

        checkGameOver();

        if (!gameOver) {
            switchPlayer();
        }

        return true;
    }

    /**
     * Gets the current player.
     * @return "X" or "O"
     */
    public String getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Checks if the game is over.
     * @return true if game is over, false otherwise
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Gets the winner if game is over.
     * @return "X", "O", "Tie", or null if game not over
     */
    public String getWinner() {
        if (!gameOver) {
            return null;
        }

        if (board.isWinner("X")) {
            return "X";
        } else if (board.isWinner("O")) {
            return "O";
        } else if (board.isTie()) {
            return "Tie";
        }

        return null;
    }

    /**
     * Gets the current move count from the board.
     * @return The number of moves made
     */
    public int getMoveCount() {
        return board.getMoveCount();
    }

    /**
     * Starts a new game.
     */
    public void newGame() {
        board.clear();
        currentPlayer = "X";
        gameOver = false;
    }

    /**
     * Switches to the other player.
     */
    private void switchPlayer() {
        currentPlayer = currentPlayer.equals("X") ? "O" : "X";
    }

    /**
     * Checks if the game is over and updates game state.
     * Checks for winner starting at move 5.
     * Checks for tie starting at move 7.
     */
    private void checkGameOver() {
        if (board.getMoveCount() >= 5) {
            if (board.isWinner("X") || board.isWinner("O")) {
                gameOver = true;
                return;
            }
        }

        if (board.getMoveCount() >= 7) {
            if (board.isTie()) {
                gameOver = true;
            }
        }
    }
}