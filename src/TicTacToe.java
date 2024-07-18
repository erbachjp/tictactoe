import java.util.Scanner;

public class TicTacToe {
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String board[][] = new String[ROW][COL];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            clearBoard();
            String currentPlayer = "X";
            boolean gameWon = false;
            boolean gameTie = false;

            while (!gameWon && !gameTie) {
                display();
                System.out.println("Player " + currentPlayer + ", enter move:");

                int row = getRangedInt(scanner, "Enter row (1-3): ", 1, 3) - 1;
                int col = getRangedInt(scanner, "Enter column (1-3): ", 1, 3) - 1;

                while (!isValidMove(row, col)) {
                    System.out.println("Invalid move. Try again.");
                    row = getRangedInt(scanner, "Enter row (1-3): ", 1, 3) - 1;
                    col = getRangedInt(scanner, "Enter column (1-3): ", 1, 3) - 1;
                }

                board[row][col] = currentPlayer;

                gameWon = isWin(currentPlayer);
                gameTie = isTie();

                if (!gameWon && !gameTie) {
                    currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";
                }
            }

            display();
            if (gameWon) {
                System.out.println("Player " + currentPlayer + " wins!");
            } else {
                System.out.println("It's a tie!");
            }

            playAgain = getYNConfirm(scanner, "play again? (yes/no) ");
        }

        scanner.close();
    }

    public static int getRangedInt(Scanner console, String prompt, int low, int high) {
        int input;
        do {
            System.out.print(prompt);
            while (!console.hasNextInt()) {
                System.out.println("Invalid input");
                console.next();
            }
            input = console.nextInt();
        } while (input < low || input > high);
        return input;
    }

    public static boolean getYNConfirm(Scanner console, String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = console.next().trim().toLowerCase();
        } while (!input.equals("yes") && !input.equals("no"));
        return input.equals("yes");
    }

    private static void clearBoard() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                board[i][j] = " ";
            }
        }
    }

    private static void display() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                System.out.print(board[i][j]);
                if (j < COL - 1) System.out.print("|");
            }
            System.out.println();
            if (i < ROW - 1) System.out.println("-----");
        }
    }

    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < ROW && col >= 0 && col < COL && board[row][col].equals(" ");
    }

    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    private static boolean isRowWin(String player) {
        for (int i = 0; i < ROW; i++) {
            if (board[i][0].equals(player) && board[i][1].equals(player) && board[i][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String player) {
        for (int j = 0; j < COL; j++) {
            if (board[0][j].equals(player) && board[1][j].equals(player) && board[2][j].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }

    private static boolean isTie() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (board[i][j].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }
}
