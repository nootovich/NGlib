package examples.tictactoe;

import nootovich.nglib.*;

import static examples.tictactoe.Main.*;

public class TicTacToe extends NGMain {

    public void main() {
        setTickRate(30);
        setFrameRate(60);
        window = new NGWindow(w, h, new TicTacToeRenderer(), this); // TODO: rework
        start();
    }

    public void win(byte player) {
        System.out.printf("Player '%c' won!\n", player == 1 ? 'O' : 'X');
        window.shouldClose = true;
    }

    public void checkWin() {
        for (int y = 0; y < BOARD_SIZE; y++) {
            if (board[y][0] > 0 && board[y][0] == board[y][1] && board[y][1] == board[y][2]) win(board[y][0]);
        }
        for (int x = 0; x < BOARD_SIZE; x++) {
            if (board[0][x] > 0 && board[0][x] == board[1][x] && board[1][x] == board[2][x]) win(board[0][x]);
        }
        if (board[0][0] > 0 && board[0][0] == board[1][1] && board[1][1] == board[2][2]) win(board[1][1]);
        if (board[0][2] > 0 && board[0][2] == board[1][1] && board[1][1] == board[2][0]) win(board[1][1]);
    }

    @Override
    public void onLMBPressed(NGVec2i pos) {
        // TODO: change to "pos.div(cellWidth, cellHeight).in(board) = (byte) (player ? 1 : 2);" after NGVec rework
        int x = pos.x / cellWidth;
        int y = pos.y / cellHeight;
        board[y][x] = player;
        player      = (byte) (player == 1 ? 2 : 1);
        checkWin();
    }

    @Override
    public void onEscapePress() {
        window.shouldClose = true; // TODO: exit(<float waitTime>?) in NGMain that does this
    }
}
