package examples.tictactoe;

public class Main {

    public static final int BOARD_SIZE = 3;

    public static int w = 400, h = 400;
    public static int cellWidth = w / BOARD_SIZE, cellHeight = h / BOARD_SIZE;
    public static byte[][] board  = new byte[BOARD_SIZE][BOARD_SIZE];
    public static byte     player = 1;

    public static void main(String[] args) {
        new TicTacToe().main();
    }
}
