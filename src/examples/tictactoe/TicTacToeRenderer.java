package examples.tictactoe;

import java.awt.Color;
import nootovich.nglib.NGGraphics;
import nootovich.nglib.NGRenderer;

import static examples.tictactoe.Main.*;

public class TicTacToeRenderer extends NGRenderer {

    private static final Color COLOR_PLAYER1 = new Color(0x6969FF);
    private static final Color COLOR_PLAYER2 = new Color(0xFF6969);

    private static final int MARGIN_H = (int) (w - Math.min(w, h) + w * 0.05f);
    private static final int MARGIN_V = (int) (h - Math.min(w, h) + h * 0.05f);

    @Override
    public void render(NGGraphics g) {

        // TODO: all of this would be nice to refactor with new and improved `NGVec` whenever it's ready

        g.drawRect(0, 0, w, h, Color.DARK_GRAY);
        g.drawRoundedLine(cellWidth, MARGIN_V, cellWidth, h - MARGIN_V, Color.GRAY, 3);
        g.drawRoundedLine(cellWidth * 2, MARGIN_V, cellWidth * 2, h - MARGIN_V, Color.GRAY, 3);
        g.drawRoundedLine(MARGIN_H, cellHeight, w - MARGIN_H, cellHeight, Color.GRAY, 3);
        g.drawRoundedLine(MARGIN_H, cellHeight * 2, w - MARGIN_H, cellHeight * 2, Color.GRAY, 3);

        for (int y = 0; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                if (board[y][x] == 1) {
                    int cx = x * cellWidth + MARGIN_H;
                    int cy = y * cellHeight + MARGIN_V;
                    int cd = cellWidth - Math.min(MARGIN_H, MARGIN_V) * 2;
                    g.drawCircleBorder(cx, cy, cd, COLOR_PLAYER1, 8);
                } else if (board[y][x] == 2) {
                    int cx1 = x * cellWidth + MARGIN_H;
                    int cy1 = y * cellHeight + MARGIN_V;
                    int cx2 = (x + 1) * cellWidth - MARGIN_H;
                    int cy2 = (y + 1) * cellHeight - MARGIN_V;
                    g.drawRoundedLine(cx1, cy1, cx2, cy2, COLOR_PLAYER2, 8);
                    g.drawRoundedLine(cx2, cy1, cx1, cy2, COLOR_PLAYER2, 8);
                }
            }
        }
    }
}
