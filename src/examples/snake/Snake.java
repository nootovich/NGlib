package examples.snake;

import nootovich.nglib.NGAnimation;
import nootovich.nglib.NGUtils;
import nootovich.nglib.NGVec2i;
import nootovich.nglib.NGWindow;

import javax.swing.*;
import java.util.ArrayList;

public class Snake {

    private static final float TICK_DURATION  = 0.1f;
    private static final float FRAME_DURATION = 0.02f;

    protected static final int cellAmount = 20;
    protected static final int cellSize   = 40;
    protected static       int w          = cellSize * cellAmount;
    protected static       int h          = cellSize * cellAmount;

    private static final NGWindow window = new NGWindow(w, h, new SnakeRenderer());

    protected static final ArrayList<SnakePart> snake = new ArrayList<>();

    protected enum DIRECTION {UP, RIGHT, DOWN, LEFT}

    protected static DIRECTION queuedDirection = DIRECTION.UP;

    protected static NGVec2i foodPosition = new NGVec2i();

    private static void update() {
        SnakePart head = snake.getLast();

        int nx = head.pos.x;
        int ny = head.pos.y;
        switch (head.dir) {
            case UP    -> ny = mod(head.pos.y - 1, h / cellSize);
            case RIGHT -> nx = mod(head.pos.x + 1, w / cellSize);
            case DOWN  -> ny = mod(head.pos.y + 1, h / cellSize);
            case LEFT  -> nx = mod(head.pos.x - 1, w / cellSize);
            default -> NGUtils.error("Snake has entered the 4-th dimension.");
        }

        if (nx == foodPosition.x && ny == foodPosition.y)
            foodPosition = new NGVec2i(getRandomPos(), getRandomPos());
        else snake.removeFirst();

        for (SnakePart part : snake) {
            if (part.pos.x == nx && part.pos.y == ny) {
                System.out.println("u ded");
                window.shouldClose = true;
            }
        }

        snake.add(new SnakePart(nx, ny, queuedDirection));
        for (SnakePart part : snake) part.nextAnim();
    }

    public static void main(String[] args) {
        snake.add(new SnakePart(cellAmount / 2, cellAmount / 2 + 6, DIRECTION.UP));
        snake.add(new SnakePart(cellAmount / 2, cellAmount / 2 + 5, DIRECTION.UP));
        snake.add(new SnakePart(cellAmount / 2, cellAmount / 2 + 4, DIRECTION.UP));
        foodPosition = new NGVec2i(getRandomPos(), getRandomPos());

        window.setKeyHandler(new SnakeKeyHandler());
        window.setResizeHandler(new SnakeResizeHandler());

        Timer updateTimer = new Timer((int) (TICK_DURATION * 1000), _ -> update());
        Timer redrawTimer = new Timer((int) (FRAME_DURATION * 1000), _ -> window.redraw());
        updateTimer.start();
        redrawTimer.start();
    }

    private static int getRandomPos() {
        return (int) (Math.random() * (cellAmount));
    }

    public static int mod(int n, int m) {
        return ((n % m) + m) % m;
    }

    protected static class SnakePart {
        public NGVec2i     pos;
        public DIRECTION   dir;
        public NGAnimation anim;

        public SnakePart(int x, int y, DIRECTION dir, NGAnimation anim) {
            this.pos = new NGVec2i(x, y);
            this.dir = dir;
            if (anim == null) nextAnim();
            else this.anim = anim;
        }

        public SnakePart(int x, int y, DIRECTION dir) {
            this(x, y, dir, null);
        }

        public void nextAnim() {
            NGVec2i start = pos.scale(Snake.cellSize);
            NGVec2i end = switch (dir) {
                case UP    -> pos.add( 0, -1).scale(Snake.cellSize);
                case RIGHT -> pos.add( 1,  0).scale(Snake.cellSize);
                case DOWN  -> pos.add( 0,  1).scale(Snake.cellSize);
                case LEFT  -> pos.add(-1,  0).scale(Snake.cellSize);
            };
            anim = new NGAnimation(start.toFloat(), end.toFloat(), Snake.TICK_DURATION);
        }
    }

}