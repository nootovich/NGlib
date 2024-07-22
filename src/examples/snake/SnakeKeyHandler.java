package examples.snake;

import nootovich.nglib.NGKeyHandler;
import nootovich.nglib.NGKeys;

public class SnakeKeyHandler extends NGKeyHandler {
    @Override
    public void onKeyDn(int key, char chr) {
        Snake.SnakePart head = Snake.snake.getLast();
        Snake.queuedDirection = switch (key) {
            case NGKeys.W, NGKeys.ARROW_UP -> head.dir != Snake.DIRECTION.DOWN ? Snake.DIRECTION.UP : head.dir;
            case NGKeys.A, NGKeys.ARROW_LEFT -> head.dir != Snake.DIRECTION.RIGHT ? Snake.DIRECTION.LEFT : head.dir;
            case NGKeys.S, NGKeys.ARROW_DOWN -> head.dir != Snake.DIRECTION.UP ? Snake.DIRECTION.DOWN : head.dir;
            case NGKeys.D, NGKeys.ARROW_RIGHT -> head.dir != Snake.DIRECTION.LEFT ? Snake.DIRECTION.RIGHT : head.dir;
            default -> head.dir;
        };
    }
}
