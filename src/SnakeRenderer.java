import nootovich.nglib.NGGraphics;
import nootovich.nglib.NGRenderer;

import java.awt.*;

public class SnakeRenderer extends NGRenderer {

    private final Color COLOR_BG           = new Color(0x141820);
    private final Color COLOR_SNAKE        = new Color(0xFFFFFF);
    private final Color COLOR_SNAKE_BORDER = new Color(0x000000);
    private final Color COLOR_FOOD         = new Color(0x166236);
    private final Color COLOR_FOOD_BORDER  = new Color(0xFFFFFF);

    public long prevTime = System.currentTimeMillis();

    @Override
    public void render(NGGraphics g) {
        long  curTime = System.currentTimeMillis();
        float dt      = (curTime - prevTime) / 1000f;
        prevTime = curTime;

        g.drawRect(0, 0, Snake.w, Snake.h, COLOR_BG);

        g.drawRect(Snake.foodPosition[0] * Snake.cellSize, Snake.foodPosition[1] * Snake.cellSize, Snake.cellSize, Snake.cellSize, COLOR_FOOD);
        g.drawRectBorder(Snake.foodPosition[0] * Snake.cellSize, Snake.foodPosition[1] * Snake.cellSize, Snake.cellSize, Snake.cellSize, COLOR_FOOD_BORDER);

        for (Snake.SnakePart part : Snake.snake) {
            g.drawRect(part.anim.state, Snake.cellSize, COLOR_SNAKE);
            g.drawRectBorder(part.anim.state, Snake.cellSize, COLOR_SNAKE_BORDER);
            part.anim.update(dt);
        }

        // TODO: Bring eyes back
        Snake.SnakePart head     = Snake.snake.getLast();
        float[][]       eyeCords = new float[][]{{0.15f, 0.15f}, {0.60f, 0.15f}, {0.60f, 0.60f}, {0.15f, 0.60f}};
        int             snakeDir = head.dir.ordinal();
        int             otherDir = (snakeDir + 1) % 4;
        int             snakeX1  = (int) ((head.x + eyeCords[snakeDir][0]) * Snake.cellSize);
        int             snakeY1  = (int) ((head.y + eyeCords[snakeDir][1]) * Snake.cellSize);
        int             snakeX2  = (int) ((head.x + eyeCords[otherDir][0]) * Snake.cellSize);
        int             snakeY2  = (int) ((head.y + eyeCords[otherDir][1]) * Snake.cellSize);
        int             radius   = (int) (Snake.cellSize * 0.25f);

//            g.drawCircle(snakeX1, snakeY1, radius, Color.BLACK);
//            g.drawCircle(snakeX2, snakeY2, radius, Color.BLACK);
    }
}
