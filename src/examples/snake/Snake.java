package examples.snake;

import java.awt.Font;
import java.util.ArrayList;
import nootovich.nglib.*;

import static examples.snake.Main.*;
import static examples.snake.SnakeRenderer.*;
import static nootovich.nglib.NGSprite.NGSpriteType.CIRCLE_CENTERED;
import static nootovich.nglib.NGUtils.mod;

public class Snake extends NGMain {

    public void main() {
        setTickRate(10);
        setFrameRate(80);

        SnakeRenderer renderer = new SnakeRenderer();
        renderer.defaultFont = new Font(Font.MONOSPACED, Font.BOLD, 64);
        window               = new NGWindow(w, h, renderer, this);

        ArrayList<NGSprite> eyes = new ArrayList<>();
        eyes.add(new NGSprite(new NGVec2i(EYE_LEFT, EYE_LEFT), new NGVec2i(EYE_RADIUS), COLOR_SNAKE_EYE, CIRCLE_CENTERED));
        eyes.add(new NGSprite(new NGVec2i(EYE_RIGHT, EYE_LEFT), new NGVec2i(EYE_RADIUS), COLOR_SNAKE_EYE, CIRCLE_CENTERED));
        eyes.add(new NGSprite(new NGVec2i(EYE_LEFT, EYE_RIGHT), new NGVec2i(EYE_RADIUS), COLOR_SNAKE_EYE, CIRCLE_CENTERED));
        eyes.add(new NGSprite(new NGVec2i(EYE_RIGHT, EYE_RIGHT), new NGVec2i(EYE_RADIUS), COLOR_SNAKE_EYE, CIRCLE_CENTERED));
        snake.add(new SnakePart(new NGVec2i(cellAmount / 2).add(0, 6).scale(cellSize), DIRECTION.UP));
        snake.add(new SnakePart(new NGVec2i(cellAmount / 2).add(0, 5).scale(cellSize), DIRECTION.UP));
        snake.add(new SnakePart(new NGVec2i(cellAmount / 2).add(0, 4).scale(cellSize), DIRECTION.UP, eyes));

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

        snake.add(new SnakePart(newPos, queuedDirection, head.children));
        head.children.clear();
        for (SnakePart part: snake) part.nextAnim();
    }

    @Override
    public void onLMBPressed(NGVec2i pos) {
        if (pos.snap(cellSize).equals(food.pos)) eat();
        highlightFood = false;
    }

    @Override
    public void whileRMBHeld(NGVec2i pos) {
        food.pos = pos.snap(cellSize);
    }

    @Override
    public void onMouseMoved(NGVec2i pos) {
        highlightFood = (pos.snap(cellSize).equals(food.pos));
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
            this(pos, dir, new ArrayList<>());
        }

        public SnakePart(NGVec2i pos, DIRECTION dir, ArrayList<NGSprite> eyes) {
            this(pos, dir, eyes, null);
        }

        public SnakePart(NGVec2i pos, DIRECTION dir, ArrayList<NGSprite> eyes, NGAnimation anim) {
            super(pos, new NGVec2i(cellSize), COLOR_SNAKE, COLOR_SNAKE_BORDER);
            this.dir = dir;
            if (anims.isEmpty()) nextAnim();
            else anims.add(anim);
            for (NGSprite eye: eyes) addChild(eye);
        }

        @Override
        public void update(float dt) {
            super.update(dt);
            if (children.size() != 4) return;
            children.get(0).visible = dir == DIRECTION.UP || dir == DIRECTION.LEFT;
            children.get(1).visible = dir == DIRECTION.UP || dir == DIRECTION.RIGHT;
            children.get(2).visible = dir == DIRECTION.DOWN || dir == DIRECTION.LEFT;
            children.get(3).visible = dir == DIRECTION.DOWN || dir == DIRECTION.RIGHT;
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
