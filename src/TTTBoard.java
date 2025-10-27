/**
 * Represents the Tic Tac Toe game board.
 * Manages board state and game logic.
 */
public class TTTBoard {

    private String[][] board;
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private int moveCount;

    /**
     * Constructor initializes empty board.
     */
    public TTTBoard() {
        board = new String[ROWS][COLS];
        clear();
    }

    /**
     * Clears the board to empty state.
     */
    public void clear() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                board[row][col] = " ";
            }
        }
        moveCount = 0;
    }

    /**
     * Checks if a move is valid (in bounds and cell is empty).
     * @param row The row index
     * @param col The column index
     * @return true if move is valid, false otherwise
     */
    public boolean isValidMove(int row, int col) {
        if (row < 0 || row >= ROWS || col < 0 || col >= COLS) {
            return false;
        }
        return board[row][col].equals(" ");
    }

    /**
     * Sets a value at the specified position.
     * @param row The row index
     * @param col The column index
     * @param value The value to set ("X" or "O")
     */
    public void setValue(int row, int col, String value) {
        if (isValidMove(row, col)) {
            board[row][col] = value;
            moveCount++;
        }
    }

    /**
     * Gets the value at the specified position.
     * @param row The row index
     * @param col The column index
     * @return The value at that position
     */
    public String getValue(int row, int col) {
        return board[row][col];
    }

    /**
     * Checks if the specified player has won.
     * @param player The player to check ("X" or "O")
     * @return true if player has won, false otherwise
     */
    public boolean isWinner(String player) {
        for (int row = 0; row < ROWS; row++) {
            if (board[row][0].equals(player) &&
                    board[row][1].equals(player) &&
                    board[row][2].equals(player)) {
                return true;
            }
        }

        for (int col = 0; col < COLS; col++) {
            if (board[0][col].equals(player) &&
                    board[1][col].equals(player) &&
                    board[2][col].equals(player)) {
                return true;
            }
        }

        if (board[0][0].equals(player) &&
                board[1][1].equals(player) &&
                board[2][2].equals(player)) {
            return true;
        }

        if (board[0][2].equals(player) &&
                board[1][1].equals(player) &&
                board[2][0].equals(player)) {
            return true;
        }

        return false;
    }

    /**
     * Checks if the game is a tie.
     * Board is full OR no winning moves possible.
     * @return true if tie, false otherwise
     */
    public boolean isTie() {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (board[r][c].equals(" ")) {
                    return isNoWinPossible();
                }
            }
        }
        return true;
    }

    /**
     * Checks if no winning moves are possible.
     * If each row, column, and diagonal has both X and O, no one can win.
     * @return true if no win is possible, false otherwise
     */
    private boolean isNoWinPossible() {
        boolean xFlag, oFlag;

        for (int r = 0; r < ROWS; r++) {
            xFlag = oFlag = false;
            for (int c = 0; c < COLS; c++) {
                if (board[r][c].equals("X")) xFlag = true;
                if (board[r][c].equals("O")) oFlag = true;
            }
            if (!(xFlag && oFlag)) return false;
        }

        for (int c = 0; c < COLS; c++) {
            xFlag = oFlag = false;
            for (int r = 0; r < ROWS; r++) {
                if (board[r][c].equals("X")) xFlag = true;
                if (board[r][c].equals("O")) oFlag = true;
            }
            if (!(xFlag && oFlag)) return false;
        }

        xFlag = oFlag = false;
        for (int i = 0; i < ROWS; i++) {
            if (board[i][i].equals("X")) xFlag = true;
            if (board[i][i].equals("O")) oFlag = true;
        }
        if (!(xFlag && oFlag)) return false;

        xFlag = oFlag = false;
        for (int i = 0; i < ROWS; i++) {
            if (board[i][ROWS - 1 - i].equals("X")) xFlag = true;
            if (board[i][ROWS - 1 - i].equals("O")) oFlag = true;
        }
        if (!(xFlag && oFlag)) return false;

        return true;
    }

    /**
     * Gets the current move count.
     * @return The number of moves made
     */
    public int getMoveCount() {
        return moveCount;
    }
}