import nootovich.nglib.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Snake {

    private static final float TICK_DURATION  = 0.1f;
    private static final float FRAME_DURATION = 0.02f;

    private static final int cellAmount = 20;
    private static final int cellSize   = 40;
    private static       int w          = cellSize * cellAmount;
    private static       int h          = cellSize * cellAmount;

    private enum DIRECTION {UP, RIGHT, DOWN, LEFT}

    private static final ArrayList<SnakePart> snake = new ArrayList<>();

    private static Integer[] foodPosition = new Integer[2];

    private static final NGWindow window = new NGWindow(w, h);

    private static class SnakePart {
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

    private static final class Renderer implements NGRenderer {

        private long prevTime = System.currentTimeMillis();

        @Override
        public void render(NGGraphics g) {
            long  curTime = System.currentTimeMillis();
            float dt      = (curTime - prevTime) / 1000f;
            prevTime = curTime;

            g.drawRect(0, 0, w, h, new Color(0x656795));

            g.drawRect(foodPosition[0] * cellSize, foodPosition[1] * cellSize, cellSize, cellSize, Color.RED);
            g.drawRectBorder(foodPosition[0] * cellSize, foodPosition[1] * cellSize, cellSize, cellSize, Color.WHITE);

            // TODO: this is jank and i can't figure out what is even happening, but for now it's good enough
            //  NOTE TO MY FUTURE SELF: just delete this garbage and do it properly
            for (int i = 0; i < snake.size()-1; i++) {
                SnakePart curPart = snake.get(i);
                if (curPart.anim != null) {
                    if (!curPart.anim.update(dt)) {
                        float progressBleed = curPart.anim.progress - curPart.anim.duration;
                        curPart.anim = switch (snake.get(i + 1).dir) {
                            case UP -> {
                                int hx = snake.get(i + 1).x * cellSize;
                                int hy = snake.get(i + 1).y * cellSize;
                                int ny = mod(hy - cellSize, h);
                                yield new NGAnimation(hx, hy, hx, ny, TICK_DURATION);
                            }
                            case RIGHT -> {
                                int hx = snake.get(i + 1).x * cellSize;
                                int hy = snake.get(i + 1).y * cellSize;
                                int nx = mod(hx + cellSize, w);
                                yield new NGAnimation(hx, hy, nx, hy, TICK_DURATION);
                            }
                            case DOWN -> {
                                int hx = snake.get(i + 1).x * cellSize;
                                int hy = snake.get(i + 1).y * cellSize;
                                int ny = mod(hy + cellSize, h);
                                yield new NGAnimation(hx, hy, hx, ny, TICK_DURATION);
                            }
                            case LEFT -> {
                                int hx = snake.get(i + 1).x * cellSize;
                                int hy = snake.get(i + 1).y * cellSize;
                                int nx = mod(hx - cellSize, w);
                                yield new NGAnimation(hx, hy, nx, hy, TICK_DURATION);
                            }
                        };
                        curPart.anim.update(progressBleed);
                    }
                    g.drawRect(curPart.anim.state, cellSize, Color.WHITE);
                    g.drawRectBorder(curPart.anim.state, cellSize, Color.BLACK);
                } else {
                    g.drawRect(curPart.x * cellSize, curPart.y * cellSize, cellSize, Color.WHITE);
                    g.drawRectBorder(curPart.x * cellSize, curPart.y * cellSize, cellSize, Color.BLACK);
                }
            }

            SnakePart head     = snake.getLast();
            float[][] eyeCords = new float[][]{{0.15f, 0.15f}, {0.60f, 0.15f}, {0.60f, 0.60f}, {0.15f, 0.60f}};
            int       snakeDir = head.dir.ordinal();
            int       otherDir = (snakeDir + 1) % 4;
            int       snakeX1  = (int) ((head.x + eyeCords[snakeDir][0]) * cellSize);
            int       snakeY1  = (int) ((head.y + eyeCords[snakeDir][1]) * cellSize);
            int       snakeX2  = (int) ((head.x + eyeCords[otherDir][0]) * cellSize);
            int       snakeY2  = (int) ((head.y + eyeCords[otherDir][1]) * cellSize);
            int       radius   = (int) (cellSize * 0.25f);

//            g.drawCircle(snakeX1, snakeY1, radius, Color.BLACK);
//            g.drawCircle(snakeX2, snakeY2, radius, Color.BLACK);
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