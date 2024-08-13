package examples.tictactoe;

import java.awt.Color;
import java.util.ArrayList;
import nootovich.nglib.*;

import static examples.tictactoe.Main.*;
import static nootovich.nglib.NGSprite.NGSpriteType.CIRCLE_BORDER;
import static nootovich.nglib.NGSprite.NGSpriteType.LINE;

// TODO: Sprites don't show up after hot-reloading this class

public class TicTacToeRenderer extends NGRenderer {

    private static final boolean DEBUG = false;

    private static final Color COLOR_PLAYER1 = new Color(0x6969FF);
    private static final Color COLOR_PLAYER2 = new Color(0xFF6969);

    private static final int LINE_THICCNESS = 5;

    private static final float DROP_ANIMATION_OFFSET = 0.25f;

    public static NGSprite[][]        shapeSprites = new NGSprite[BOARD_SIZE][BOARD_SIZE];
    public static ArrayList<NGSprite> lineSprites  = new ArrayList<>();

    private long prevTime = System.currentTimeMillis();

    public static void addHorizontalLine(int y) {
        NGVec2i  pos   = WINDOW_SIZE.sub(MIN_WH).divide(2f).add(MARGIN_FIXED, CELL_SIZE * (y + 0.5f));
        Color    color = PLAYER == 1 ? COLOR_PLAYER2 : COLOR_PLAYER1;
        NGSprite line  = new NGSprite(pos, new NGVec2i(LINE_LEN, 0), color, LINE);
        line.extra = LINE_THICCNESS * 2;
        lineSprites.add(line);
    }

    public static void addVerticalLine(int x) {
        NGVec2i  pos   = WINDOW_SIZE.sub(MIN_WH).divide(2f).add(CELL_SIZE * (x + 0.5f), MARGIN_FIXED);
        Color    color = PLAYER == 1 ? COLOR_PLAYER2 : COLOR_PLAYER1;
        NGSprite line  = new NGSprite(pos, new NGVec2i(0, LINE_LEN), color, LINE);
        line.extra = LINE_THICCNESS * 2;
        lineSprites.add(line);
    }

    public static void addDiagonalLineBackward() {
        Color    color = PLAYER == 1 ? COLOR_PLAYER2 : COLOR_PLAYER1;
        NGSprite line  = new NGSprite(MARGIN, new NGVec2i(LINE_LEN), color, LINE);
        line.extra = LINE_THICCNESS * 2;
        lineSprites.add(line);
    }

    public static void addDiagonalLineForward() {
        NGVec2i  pos   = WINDOW_SIZE.add(MIN_WH, -MIN_WH).divide(2f).add(-MARGIN_FIXED, MARGIN_FIXED);
        Color    color = PLAYER == 1 ? COLOR_PLAYER2 : COLOR_PLAYER1;
        NGSprite line  = new NGSprite(pos, new NGVec2i(-LINE_LEN, LINE_LEN), color, LINE);
        line.extra = LINE_THICCNESS * 2;
        lineSprites.add(line);
    }

    public static void addShape(NGVec2i cell, byte player) {

        NGVec2i pos  = MARGIN.add(cell.scale(CELL_SIZE));
        NGVec2i size = new NGVec2i(CELL_SIZE - MARGIN_FIXED * 2);

        NGSprite shape = new NGSprite(pos, size, player == 1 ? COLOR_PLAYER1 : COLOR_PLAYER2, player == 1 ? CIRCLE_BORDER : LINE);
        shape.extra = LINE_THICCNESS;
        NGAnimation anim = new NGAnimation(pos.toFloat().sub(MARGIN_FIXED * (1 - DROP_ANIMATION_OFFSET)), pos.toFloat(), 0.15f);
        shape.anims.add(anim);
        shapeSprites[cell.y][cell.x] = shape;

        if (player == 2) {
            NGVec2i  pos2      = new NGVec2i(CELL_SIZE - MARGIN_FIXED * 2, 0);
            NGVec2i  size2     = new NGVec2i(-size.x, size.y);
            NGSprite crossHalf = new NGSprite(pos2, size2, COLOR_PLAYER2, LINE);
            crossHalf.extra = LINE_THICCNESS;
            shape.addChild(crossHalf);
        }
    }

    public static void onResize() {
        MARGIN_W = (W - MIN_WH) / 2 + MARGIN_FIXED;
        MARGIN_H = (H - MIN_WH) / 2 + MARGIN_FIXED;
        MARGIN   = new NGVec2i(MARGIN_W, MARGIN_H);
        for (int y = 0; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                if (shapeSprites[y][x] == null) continue;
                addShape(new NGVec2i(x, y), (byte) (shapeSprites[y][x].type == LINE ? 2 : 1));
            }
        }
    }

    @Override
    public void render(NGGraphics g) {
        // TODO: `dt` should be a parameter of `render()` method or a field of `NGRenderer` class
        long  curTime = System.currentTimeMillis();
        float dt      = (curTime - prevTime) / 1000f;
        prevTime = curTime;

        // TODO: all of this would be nice to refactor with new and improved `NGVec` whenever it's ready

        g.drawRect(0, 0, W, H, Color.DARK_GRAY);

        NGVec2i v1 = MARGIN.add(CELL_SIZE - MARGIN_FIXED, 0);
        NGVec2i v2 = MARGIN.add(0, CELL_SIZE - MARGIN_FIXED);
        g.drawRoundedLine(v1, v1.add(0, LINE_LEN), Color.GRAY, LINE_THICCNESS);
        g.drawRoundedLine(v2, v2.add(LINE_LEN, 0), Color.GRAY, LINE_THICCNESS);
        g.drawRoundedLine(v1.add(CELL_SIZE, 0), v1.add(CELL_SIZE, LINE_LEN), Color.GRAY, LINE_THICCNESS);
        g.drawRoundedLine(v2.add(0, CELL_SIZE), v2.add(LINE_LEN, CELL_SIZE), Color.GRAY, LINE_THICCNESS);

        for (int y = 0; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                if (shapeSprites[y][x] == null) continue;
                shapeSprites[y][x].update(dt);
                g.drawSprite(shapeSprites[y][x]);
            }
        }

        // TODO: this should be tucked away into `NGRenderer` class
        for (NGSprite line: lineSprites) {
            line.update(dt);
            g.drawSprite(line);
        }

        Color gradientColor1 = new Color(0x20 << 24, true);
        Color gradientColor2 = new Color(0, true);
        g.drawGradient(new NGVec2i(0, H - MARGIN_H * 2), new NGVec2i(W, MARGIN_H * 2), 0, gradientColor1, gradientColor2);
        g.drawGradient(new NGVec2i(0, 0), new NGVec2i(MARGIN_W * 2, H), 1, gradientColor1, gradientColor2);
        g.drawGradient(new NGVec2i(0, 0), new NGVec2i(W, MARGIN_H * 2), 2, gradientColor1, gradientColor2);
        g.drawGradient(new NGVec2i(W - MARGIN_W * 2, 0), new NGVec2i(MARGIN_W * 2, H), 3, gradientColor1, gradientColor2);

        if (DEBUG) {
            { // MARGINS
                g.drawRect(0, (int) (H * 0.1f), MARGIN_W, (int) (H * 0.8f), new Color(0x69ff0000, true));
                g.drawRect((int) (W * 0.1f), 0, (int) (W * 0.8f), MARGIN_H, new Color(0x6900ff00, true));
                g.drawRect(W - MARGIN_W, (int) (H * 0.1f), MARGIN_W, (int) (H * 0.8f), new Color(0x6900ffff, true));
                g.drawRect((int) (W * 0.1f), H - MARGIN_H, (int) (W * 0.8f), MARGIN_H, new Color(0x69ff00ff, true));
            } // MARGINS
            { // SHAPES
                Color p1  = new Color(COLOR_PLAYER1.getRGB() & 0x69ffffff, true);
                Color p2  = new Color(COLOR_PLAYER2.getRGB() & 0x69ffffff, true);
                Color p2e = new Color(0x6969ff69, true);
                for (int cy = 0; cy < BOARD_SIZE; cy++) {
                    for (int cx = 0; cx < BOARD_SIZE; cx++) {
                        int     plr  = (cx + cy) % 2 + 1;

                        NGVec2i cell = new NGVec2i(cx, cy);
                        NGVec2i pos  = MARGIN.add(cell.scale(CELL_SIZE));//.sub((int) (MARGIN_FIXED * (1 - DROP_ANIMATION_OFFSET)));
                        NGVec2i size = new NGVec2i(CELL_SIZE - MARGIN_FIXED * 2);
                        g.drawRectBorder(pos, size, plr == 1 ? p1 : p2, LINE_THICCNESS);//, plr == 1 ? CIRCLE_BORDER : LINE);

                        NGVec2i pos3      = new NGVec2i(CELL_SIZE - MARGIN_FIXED * 2, 0);
                        NGVec2i pos4      = new NGVec2i(-size.x, size.y);
                        NGVec2i dbgOffset = new NGVec2i(MARGIN_FIXED).divide(2).negY();
                        g.drawRectBorder(pos3.add(pos).sub(dbgOffset), pos4.add(dbgOffset.scale(2)), p2e, LINE_THICCNESS);//, LINE);
                    }
                }
            } // SHAPES
            { // WIN LINES
                for (int i = 0; i < BOARD_SIZE; i++) {
                    // HORIZONTAL
                    NGVec2i pos1 = WINDOW_SIZE.sub(MIN_WH).divide(2f).add(MARGIN_FIXED, CELL_SIZE * (i + 0.5f));
                    g.drawLine(pos1, pos1.add(LINE_LEN, 0), Color.RED);

                    // VERTICAL
                    NGVec2i pos2 = WINDOW_SIZE.sub(MIN_WH).divide(2f).add(CELL_SIZE * (i + 0.5f), MARGIN_FIXED);
                    g.drawLine(pos2, pos2.add(0, LINE_LEN), Color.GREEN);
                }

                // DIAGONAL BACKWARDS
                g.drawLine(MARGIN, MARGIN.add(LINE_LEN), Color.CYAN);

                // DIAGONAL FORWARDS
                NGVec2i pos4 = WINDOW_SIZE.add(MIN_WH, -MIN_WH).divide(2f).add(-MARGIN_FIXED, MARGIN_FIXED);
                g.drawLine(pos4, pos4.add(-LINE_LEN, LINE_LEN), Color.MAGENTA);
            } // WIN LINES
        }
    }
}
