package examples.snake;

import nootovich.nglib.NGGraphics;
import nootovich.nglib.NGRenderer;
import nootovich.nglib.NGVec2f;
import nootovich.nglib.NGVec4i;

import java.awt.*;

public class SnakeRenderer extends NGRenderer {

    private static final Color COLOR_BG           = new Color(0x141820);
    private static final Color COLOR_SNAKE        = new Color(0xFFFFFF);
    private static final Color COLOR_SNAKE_BORDER = new Color(0x000000);
    private static final Color COLOR_FOOD         = new Color(0x166236);
    private static final Color COLOR_FOOD_BORDER  = new Color(0xFFFFFF);

    private static final int EYE_RADIUS = Snake.cellSize / 4;
    private static final int EYE_LEFT   = (int) (Snake.cellSize * 0.275f);
    private static final int EYE_RIGHT  = (int) (Snake.cellSize * 0.725f);

    public long prevTime = System.currentTimeMillis();

    @Override
    public void render(NGGraphics g) {
        long  curTime = System.currentTimeMillis();
        float dt      = (curTime - prevTime) / 1000f;
        prevTime = curTime;
        { // BG
            g.drawRect(0, 0, Snake.w, Snake.h, COLOR_BG);
        }
        { // FOOD
            NGVec4i foodPos = new NGVec4i(Snake.foodPosition, 1, 1).scale(Snake.cellSize);
            g.drawRect(foodPos, COLOR_FOOD);
            g.drawRectBorder(foodPos, COLOR_FOOD_BORDER);
        }
        { // SNAKE
            for (Snake.SnakePart part : Snake.snake) {
                part.anim.update(dt);
                g.drawRect(part.anim.state, Snake.cellSize, COLOR_SNAKE);
                g.drawRectBorder(part.anim.state, Snake.cellSize, COLOR_SNAKE_BORDER);
            }
        }
        { // EYES
            Snake.SnakePart head     = Snake.snake.getLast();
            int             ldir     = head.dir.ordinal();
            int             rdir     = (ldir + 1) % 4;
            NGVec2f         leftEye  = head.anim.state.add((ldir % 3) < 1 ? EYE_LEFT : EYE_RIGHT, ldir < 2 ? EYE_LEFT : EYE_RIGHT);
            NGVec2f         rightEye = head.anim.state.add((rdir % 3) < 1 ? EYE_LEFT : EYE_RIGHT, rdir < 2 ? EYE_LEFT : EYE_RIGHT);
            g.drawCircleCentered(leftEye, EYE_RADIUS, Color.BLACK);
            g.drawCircleCentered(rightEye, EYE_RADIUS, Color.BLACK);
        }
    }
}
