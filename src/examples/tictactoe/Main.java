package examples.tictactoe;

public class Main {

    public static final int BOARD_SIZE = 3;
    public static final int MARGIN_FIXED = 10;

    public static byte[][] BOARD     = new byte[BOARD_SIZE][BOARD_SIZE];
    public static byte     PLAYER    = 1;

    public static void main(String[] args) {
        new TicTacToe().main();
    }
}
