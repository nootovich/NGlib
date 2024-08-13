package examples.tictactoe;

import nootovich.nglib.NGVec2i;

public class Main {

    public static final int BOARD_SIZE = 3;

    public static int     W           = 600;
    public static int     H           = 400;
    public static int     MIN_WH      = Math.min(W, H);
    public static NGVec2i WINDOW_SIZE = new NGVec2i(W, H);

    public static int      CELL_SIZE = MIN_WH / BOARD_SIZE;
    public static byte[][] BOARD     = new byte[BOARD_SIZE][BOARD_SIZE];
    public static byte     PLAYER    = 1;

    public static final int MARGIN_FIXED = 10;

    public static int     MARGIN_W = (W - MIN_WH) / 2 + MARGIN_FIXED;
    public static int     MARGIN_H = (H - MIN_WH) / 2 + MARGIN_FIXED;
    public static NGVec2i MARGIN   = new NGVec2i(MARGIN_W, MARGIN_H);

    public static int LINE_LEN = MIN_WH - MARGIN_FIXED * 2;

    public static void main(String[] args) {
        new TicTacToe().main();
    }
}
