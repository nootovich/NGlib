package examples.snake;

import examples.snake.Snake.SnakePart;
import nootovich.nglib.*;

import static examples.snake.Main.*;

public class SnakeRenderer extends NGRenderer {

    private static final NGColor COLOR_BG             = new NGColor(0x141820);
    private static final NGColor COLOR_SNAKE          = new NGColor(0xFFFFFF);
    private static final NGColor COLOR_SNAKE_BORDER   = new NGColor(0x000000);
    private static final NGColor COLOR_SNAKE_EYES     = new NGColor(0x000000);
    private static final NGColor COLOR_FOOD           = new NGColor(0x166236);
    private static final NGColor COLOR_FOOD_HIGHLIGHT = new NGColor(0x338F54);
    private static final NGColor COLOR_FOOD_BORDER    = new NGColor(0xFFFFFF);
    private static final NGColor COLOR_SCORE          = new NGColor(0x507DBE);

    private static final int EYE_RADIUS = cellSize / 4;
    private static final int EYE_LEFT   = (int) (cellSize * 0.275f);
    private static final int EYE_RIGHT  = (int) (cellSize * 0.725f);

    public long prevTime = System.currentTimeMillis();

    public static boolean highlightFood = false;

    @Override
    public void render(NGGraphics g) {
        long  curTime = System.currentTimeMillis();
        float dt      = (curTime - prevTime) / 1000f;
        prevTime = curTime;
        { // BG
            g.drawRect(0, 0, w, h, COLOR_BG);
        }
        { // FOOD
            NGVec4i foodPos = new NGVec4i(foodPosition, 1, 1).scale(cellSize);
            g.drawRect(foodPos, highlightFood ? COLOR_FOOD_HIGHLIGHT : COLOR_FOOD);
            g.drawRectBorder(foodPos, COLOR_FOOD_BORDER);
        }
        { // SNAKE
            for (SnakePart part: snake) {
                part.anim.update(dt);
                g.drawRect(part.anim.state, cellSize, COLOR_SNAKE);
                g.drawRectBorder(part.anim.state, cellSize, COLOR_SNAKE_BORDER);
            }
        }
        { // EYES
            SnakePart head     = snake.getLast();
            int       ldir     = head.dir.ordinal();
            int       rdir     = (ldir + 1) % 4;
            NGVec2f   leftEye  = head.anim.state.add((ldir % 3) < 1 ? EYE_LEFT : EYE_RIGHT, ldir < 2 ? EYE_LEFT : EYE_RIGHT);
            NGVec2f   rightEye = head.anim.state.add((rdir % 3) < 1 ? EYE_LEFT : EYE_RIGHT, rdir < 2 ? EYE_LEFT : EYE_RIGHT);
            g.drawCircleCentered(leftEye, EYE_RADIUS, COLOR_SNAKE_EYES);
            g.drawCircleCentered(rightEye, EYE_RADIUS, COLOR_SNAKE_EYES);
        }
        { // SCORE
            g.drawTextCentered(String.valueOf(score), w / 2, cellSize / 2, COLOR_SCORE);
        }
    }
}
