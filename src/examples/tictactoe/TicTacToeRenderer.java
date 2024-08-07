package examples.tictactoe;

import java.awt.Color;
import nootovich.nglib.NGGraphics;
import nootovich.nglib.NGRenderer;

import static examples.tictactoe.Main.*;

public class TicTacToeRenderer extends NGRenderer {

    @Override
    public void render(NGGraphics g) {

        g.drawRect(0, 0, w, h, Color.DARK_GRAY);
        g.drawRect(cellWidth, 0, 3, h, Color.GRAY);
        g.drawRect(cellWidth * 2, 0, 3, h, Color.GRAY);
        g.drawRect(0, cellHeight, w, 3, Color.GRAY);
        g.drawRect(0, cellHeight * 2, w, 3, Color.GRAY);

        for (int y = 0; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                if (board[y][x] == 1) {
                    g.drawCircleBorder(x * cellWidth, y * cellHeight, Math.min(cellWidth, cellHeight), Color.BLUE);
                } else if (board[y][x] == 2) {
                    g.drawLine(x * cellWidth, y * cellHeight, (x + 1) * cellWidth, (y + 1) * cellHeight, Color.RED);
                    g.drawLine((x + 1) * cellWidth, y * cellHeight, x * cellWidth, (y + 1) * cellHeight, Color.RED);
                }
            }
        }
    }
}
