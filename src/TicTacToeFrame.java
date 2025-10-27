import javax.swing.*;
import java.awt.*;

/**
 * Main GUI frame for Tic Tac Toe game.
 * Handles all Swing components and user interaction.
 */
public class TicTacToeFrame extends JFrame {

    private TicTacToe game;
    private TicTacToeButton[][] buttons;
    private JLabel statusLabel;
    private JButton resetButton;
    private JButton quitButton;

    /**
     * Constructor creates and displays the game window.
     */
    public TicTacToeFrame() {
        game = new TicTacToe();
        game.setFrame(this);
        buttons = new TicTacToeButton[3][3];

        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setLocationRelativeTo(null);

        initComponents();

        setVisible(true);
    }

    /**
     * Initializes all GUI components and layout.
     */
    private void initComponents() {
        setLayout(new BorderLayout());

        // --- Create the 3x3 board (no spacing, no border) ---
        JPanel boardPanel = new JPanel(new GridLayout(3, 3, 0, 0));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // no border

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new TicTacToeButton(row, col);
                final int r = row;
                final int c = col;

                buttons[row][col].addActionListener(e -> handleButtonClick(r, c));

                boardPanel.add(buttons[row][col]);
            }
        }

        add(boardPanel, BorderLayout.CENTER);

        // --- Create bottom control panel ---
        JPanel controlPanel = new JPanel(new BorderLayout());

        // Center panel for status label
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        statusLabel = new JLabel("Player X's turn");
        statusLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        centerPanel.add(statusLabel);
        controlPanel.add(centerPanel, BorderLayout.CENTER);

        // Right panel for buttons
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        resetButton = new JButton("Reset");
        resetButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        resetButton.addActionListener(e -> {
            game.newGame();
            resetDisplay();
            statusLabel.setText("Player X's turn");
        });

        quitButton = new JButton("Quit");
        quitButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        quitButton.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(
                    TicTacToeFrame.this,
                    "Are you sure you want to quit?",
                    "Quit Game",
                    JOptionPane.YES_NO_OPTION
            );
            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        rightPanel.add(resetButton);
        rightPanel.add(quitButton);
        controlPanel.add(rightPanel, BorderLayout.EAST);

        add(controlPanel, BorderLayout.SOUTH);
    }

    /**
     * Handles button click events.
     * @param row The row of clicked button
     * @param col The column of clicked button
     */
    private void handleButtonClick(int row, int col) {
        // Get the current player BEFORE making the move
        String playerMakingMove = game.getCurrentPlayer();

        if (game.makeMove(row, col)) {
            // Display the player who JUST moved
            buttons[row][col].setText(playerMakingMove);
            updateDisplay();
        }
    }

    /**
     * Updates the display based on current game state.
     */
    private void updateDisplay() {
        if (game.isGameOver()) {
            String winner = game.getWinner();
            String message;

            if (winner.equals("Tie")) {
                message = "It's a Tie!";
                statusLabel.setText("Game Over - It's a Tie!");
            } else {
                message = "Player " + winner + " wins!";
                statusLabel.setText("Player " + winner + " Wins!");
            }

            disableAllButtons();

            // Show popup with result
            JOptionPane.showMessageDialog(
                    this,
                    message,
                    "Game Over",
                    JOptionPane.INFORMATION_MESSAGE
            );

            // Ask if they want to play again
            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Would you like to play again?",
                    "Play Again?",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {
                game.newGame();
                resetDisplay();
                statusLabel.setText("Player X's turn");
            } else {
                System.exit(0);
            }
        } else {
            statusLabel.setText("Player " + game.getCurrentPlayer() + "'s turn");
        }
    }


    /**
     * Disables all game buttons (when game is over).
     */
    private void disableAllButtons() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setEnabled(false);
            }
        }
    }

    /**
     * Resets the display for a new game.
     */
    private void resetDisplay() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText(" ");
                buttons[row][col].setEnabled(true);
            }
        }
    }

    /**
     * Main method to start the application.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToeFrame::new);
    }
}
