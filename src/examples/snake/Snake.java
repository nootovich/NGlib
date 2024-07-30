package examples.snake;

import java.awt.Color;
import java.awt.Font;
import nootovich.nglib.*;
import nootovich.nglib.NGSprite.NGSpriteShape;

import static examples.snake.Main.*;
import static examples.snake.SnakeRenderer.*;
import static nootovich.nglib.NGUtils.mod;

public class Snake extends NGMain {

    public void main() {
        setTickRate(10);
        setFrameRate(80);

        SnakeRenderer renderer = new SnakeRenderer();
        renderer.defaultFont = new Font(Font.MONOSPACED, Font.BOLD, 64);
        window               = new NGWindow(w, h, renderer, this);

        snake.add(new SnakePart(new NGVec2i(cellAmount / 2).add(0, 6).scale(cellSize), DIRECTION.UP));
        snake.add(new SnakePart(new NGVec2i(cellAmount / 2).add(0, 5).scale(cellSize), DIRECTION.UP));
        snake.add(new SnakePart(new NGVec2i(cellAmount / 2).add(0, 4).scale(cellSize), DIRECTION.UP));
        SnakePart head = snake.getLast();
        NGSprite  eye  = new NGSprite(head.pos, new NGVec2i(EYE_RADIUS), Color.RED/*COLOR_SNAKE_EYE*/);
        eye.shape = NGSpriteShape.CIRCLE;
        head.children.add(eye);

        start();
    }

    @Override
    public void update() {
        SnakePart head = snake.getLast();

        int nx = head.pos.x;
        int ny = head.pos.y;
        switch (head.dir) {
            case UP -> ny = mod(head.pos.y - cellSize, h);
            case RIGHT -> nx = mod(head.pos.x + cellSize, w);
            case DOWN -> ny = mod(head.pos.y + cellSize, h);
            case LEFT -> nx = mod(head.pos.x - cellSize, w);
            default -> NGUtils.error("Snake has entered the 4-th dimension.");
        }

        NGVec2i newPos = new NGVec2i(nx, ny).snap(cellSize);

        if (newPos.equals(food.pos)) eat();
        else snake.removeFirst();

        for (SnakePart part: snake) {
            if (newPos.equals(part.pos)) {
                System.out.println("u ded");
                window.shouldClose = true;
            }
        }

        head.children.clear();

        SnakePart newHead = new SnakePart(newPos, queuedDirection);
        int       ldir    = queuedDirection.ordinal();
        int       rdir    = (ldir + 1) % 4;

        NGVec2i  leftEyePos = newPos.add((ldir % 3) < 1 ? EYE_LEFT : EYE_RIGHT, ldir < 2 ? EYE_LEFT : EYE_RIGHT);
        NGSprite leftEye    = new NGSprite(leftEyePos, new NGVec2i(EYE_RADIUS), Color.RED/*COLOR_SNAKE_EYE*/);
        leftEye.shape = NGSpriteShape.CIRCLE;
        // leftEye.anims.add(newHead.anims.getLast()); // TODO: figure out the animations
        newHead.children.add(leftEye);

        NGVec2i  rightEyePos = newPos.add((rdir % 3) < 1 ? EYE_LEFT : EYE_RIGHT, rdir < 2 ? EYE_LEFT : EYE_RIGHT);
        NGSprite rightEye    = new NGSprite(rightEyePos, new NGVec2i(EYE_RADIUS), Color.CYAN/*COLOR_SNAKE_EYE*/);
        rightEye.shape = NGSpriteShape.CIRCLE;
        // rightEye.anims.add(newHead.anims.getLast()); // TODO: figure out the animations
        newHead.children.add(rightEye);

        snake.add(newHead);
        for (SnakePart part: snake) part.nextAnim();
    }

    @Override
    public void onLMBPressed(NGVec2i pos) {
        if (pos.snap(cellSize).equals(food.pos)) eat();
        SnakeRenderer.highlightFood = false;
    }

    @Override
    public void whileRMBHeld(NGVec2i pos) {
        food.pos = pos.snap(cellSize);
    }

    @Override
    public void onMouseMoved(NGVec2i pos) {
        SnakeRenderer.highlightFood = (pos.snap(cellSize).equals(food.pos));
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
        food.pos = getRandomPos();
        score++;
    }

    public static NGVec2i getRandomPos() {
        return new NGVec2f((float) Math.random(), (float) Math.random()).scale(cellAmount).toInt().scale(cellSize);
    }

    public static class SnakePart extends NGSprite {
        public DIRECTION dir;

        public SnakePart(NGVec2i pos, DIRECTION dir) {
            this(pos, dir, null);
        }

        public SnakePart(NGVec2i pos, DIRECTION dir, NGAnimation anim) {
            super(pos, new NGVec2i(cellSize), COLOR_SNAKE, COLOR_SNAKE_BORDER);
            this.dir = dir;
            if (anims.isEmpty()) nextAnim();
            else anims.add(anim);
        }

        public void nextAnim() {
            anims.clear();
            NGVec2i start = pos;
            NGVec2i end = switch (dir) {
                case UP -> pos.add(0, -cellSize);
                case RIGHT -> pos.add(cellSize, 0);
                case DOWN -> pos.add(0, cellSize);
                case LEFT -> pos.add(-cellSize, 0);
            };
            anims.add(new NGAnimation(start.toFloat(), end.toFloat(), TICK_DURATION));
        }
    }
}
