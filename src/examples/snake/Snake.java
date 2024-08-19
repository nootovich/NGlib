package examples.snake;

import java.util.ArrayList;
import nootovich.nglib.*;

import static examples.snake.Main.*;
import static examples.snake.SnakeRenderer.highlightFood;
import static nootovich.nglib.NGSprite.NGSpriteType.CIRCLE_CENTERED;
import static nootovich.nglib.NGUtils.mod;

public class Snake extends NGMain {

    public void main() {
        setTickRate(10);
        setFrameRate(80);
        createWindow(w, h, new SnakeRenderer());

        ArrayList<NGSprite> eyes = new ArrayList<>();
        eyes.add(new NGSprite(new NGVec2i(EYE_LEFT, EYE_LEFT), EYE_RADIUS, COLOR_SNAKE_EYE, CIRCLE_CENTERED));
        eyes.add(new NGSprite(new NGVec2i(EYE_RIGHT, EYE_LEFT), EYE_RADIUS, COLOR_SNAKE_EYE, CIRCLE_CENTERED));
        eyes.add(new NGSprite(new NGVec2i(EYE_LEFT, EYE_RIGHT), EYE_RADIUS, COLOR_SNAKE_EYE, CIRCLE_CENTERED));
        eyes.add(new NGSprite(new NGVec2i(EYE_RIGHT, EYE_RIGHT), EYE_RADIUS, COLOR_SNAKE_EYE, CIRCLE_CENTERED));
        snake.add(new SnakePart(new NGVec2i(CELL_AMOUNT / 2).add(0, 6).scale(CELL_SIZE), DIRECTION.UP));
        snake.add(new SnakePart(new NGVec2i(CELL_AMOUNT / 2).add(0, 5).scale(CELL_SIZE), DIRECTION.UP));
        snake.add(new SnakePart(new NGVec2i(CELL_AMOUNT / 2).add(0, 4).scale(CELL_SIZE), DIRECTION.UP, eyes));

        start();
    }

    @Override
    public void update() {
        SnakePart head = snake.getLast();

        int nx = head.pos.x();
        int ny = head.pos.y();
        switch (head.dir) {
            case UP -> ny = mod(head.pos.y() - CELL_SIZE, h);
            case RIGHT -> nx = mod(head.pos.x() + CELL_SIZE, w);
            case DOWN -> ny = mod(head.pos.y() + CELL_SIZE, h);
            case LEFT -> nx = mod(head.pos.x() - CELL_SIZE, w);
            default -> NGUtils.error("Snake has entered the 4-th dimension.");
        }

        NGVec2i newPos = new NGVec2i(nx, ny).snap(CELL_SIZE);

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
        if (pos.snap(CELL_SIZE).equals(food.pos)) eat();
        highlightFood = false;
    }

    @Override
    public void whileRMBHeld(NGVec2i pos) {
        food.pos      = pos.snap(CELL_SIZE);
        highlightFood = true;
    }

    @Override
    public void onMouseMoved(NGVec2i pos) {
        highlightFood = (pos.snap(CELL_SIZE).equals(food.pos));
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
        return new NGVec2f((float) Math.random(), (float) Math.random()).scale(CELL_AMOUNT).toInt().scale(CELL_SIZE);
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
            super(pos, new NGVec2i(CELL_SIZE), COLOR_SNAKE, COLOR_SNAKE_BORDER);
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
                case UP -> pos.add(0, -CELL_SIZE);
                case RIGHT -> pos.add(CELL_SIZE, 0);
                case DOWN -> pos.add(0, CELL_SIZE);
                case LEFT -> pos.add(-CELL_SIZE, 0);
            };
            anims.add(new NGAnimation(start.toFloat(), end.toFloat(), TICK_DURATION));
        }
    }
}
