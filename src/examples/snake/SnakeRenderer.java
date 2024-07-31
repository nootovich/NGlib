package examples.snake;

import examples.snake.Snake.SnakePart;
import java.awt.Color;
import nootovich.nglib.NGGraphics;
import nootovich.nglib.NGRenderer;

import static examples.snake.Main.*;

public class SnakeRenderer extends NGRenderer {

    private static final Color COLOR_BG             = new Color(0x141820);
    public static final  Color COLOR_SNAKE          = new Color(0xFFFFFF);
    public static final  Color COLOR_SNAKE_BORDER   = new Color(0x000000);
    public static final  Color COLOR_SNAKE_EYE      = new Color(0x000000);
    public static final  Color COLOR_FOOD           = new Color(0x166236);
    public static final  Color COLOR_FOOD_HIGHLIGHT = new Color(0x338F54);
    public static final  Color COLOR_FOOD_BORDER    = new Color(0xFFFFFF);
    private static final Color COLOR_SCORE          = new Color(0x507DBE);

    public static final int EYE_RADIUS = cellSize / 4;
    public static final int EYE_LEFT   = (int) (cellSize * 0.275f);
    public static final int EYE_RIGHT  = (int) (cellSize * 0.725f);

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
            food.update(dt);
            g.drawSprite(food);
        }
        { // SNAKE
            for (SnakePart part: snake) {
                part.update(dt);
                g.drawSprite(part);
            }
        }
        { // SCORE
            g.drawTextCentered(String.valueOf(score), w / 2, cellSize / 2, COLOR_SCORE);
        }
    }
}
