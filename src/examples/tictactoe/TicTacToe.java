package examples.tictactoe;

import nootovich.nglib.*;

import static examples.tictactoe.Main.*;

public class TicTacToe extends NGMain {

    private long restartTime = 0;

    public void main() {
        setTickRate(30);
        setFrameRate(60);
        window = new NGWindow(w, h, new TicTacToeRenderer(), this); // TODO: rework
        start();
    }

    public void win(byte player) {
        if (restartTime > 0) return;
        System.out.printf("Player '%c' won!\n", player == 1 ? 'O' : 'X');
        restartTime = System.currentTimeMillis() + 1000;
    }

    @Override
    public void update() {
        // TODO: reset breaks after hot-reloading
        if (restartTime > 0 && System.currentTimeMillis() > restartTime) {
            restartTime = 0;
            board       = new byte[3][3];
            TicTacToeRenderer.sprites.clear();
        }
    }

    @Override
    public void onLMBPressed(NGVec2i pos) {
        if (restartTime > 0) return;
        // TODO: change to "pos.div(cellWidth, cellHeight).in(board) = (byte) (player ? 1 : 2);" after NGVec rework
        int cellX = pos.x / cellWidth;
        int cellY = pos.y / cellHeight;
        if (board[cellY][cellX] != 0) return;

        board[cellY][cellX] = player;
        TicTacToeRenderer.addShape(cellX, cellY, player);

        player = (byte) (player == 1 ? 2 : 1);

        { // CHECK WIN CONDITIONS
            for (int y = 0; y < BOARD_SIZE; y++) {
                if (board[y][0] > 0 && board[y][0] == board[y][1] && board[y][1] == board[y][2]) {
                    TicTacToeRenderer.addHorizontalLine(y);
                    win(board[y][0]);
                }
            }
            for (int x = 0; x < BOARD_SIZE; x++) {
                if (board[0][x] > 0 && board[0][x] == board[1][x] && board[1][x] == board[2][x]) {
                    TicTacToeRenderer.addVerticalLine(x);
                    win(board[0][x]);
                }
            }
            if (board[0][0] > 0 && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
                TicTacToeRenderer.addDiagonalLineBackward();
                win(board[1][1]);
            }
            if (board[0][2] > 0 && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
                TicTacToeRenderer.addDiagonalLineForward();
                win(board[1][1]);
            }
        } // CHECK WIN CONDITIONS

checkDraw:
        { // CHECK DRAW CONDITION
            if (restartTime > 0) break checkDraw;
            for (int y = 0; y < BOARD_SIZE; y++) {
                for (int x = 0; x < BOARD_SIZE; x++) {
                    if (board[y][x] == 0) break checkDraw;
                }
            }
            System.out.println("Draw!");
            restartTime = System.currentTimeMillis() + 1000;
        } // CHECK DRAW CONDITION
    }

    @Override
    public void onEscapePress() {
        window.shouldClose = true; // TODO: exit(<float waitTime>?) in NGMain that does this
    }
}
