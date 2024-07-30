package examples.snake;

import examples.snake.Snake.SnakePart;
import java.util.ArrayList;
import nootovich.nglib.NGSprite;
import nootovich.nglib.NGVec2i;

import static examples.snake.Snake.getRandomPos;
import static examples.snake.SnakeRenderer.COLOR_FOOD;
import static examples.snake.SnakeRenderer.COLOR_FOOD_BORDER;

// TODO: I don't like this workaround inorder to escape static context.
//  Would be nice to get rid of this at least by supporting inner classes hot-reloading.
public class Main {

    public static final int cellAmount = 20;
    public static final int cellSize   = 40;

    public static int w = cellSize * cellAmount;
    public static int h = cellSize * cellAmount;

    public static int score = 0;

    public static ArrayList<SnakePart> snake = new ArrayList<>();

    public static NGSprite food = new NGSprite(getRandomPos(), new NGVec2i(cellSize), COLOR_FOOD, COLOR_FOOD_BORDER);

    public enum DIRECTION {UP, RIGHT, DOWN, LEFT}

    public static DIRECTION queuedDirection = DIRECTION.UP;

    public static void main(String[] args) {
        new Snake().main();
    }
}
