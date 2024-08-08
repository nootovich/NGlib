package examples.tictactoe;

import java.awt.Color;
import java.util.ArrayList;
import nootovich.nglib.*;
import nootovich.nglib.NGSprite.NGSpriteType;

import static examples.tictactoe.Main.*;

public class TicTacToeRenderer extends NGRenderer {

    private static final Color COLOR_PLAYER1 = new Color(0x6969FF);
    private static final Color COLOR_PLAYER2 = new Color(0xFF6969);

    private static final int MARGIN_H = (int) (w - Math.min(w, h) + w * 0.05f);
    private static final int MARGIN_V = (int) (h - Math.min(w, h) + h * 0.05f);

    private static final int LINE_THICCNESS = 5;

    public static ArrayList<NGSprite> lines = new ArrayList<>();

    public static void addHorizontalLine(int y) {
        // TODO: Sprite doesn't show up after hot-reloading this class
        NGVec2i  pos   = new NGVec2i(MARGIN_H * 0.5f, (y + 0.5f) * cellHeight);
        NGVec2i  size  = new NGVec2i(w - MARGIN_H, 0);
        Color    color = player == 1 ? COLOR_PLAYER2 : COLOR_PLAYER1;
        NGSprite line  = new NGSprite(pos, size, color, NGSpriteType.LINE);
        line.extra = LINE_THICCNESS * 2;
        lines.add(line);
    }

    public static void addVerticalLine(int x) {
        // TODO: Sprite doesn't show up after hot-reloading this class
        NGVec2i  pos   = new NGVec2i((x + 0.5f) * cellWidth, MARGIN_V * 0.5f);
        NGVec2i  size  = new NGVec2i(0, h - MARGIN_V);
        Color    color = player == 1 ? COLOR_PLAYER2 : COLOR_PLAYER1;
        NGSprite line  = new NGSprite(pos, size, color, NGSpriteType.LINE);
        line.extra = LINE_THICCNESS * 2;
        lines.add(line);
    }

    public static void addDiagonalLineBackward() {
        NGVec2i  pos   = new NGVec2i(MARGIN_H * 0.5f, MARGIN_V * 0.5f);
        NGVec2i  size  = new NGVec2i(w - MARGIN_H, h - MARGIN_V);
        Color    color = player == 1 ? COLOR_PLAYER2 : COLOR_PLAYER1;
        NGSprite line  = new NGSprite(pos, size, color, NGSpriteType.LINE);
        line.extra = LINE_THICCNESS * 2;
        lines.add(line);
    }

    public static void addDiagonalLineForward() {
        NGVec2i  pos   = new NGVec2i(w - MARGIN_H * 0.5f, MARGIN_V * 0.5f);
        NGVec2i  size  = new NGVec2i(-w + MARGIN_H, h - MARGIN_V);
        Color    color = player == 1 ? COLOR_PLAYER2 : COLOR_PLAYER1;
        NGSprite line  = new NGSprite(pos, size, color, NGSpriteType.LINE);
        line.extra = LINE_THICCNESS * 2;
        lines.add(line);
    }

    @Override
    public void render(NGGraphics g) {

        // TODO: all of this would be nice to refactor with new and improved `NGVec` whenever it's ready

        g.drawRect(0, 0, w, h, Color.DARK_GRAY);
        g.drawRoundedLine(cellWidth, MARGIN_V, cellWidth, h - MARGIN_V, Color.GRAY, LINE_THICCNESS);
        g.drawRoundedLine(cellWidth * 2, MARGIN_V, cellWidth * 2, h - MARGIN_V, Color.GRAY, LINE_THICCNESS);
        g.drawRoundedLine(MARGIN_H, cellHeight, w - MARGIN_H, cellHeight, Color.GRAY, LINE_THICCNESS);
        g.drawRoundedLine(MARGIN_H, cellHeight * 2, w - MARGIN_H, cellHeight * 2, Color.GRAY, LINE_THICCNESS);

        for (int y = 0; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                if (board[y][x] == 1) {
                    int cx = x * cellWidth + MARGIN_H;
                    int cy = y * cellHeight + MARGIN_V;
                    int cd = cellWidth - Math.min(MARGIN_H, MARGIN_V) * 2;
                    g.drawCircleBorder(cx, cy, cd, COLOR_PLAYER1, LINE_THICCNESS);
                } else if (board[y][x] == 2) {
                    int cx1 = x * cellWidth + MARGIN_H;
                    int cy1 = y * cellHeight + MARGIN_V;
                    int cx2 = (x + 1) * cellWidth - MARGIN_H;
                    int cy2 = (y + 1) * cellHeight - MARGIN_V;
                    g.drawRoundedLine(cx1, cy1, cx2, cy2, COLOR_PLAYER2, LINE_THICCNESS);
                    g.drawRoundedLine(cx2, cy1, cx1, cy2, COLOR_PLAYER2, LINE_THICCNESS);
                }
            }
        }

        for (NGSprite line: lines) {
            line.draw(g);
        }
    }
}
