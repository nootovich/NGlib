import nootovich.nglib.NGGraphics;
import nootovich.nglib.NGRenderer;
import nootovich.nglib.NGSprite;
import nootovich.nglib.NGVec2f;

import java.awt.*;

public class SnakeRenderer extends NGRenderer {

    private static final Color COLOR_BG           = new Color(0x141820);
    private static final Color COLOR_SNAKE        = new Color(0xFFFFFF);
    private static final Color COLOR_SNAKE_BORDER = new Color(0x000000);
    private static final Color COLOR_FOOD         = new Color(0x166236);
    private static final Color COLOR_FOOD_BORDER  = new Color(0xFFFFFF);

    private static final NGVec2f[] eyeCords = NGVec2f.createArray(new float[][]{{0.15f, 0.15f}, {0.60f, 0.15f}, {0.60f, 0.60f}, {0.15f, 0.60f}});

    private static final Color[][] spritePixels = new Color[][]{
            {Color.BLUE, Color.BLUE, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.BLUE, Color.BLUE},
            {Color.BLUE, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.BLUE},
            {Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.BLACK, Color.YELLOW, Color.YELLOW, Color.YELLOW},
            {Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.BLUE, Color.BLUE, Color.BLUE},
            {Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE},
            {Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.BLUE, Color.BLUE},
            {Color.BLUE, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.BLUE},
            {Color.BLUE, Color.BLUE, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.BLUE, Color.BLUE}
    };

    private static final NGSprite sprite = new NGSprite(spritePixels);

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
            g.drawRect(Snake.foodPosition[0] * Snake.cellSize, Snake.foodPosition[1] * Snake.cellSize, Snake.cellSize, Snake.cellSize, COLOR_FOOD);
            g.drawRectBorder(Snake.foodPosition[0] * Snake.cellSize, Snake.foodPosition[1] * Snake.cellSize, Snake.cellSize, Snake.cellSize, COLOR_FOOD_BORDER);
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
            NGVec2f         leftEye  = eyeCords[head.dir.ordinal()].scale(Snake.cellSize).add(head.anim.state);
            NGVec2f         rightEye = eyeCords[(head.dir.ordinal() + 1) % 4].scale(Snake.cellSize).add(head.anim.state);
            g.drawCircle(leftEye, Snake.cellSize / 4, Color.BLACK);
            g.drawCircle(rightEye, Snake.cellSize / 4, Color.BLACK);
        }
        { // SPRITE TESTING
            g.drawPixelSprite(0, 0, 200, 200, sprite);
        }
    }
}
