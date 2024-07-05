import nootovich.nglib.*;

import javax.swing.*;
import java.awt.*;
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

    public static class SnakePart {
        // TODO: x,y -> NGVec2i
        public int         x;
        public int         y;
        public DIRECTION   dir;
        public NGAnimation anim;

        public SnakePart(int x, int y, DIRECTION dir, NGAnimation anim) {
            this.x    = x;
            this.y    = y;
            this.dir  = dir;
            this.anim = anim;
        }

        public SnakePart(int x, int y, DIRECTION dir) {
            this(x, y, dir, null);
        }
    }

    public static void update() {

        SnakePart head = snake.getLast();
        if (head.x == foodPosition[0] && head.y == foodPosition[1])
            foodPosition = new Integer[]{getRandomPos(), getRandomPos()};
        else snake.removeFirst();

        for (int i = 0; i < snake.size(); i++) {
            SnakePart curPart = snake.get(i);
            for (int j = i + 1; j < snake.size(); j++) {
                SnakePart otherPart = snake.get(j);
                if (curPart.x == otherPart.x && curPart.y == otherPart.y) {
                    System.out.println("dead");
                    // TODO: window.shouldClose = true;
                    System.exit(0);
                }
            }
        }

        switch (head.dir) {
            case UP -> {
                int         hx   = head.x * cellSize;
                int         hy   = head.y * cellSize;
                int         ny   = mod(hy - cellSize, h);
                NGAnimation anim = new NGAnimation(hx, hy, hx, ny, TICK_DURATION);
                snake.add(new SnakePart(head.x, ny / cellSize, DIRECTION.UP, anim));
            }
            case RIGHT -> {
                int         hx   = head.x * cellSize;
                int         hy   = head.y * cellSize;
                int         nx   = mod(hx + cellSize, w);
                NGAnimation anim = new NGAnimation(hx, hy, nx, hy, TICK_DURATION);
                snake.add(new SnakePart(nx / cellSize, head.y, DIRECTION.RIGHT, anim));
            }
            case DOWN -> {
                int         hx   = head.x * cellSize;
                int         hy   = head.y * cellSize;
                int         ny   = mod(hy + cellSize, h);
                NGAnimation anim = new NGAnimation(hx, hy, hx, ny, TICK_DURATION);
                snake.add(new SnakePart(head.x, ny / cellSize, DIRECTION.DOWN, anim));
            }
            case LEFT -> {
                int         hx   = head.x * cellSize;
                int         hy   = head.y * cellSize;
                int         nx   = mod(hx - cellSize, w);
                NGAnimation anim = new NGAnimation(hx, hy, nx, hy, TICK_DURATION);
                snake.add(new SnakePart(nx / cellSize, head.y, DIRECTION.LEFT, anim));
            }
        }
    }

    private static final class KeyboardHandler extends NGKeyboardHandler {

        @Override
        public void onKeyDn(int key, char chr) {
            SnakePart head = snake.getLast();
            switch (key) {
                case NGKeys.W -> {
                    if (head.dir == DIRECTION.DOWN) return;
                    head.dir = DIRECTION.UP;
                }
                case NGKeys.A -> {
                    if (head.dir == DIRECTION.RIGHT) return;
                    head.dir = DIRECTION.LEFT;
                }
                case NGKeys.S -> {
                    if (head.dir == DIRECTION.UP) return;
                    head.dir = DIRECTION.DOWN;
                }
                case NGKeys.D -> {
                    if (head.dir == DIRECTION.LEFT) return;
                    head.dir = DIRECTION.RIGHT;
                }
            }
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

        // TODO: Parity
        window.renderer = new Renderer();
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