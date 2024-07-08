import nootovich.nglib.*;

import javax.swing.*;
import java.util.ArrayList;

public class Snake {

    public static final float TICK_DURATION  = 0.1f;
    public static final float FRAME_DURATION = 0.02f;

    public static final int cellAmount = 20;
    public static final int cellSize   = 40;
    public static       int w          = cellSize * cellAmount;
    public static       int h          = cellSize * cellAmount;

    public enum DIRECTION {UP, RIGHT, DOWN, LEFT}

    public static final ArrayList<SnakePart> snake = new ArrayList<>();

    public static Integer[] foodPosition = new Integer[2];

    private static final NGWindow window = new NGWindow(w, h, new SnakeRenderer());

    private static DIRECTION queuedDirection = DIRECTION.UP;

    public static class SnakePart {
        // TODO: x,y -> NGVec2i
        public int         x;
        public int         y;
        public DIRECTION   dir;
        public NGAnimation anim;

        public SnakePart(int x, int y, DIRECTION dir, NGAnimation anim) {
            this.x   = x;
            this.y   = y;
            this.dir = dir;
            if (anim == null) nextAnim();
            else this.anim = anim;
        }

        public SnakePart(int x, int y, DIRECTION dir) {
            this(x, y, dir, null);
        }

        public void nextAnim() {
            NGVec2f start = new NGVec2f(x, y).scale(Snake.cellSize);
            NGVec2f end = switch (dir) {
                case UP -> new NGVec2f(x, y - 1).scale(Snake.cellSize);
                case RIGHT -> new NGVec2f(x + 1, y).scale(Snake.cellSize);
                case DOWN -> new NGVec2f(x, y + 1).scale(Snake.cellSize);
                case LEFT -> new NGVec2f(x - 1, y).scale(Snake.cellSize);
            };
            anim = new NGAnimation(start, end, Snake.TICK_DURATION);
        }
    }

    public static void update() {

        SnakePart head = snake.getLast();
        if (head.x == foodPosition[0] && head.y == foodPosition[1])
            foodPosition = new Integer[]{getRandomPos(), getRandomPos()};
        else snake.removeFirst();

        for (int i = 0; i < snake.size(); i++) {
            SnakePart part = snake.get(i);
            for (int j = i + 1; j < snake.size(); j++) {
                SnakePart otherPart = snake.get(j);
                if (part.x == otherPart.x && part.y == otherPart.y) {
                    System.out.println("dead");
                    // TODO: window.shouldClose = true;
                    System.exit(0);
                }
            }
        }

        int nx = head.x;
        int ny = head.y;
        switch (head.dir) {
            case UP -> ny = mod(head.y - 1, h / cellSize);
            case RIGHT -> nx = mod(head.x + 1, w / cellSize);
            case DOWN -> ny = mod(head.y + 1, h / cellSize);
            case LEFT -> nx = mod(head.x - 1, w / cellSize);
            default -> NGUtils.error("Snake has entered the 4-th dimension.");
        }
        snake.add(new SnakePart(nx, ny, head.dir));

        if (snake.getLast().dir != queuedDirection) snake.getLast().dir = queuedDirection;
        for (SnakePart part : snake) part.nextAnim();
    }

    private static final class KeyboardHandler extends NGKeyboardHandler {

        @Override
        public void onKeyDn(int key, char chr) {
            SnakePart head = snake.getLast();
            queuedDirection = switch (key) {
                case NGKeys.W -> head.dir != DIRECTION.DOWN ? DIRECTION.UP : head.dir;
                case NGKeys.A -> head.dir != DIRECTION.RIGHT ? DIRECTION.LEFT : head.dir;
                case NGKeys.S -> head.dir != DIRECTION.UP ? DIRECTION.DOWN : head.dir;
                case NGKeys.D -> head.dir != DIRECTION.LEFT ? DIRECTION.RIGHT : head.dir;
                default -> head.dir;
            };
        }
    }

    private static final class ResizeHandler extends NGResizeHandler {

        @Override
        public void onResize(int nw, int nh) {
            w = nw;
            h = nh;
            window.g.resize(w, h);
        }
    }

    public static void main(String[] args) {
        snake.add(new SnakePart(cellAmount / 2, cellAmount / 2 + 6, DIRECTION.UP));
        snake.add(new SnakePart(cellAmount / 2, cellAmount / 2 + 5, DIRECTION.UP));
        snake.add(new SnakePart(cellAmount / 2, cellAmount / 2 + 4, DIRECTION.UP));
        foodPosition = new Integer[]{getRandomPos(), getRandomPos()};

        window.setKeyboardHandler(new KeyboardHandler());
        window.setResizeHandler(new ResizeHandler());

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

}