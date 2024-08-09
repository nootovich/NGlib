package examples.tictactoe;

import java.awt.Color;
import java.util.ArrayList;
import nootovich.nglib.*;

import static examples.tictactoe.Main.*;
import static nootovich.nglib.NGSprite.NGSpriteType.CIRCLE_BORDER;
import static nootovich.nglib.NGSprite.NGSpriteType.LINE;

public class TicTacToeRenderer extends NGRenderer {

    private static final Color COLOR_PLAYER1 = new Color(0x6969FF);
    private static final Color COLOR_PLAYER2 = new Color(0xFF6969);

    private static final int     MARGIN_H = (int) (w - Math.min(w, h) + w * 0.05f);
    private static final int     MARGIN_V = (int) (h - Math.min(w, h) + h * 0.05f);
    private static final NGVec2i MARGIN   = new NGVec2i(MARGIN_H, MARGIN_V);

    private static final int LINE_THICCNESS = 5;

    private static final float DROP_ANIMATION_OFFSET = 0.25f;

    public static ArrayList<NGSprite> sprites = new ArrayList<>();

    private long prevTime = System.currentTimeMillis();

    public static void addHorizontalLine(int y) {
        // TODO: Sprite doesn't show up after hot-reloading this class
        NGVec2i  pos   = new NGVec2i(MARGIN_H * 0.5f, (y + 0.5f) * cellHeight);
        NGVec2i  size  = new NGVec2i(w - MARGIN_H, 0);
        Color    color = player == 1 ? COLOR_PLAYER2 : COLOR_PLAYER1;
        NGSprite line  = new NGSprite(pos, size, color, LINE);
        line.extra = LINE_THICCNESS * 2;
        sprites.add(line);
    }

    public static void addVerticalLine(int x) {
        // TODO: Sprite doesn't show up after hot-reloading this class
        NGVec2i  pos   = new NGVec2i((x + 0.5f) * cellWidth, MARGIN_V * 0.5f);
        NGVec2i  size  = new NGVec2i(0, h - MARGIN_V);
        Color    color = player == 1 ? COLOR_PLAYER2 : COLOR_PLAYER1;
        NGSprite line  = new NGSprite(pos, size, color, LINE);
        line.extra = LINE_THICCNESS * 2;
        sprites.add(line);
    }

    public static void addDiagonalLineBackward() {
        NGVec2i  pos   = new NGVec2i(MARGIN_H * 0.5f, MARGIN_V * 0.5f);
        NGVec2i  size  = new NGVec2i(w - MARGIN_H, h - MARGIN_V);
        Color    color = player == 1 ? COLOR_PLAYER2 : COLOR_PLAYER1;
        NGSprite line  = new NGSprite(pos, size, color, LINE);
        line.extra = LINE_THICCNESS * 2;
        sprites.add(line);
    }

    public static void addDiagonalLineForward() {
        NGVec2i  pos   = new NGVec2i(w - MARGIN_H * 0.5f, MARGIN_V * 0.5f);
        NGVec2i  size  = new NGVec2i(-w + MARGIN_H, h - MARGIN_V);
        Color    color = player == 1 ? COLOR_PLAYER2 : COLOR_PLAYER1;
        NGSprite line  = new NGSprite(pos, size, color, LINE);
        line.extra = LINE_THICCNESS * 2;
        sprites.add(line);
    }

    public static void addShape(int cellX, int cellY, byte player) {

        NGVec2i offset = MARGIN.scale(1.0f - DROP_ANIMATION_OFFSET);
        NGVec2i pos1   = new NGVec2i(cellX * cellWidth, cellY * cellHeight).add(offset);
        NGVec2i pos2   = new NGVec2i(cellWidth, cellHeight).sub(MARGIN.scale(2));

        NGSprite shape = new NGSprite(pos1, pos2, player == 1 ? COLOR_PLAYER1 : COLOR_PLAYER2, player == 1 ? CIRCLE_BORDER : LINE);
        shape.extra = LINE_THICCNESS;
        shape.addAnimPosRelative(MARGIN.scale(DROP_ANIMATION_OFFSET).toFloat(), 0.15f);
        sprites.add(shape);

        if (player == 2) {
            NGVec2i  pos3      = new NGVec2i(cellWidth - MARGIN_H * 2, 0);
            NGVec2i  pos4      = new NGVec2i(-cellWidth + MARGIN_H * 2, pos2.y);
            NGSprite crossHalf = new NGSprite(pos3, pos4, COLOR_PLAYER2, LINE);
            crossHalf.extra = LINE_THICCNESS;
            shape.addChild(crossHalf);
        }
    }

    @Override
    public void render(NGGraphics g) {
        // TODO: `dt` should be a parameter of `render()` method or a field of `NGRenderer` class
        long  curTime = System.currentTimeMillis();
        float dt      = (curTime - prevTime) / 1000f;
        prevTime = curTime;

        // TODO: all of this would be nice to refactor with new and improved `NGVec` whenever it's ready

        g.drawRect(0, 0, w, h, Color.DARK_GRAY);
        g.drawRoundedLine(cellWidth, MARGIN_V, cellWidth, h - MARGIN_V, Color.GRAY, LINE_THICCNESS);
        g.drawRoundedLine(cellWidth * 2, MARGIN_V, cellWidth * 2, h - MARGIN_V, Color.GRAY, LINE_THICCNESS);
        g.drawRoundedLine(MARGIN_H, cellHeight, w - MARGIN_H, cellHeight, Color.GRAY, LINE_THICCNESS);
        g.drawRoundedLine(MARGIN_H, cellHeight * 2, w - MARGIN_H, cellHeight * 2, Color.GRAY, LINE_THICCNESS);

        // TODO: this should be tucked away into `NGRenderer` class
        for (NGSprite sprite: sprites) {
            sprite.update(dt);
            g.drawSprite(sprite);
        }

        Color gradientColor1 = new Color(0x20 << 24, true);
        Color gradientColor2 = new Color(0, true);
        g.drawGradient(new NGVec2i(0, h - MARGIN_V * 2), new NGVec2i(w, MARGIN_V * 2), 0, gradientColor1, gradientColor2);
        g.drawGradient(new NGVec2i(0, 0), new NGVec2i(MARGIN_H * 2, h), 1, gradientColor1, gradientColor2);
        g.drawGradient(new NGVec2i(0, 0), new NGVec2i(w, MARGIN_V * 2), 2, gradientColor1, gradientColor2);
        g.drawGradient(new NGVec2i(w - MARGIN_H * 2, 0), new NGVec2i(MARGIN_H * 2, h), 3, gradientColor1, gradientColor2);
    }
}
