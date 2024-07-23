package examples.snake;

import java.awt.Font;
import java.util.ArrayList;
import javax.swing.Timer;
import nootovich.nglib.*;

public class Snake {

    private static final float TICK_DURATION  = 0.1f;
    private static final float FRAME_DURATION = 0.02f;

    public static final int cellAmount = 20;
    public static final int cellSize   = 40;
    public static       int w          = cellSize * cellAmount;
    public static       int h          = cellSize * cellAmount;

    public static int score = 0;

    private static NGWindow window;

    public static final ArrayList<SnakePart> snake = new ArrayList<>();

    public enum DIRECTION {UP, RIGHT, DOWN, LEFT}

    public static DIRECTION queuedDirection = DIRECTION.UP;

    public static NGVec2i foodPosition = new NGVec2i();

    private static void update() {
        SnakePart head = snake.getLast();

        int nx = head.pos.x;
        int ny = head.pos.y;
        switch (head.dir) {
            case UP -> ny = mod(head.pos.y - 1, h / cellSize);
            case RIGHT -> nx = mod(head.pos.x + 1, w / cellSize);
            case DOWN -> ny = mod(head.pos.y + 1, h / cellSize);
            case LEFT -> nx = mod(head.pos.x - 1, w / cellSize);
            default -> NGUtils.error("Snake has entered the 4-th dimension.");
        }

        if (nx == foodPosition.x && ny == foodPosition.y) eat();
        else snake.removeFirst();

        for (SnakePart part: snake) {
            if (part.pos.x == nx && part.pos.y == ny) {
                System.out.println("u ded");
                window.shouldClose = true;
            }
        }

        snake.add(new SnakePart(nx, ny, queuedDirection));
        for (SnakePart part: snake) part.nextAnim();
    }

    public static void eat() {
        foodPosition = new NGVec2i(getRandomPos(), getRandomPos());
        score++;
    }

    public static void resize(int nw, int nh) {
        w = nw;
        h = nh;
        window.g.resize(w, h);
    }

    public static void main(String[] args) {
        SnakeRenderer renderer = new SnakeRenderer();
        renderer.defaultFont = new Font(Font.MONOSPACED, Font.BOLD, 64);

        window = new NGWindow(w, h, renderer);
        window.setKeyHandler(new SnakeKeyHandler());
        window.setMouseHandler(new SnakeMouseHandler());
        window.setResizeHandler(new SnakeWindowHandler());

        snake.add(new SnakePart(cellAmount / 2, cellAmount / 2 + 6, DIRECTION.UP));
        snake.add(new SnakePart(cellAmount / 2, cellAmount / 2 + 5, DIRECTION.UP));
        snake.add(new SnakePart(cellAmount / 2, cellAmount / 2 + 4, DIRECTION.UP));
        foodPosition = new NGVec2i(getRandomPos(), getRandomPos());

        new Timer((int) (TICK_DURATION * 1000), _ -> update()).start();
        new Timer((int) (FRAME_DURATION * 1000), _ -> window.redraw()).start();
    }

    private static int getRandomPos() {
        return (int) (Math.random() * (cellAmount));
    }

    public static int mod(int n, int m) {
        return ((n % m) + m) % m;
    }

    public static class SnakePart {
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
                case UP -> pos.add(0, -1).scale(Snake.cellSize);
                case RIGHT -> pos.add(1, 0).scale(Snake.cellSize);
                case DOWN -> pos.add(0, 1).scale(Snake.cellSize);
                case LEFT -> pos.add(-1, 0).scale(Snake.cellSize);
            };
            anim = new NGAnimation(start.toFloat(), end.toFloat(), Snake.TICK_DURATION);
        }
    }
}
