package examples.snake;

import java.awt.Font;
import javax.swing.Timer;
import nootovich.nglib.*;

import static examples.snake.Main.*;
import static nootovich.nglib.NGUtils.mod;

public class Snake extends NGMain {

    private static NGWindow window;

    public void main() {
        SnakeRenderer renderer = new SnakeRenderer();
        renderer.defaultFont = new Font(Font.MONOSPACED, Font.BOLD, 64);

        window = new NGWindow(w, h, renderer, this);

        snake.add(new SnakePart(cellAmount / 2, cellAmount / 2 + 6, DIRECTION.UP));
        snake.add(new SnakePart(cellAmount / 2, cellAmount / 2 + 5, DIRECTION.UP));
        snake.add(new SnakePart(cellAmount / 2, cellAmount / 2 + 4, DIRECTION.UP));
        foodPosition = new NGVec2i(getRandomPos(), getRandomPos());

        new Timer((int) (TICK_DURATION * 1000), _ -> update()).start();
        new Timer((int) (FRAME_DURATION * 1000), _ -> window.redraw()).start();
    }

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

    @Override
    public void onLMBPressed(NGVec2i pos) {
        if (pos.divide(cellSize).equals(foodPosition)) eat();
        SnakeRenderer.highlightFood = false;
    }

    @Override
    public void onMouseMoved(NGVec2i pos) {
        SnakeRenderer.highlightFood = (pos.divide(cellSize).equals(foodPosition));
    }

    @Override
    public void onWPress() {
        SnakePart head = snake.getLast();
        queuedDirection = head.dir != DIRECTION.DOWN ? DIRECTION.UP : head.dir;
    }

    @Override
    public void onAPress() {
        SnakePart head = snake.getLast();
        queuedDirection = head.dir != DIRECTION.RIGHT ? DIRECTION.LEFT : head.dir;
    }

    @Override
    public void onSPress() {
        SnakePart head = snake.getLast();
        queuedDirection = head.dir != DIRECTION.UP ? DIRECTION.DOWN : head.dir;
    }

    @Override
    public void onDPress() {
        SnakePart head = snake.getLast();
        queuedDirection = head.dir != DIRECTION.LEFT ? DIRECTION.RIGHT : head.dir;
    }

    @Override
    public void onWindowResize(int nw, int nh) {
        w = nw;
        h = nh;
        window.g.resize(w, h);
    }

    public static void eat() {
        foodPosition = new NGVec2i(getRandomPos(), getRandomPos());
        score++;
    }

    private static int getRandomPos() {
        return (int) (Math.random() * (cellAmount));
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
            NGVec2i start = pos.scale(cellSize);
            NGVec2i end = switch (dir) {
                case UP -> pos.add(0, -1).scale(cellSize);
                case RIGHT -> pos.add(1, 0).scale(cellSize);
                case DOWN -> pos.add(0, 1).scale(cellSize);
                case LEFT -> pos.add(-1, 0).scale(cellSize);
            };
            anim = new NGAnimation(start.toFloat(), end.toFloat(), TICK_DURATION);
        }
    }
}
