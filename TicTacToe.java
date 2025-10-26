import java.util.Scanner;

// Represents the game board
class GameBoard {
    private char[][] board;

    public GameBoard() {
        board = new char[3][3];
        resetBoard();
    }

    public void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public void printBoard() {
        System.out.println("\n-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    public boolean makeMove(int row, int col, char playerSymbol) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3) {
            System.out.println("❌ Invalid position. Try again!");
            return false;
        }
        if (board[row][col] != ' ') {
            System.out.println("⚠️  Position already taken. Try again!");
            return false;
        }
        board[row][col] = playerSymbol;
        return true;
    }

    public boolean checkWin(char player) {
        // Rows & Columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                (board[0][i] == player && board[1][i] == player && board[2][i] == player))
                return true;
        }

        // Diagonals
        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
            (board[0][2] == player && board[1][1] == player && board[2][0] == player))
            return true;

        return false;
    }

    public boolean isFull() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ')
                    return false;
        return true;
    }
}

// Main Game Logic
public class TicTacToe {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GameBoard board = new GameBoard();

        System.out.println("🎮 Welcome to Java Tic Tac Toe!");
        System.out.print("Enter name for Player 1 (X): ");
        String player1 = sc.nextLine();
        System.out.print("Enter name for Player 2 (O): ");
        String player2 = sc.nextLine();

        boolean playAgain = true;

        while (playAgain) {
            board.resetBoard();
            char currentPlayer = 'X';
            boolean gameOver = false;

            while (!gameOver) {
                board.printBoard();
                System.out.println("\n" + (currentPlayer == 'X' ? player1 : player2) + "'s turn (" + currentPlayer + ")");
                System.out.print("Enter row (0, 1, 2): ");
                int row = getValidInt(sc);
                System.out.print("Enter column (0, 1, 2): ");
                int col = getValidInt(sc);

                if (!board.makeMove(row, col, currentPlayer))
                    continue;

                if (board.checkWin(currentPlayer)) {
                    board.printBoard();
                    System.out.println("\n🏆 " + (currentPlayer == 'X' ? player1 : player2) + " wins!");
                    gameOver = true;
                } else if (board.isFull()) {
                    board.printBoard();
                    System.out.println("\n🤝 It's a draw!");
                    gameOver = true;
                } else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            }

            System.out.print("\nDo you want to play again? (yes/no): ");
            String choice = sc.next().toLowerCase();
            playAgain = choice.equals("yes");
        }

        System.out.println("👋 Thanks for playing Tic Tac Toe!");
        sc.close();
    }

    // Helper method for safe number input
    private static int getValidInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.print("❌ Please enter a valid number: ");
            sc.next();
        }
        return sc.nextInt();
    }
}
