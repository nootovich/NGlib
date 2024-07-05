import nootovich.nglib.NGAnimation;
import nootovich.nglib.NGGraphics;
import nootovich.nglib.NGRenderer;

import java.awt.*;

public class SnakeRenderer extends NGRenderer {

    public long prevTime = System.currentTimeMillis();

    @Override
    public void render(NGGraphics g) {
        long  curTime = System.currentTimeMillis();
        float dt      = (curTime - prevTime) / 1000f;
        prevTime = curTime;

        g.drawRect(0, 0, Snake.w, Snake.h, new Color(0x656795));

        g.drawRect(Snake.foodPosition[0] * Snake.cellSize, Snake.foodPosition[1] * Snake.cellSize, Snake.cellSize, Snake.cellSize, Color.RED);
        g.drawRectBorder(Snake.foodPosition[0] * Snake.cellSize, Snake.foodPosition[1] * Snake.cellSize, Snake.cellSize, Snake.cellSize, Color.DARK_GRAY);

        // TODO: this is jank and i can't figure out what is even happening, but for now it's good enough
        //  NOTE TO MY FUTURE SELF: just delete this garbage and do it properly
        for (int i = 0; i < Snake.snake.size() - 1; i++) {
            Snake.SnakePart curPart = Snake.snake.get(i);
            if (curPart.anim != null) {
                if (!curPart.anim.update(dt)) {
                    float           progressBleed = curPart.anim.progress - curPart.anim.duration;
                    Snake.DIRECTION dir           = Snake.snake.get(i + 1).dir;
                    int             hx            = Snake.snake.get(i + 1).x * Snake.cellSize;
                    int             hy            = Snake.snake.get(i + 1).y * Snake.cellSize;
                    int             nx            = hx;
                    int             ny            = hy;
                    switch (dir.ordinal()) {
                        case 0 -> ny = Snake.mod(hy - Snake.cellSize, Snake.h);
                        case 1 -> nx = Snake.mod(hx + Snake.cellSize, Snake.w);
                        case 2 -> ny = Snake.mod(hy + Snake.cellSize, Snake.h);
                        case 3 -> nx = Snake.mod(hx - Snake.cellSize, Snake.w);
                    }
                    curPart.anim = new NGAnimation(hx, hy, nx, ny, Snake.TICK_DURATION);
                    curPart.anim.update(progressBleed);
                }
                g.drawRect(curPart.anim.state, Snake.cellSize, Color.WHITE);
                g.drawRectBorder(curPart.anim.state, Snake.cellSize, Color.BLACK);
            } else {
                g.drawRect(curPart.x * Snake.cellSize, curPart.y * Snake.cellSize, Snake.cellSize, Color.WHITE);
                g.drawRectBorder(curPart.x * Snake.cellSize, curPart.y * Snake.cellSize, Snake.cellSize, Color.BLACK);
            }
        }

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
