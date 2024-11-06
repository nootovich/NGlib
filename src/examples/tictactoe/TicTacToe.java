package examples.tictactoe;

import nootovich.nglib.NGMain;
import nootovich.nglib.NGVec2i;

import static examples.tictactoe.Main.*;

public class TicTacToe extends NGMain {

    public static NGVec2i WINDOW_SIZE = new NGVec2i(w, h);

    public static int MIN_WH    = Math.min(w, h);
    public static int CELL_SIZE = MIN_WH / BOARD_SIZE;
    public static int LINE_LEN  = MIN_WH - MARGIN_FIXED * 2;

    public static int     MARGIN_W = (w - MIN_WH) / 2 + MARGIN_FIXED;
    public static int     MARGIN_H = (h - MIN_WH) / 2 + MARGIN_FIXED;
    public static NGVec2i MARGIN   = new NGVec2i(MARGIN_W, MARGIN_H);

    private long restartTime = 0;

    public void main() {
        setTickRate(30);
        setFrameRate(60);
        createWindow(600, 400, new TicTacToeRenderer());
        start();
    }

    public void win(byte player) {
        if (restartTime > 0) return;
        System.out.printf("Player '%c' won!\n", player == 1 ? 'O' : 'X');
        restartTime = System.currentTimeMillis() + 1000;
    }

    @Override
    public void update() {
        if (restartTime > 0 && System.currentTimeMillis() > restartTime) {
            restartTime = 0;
            BOARD       = new byte[BOARD_SIZE][BOARD_SIZE];
            window.renderer.reset();
        }
    }

    @Override
    public void onLMBPress(NGVec2i pos) {
        if (restartTime > 0) return;
        // TODO: change to "pos.sub(MARGIN).div(CELL_SIZE).in(board) = (byte) (player ? 1 : 2);" after NGVec rework
        NGVec2i cell = pos.sub(MARGIN).divide(CELL_SIZE);
        if (0 > cell.x() || cell.x() >= BOARD_SIZE || 0 > cell.y() || cell.y() >= BOARD_SIZE) return;
        if (BOARD[cell.y()][cell.x()] != 0) return;

        BOARD[cell.y()][cell.x()] = PLAYER;
        TicTacToeRenderer.addShape(cell, PLAYER);

        PLAYER = (byte) (PLAYER == 1 ? 2 : 1);

        { // CHECK WIN CONDITIONS
            for (int y = 0; y < BOARD_SIZE; y++) {
                if (BOARD[y][0] > 0 && BOARD[y][0] == BOARD[y][1] && BOARD[y][1] == BOARD[y][2]) {
                    TicTacToeRenderer.addHorizontalLine(y);
                    win(BOARD[y][0]);
                }
            }
            for (int x = 0; x < BOARD_SIZE; x++) {
                if (BOARD[0][x] > 0 && BOARD[0][x] == BOARD[1][x] && BOARD[1][x] == BOARD[2][x]) {
                    TicTacToeRenderer.addVerticalLine(x);
                    win(BOARD[0][x]);
                }
            }
            if (BOARD[0][0] > 0 && BOARD[0][0] == BOARD[1][1] && BOARD[1][1] == BOARD[2][2]) {
                TicTacToeRenderer.addDiagonalLineBackward();
                win(BOARD[1][1]);
            }
            if (BOARD[0][2] > 0 && BOARD[0][2] == BOARD[1][1] && BOARD[1][1] == BOARD[2][0]) {
                TicTacToeRenderer.addDiagonalLineForward();
                win(BOARD[1][1]);
            }
        } // CHECK WIN CONDITIONS

checkDraw:
        { // CHECK DRAW CONDITION
            if (restartTime > 0) break checkDraw;
            for (int y = 0; y < BOARD_SIZE; y++) {
                for (int x = 0; x < BOARD_SIZE; x++) {
                    if (BOARD[y][x] == 0) break checkDraw;
                }
            }
            System.out.println("Draw!");
            restartTime = System.currentTimeMillis() + 1000;
        } // CHECK DRAW CONDITION
    }

    @Override
    public void onWindowResize(int w, int h) {
        WINDOW_SIZE = new NGVec2i(w, h);

        MIN_WH    = Math.min(w, h);
        CELL_SIZE = MIN_WH / BOARD_SIZE;
        LINE_LEN  = MIN_WH - MARGIN_FIXED * 2;

        MARGIN_W = (w - MIN_WH) / 2 + MARGIN_FIXED;
        MARGIN_H = (h - MIN_WH) / 2 + MARGIN_FIXED;
        MARGIN   = new NGVec2i(MARGIN_W, MARGIN_H);

        TicTacToeRenderer.onResize();
    }

    @Override
    public void onRPress() {
        restartTime = System.currentTimeMillis();
    }
}
