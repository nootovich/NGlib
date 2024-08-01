package examples.snake;

import examples.snake.Snake.SnakePart;
import nootovich.nglib.NGGraphics;
import nootovich.nglib.NGRenderer;

import static examples.snake.Main.*;

public class SnakeRenderer extends NGRenderer {

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
            food.color = highlightFood ? COLOR_FOOD_HIGHLIGHT : COLOR_FOOD;
            g.drawSprite(food);
        }
        { // SNAKE
            for (SnakePart part: snake) {
                part.update(dt);
                g.drawSprite(part);
            }
        }
        { // SCORE
            g.drawTextCentered(String.valueOf(score), w / 2, CELL_SIZE / 2, COLOR_SCORE);
        }
    }
}
