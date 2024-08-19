package examples.snake;

import examples.snake.Snake.SnakePart;
import java.awt.Color;
import java.util.ArrayList;
import nootovich.nglib.NGSprite;
import nootovich.nglib.NGVec2i;

import static examples.snake.Snake.getRandomPos;

// TODO: I don't like this workaround inorder to escape static context.
//  Would be nice to get rid of this at least by supporting inner classes hot-reloading.
public class Main {

    public static final int CELL_AMOUNT = 20;
    public static final int CELL_SIZE   = 40;

    public static final Color COLOR_BG             = new Color(0x141820);
    public static final Color COLOR_SNAKE          = new Color(0xFFFFFF);
    public static final Color COLOR_SNAKE_BORDER   = new Color(0x000000);
    public static final Color COLOR_SNAKE_EYE      = new Color(0x000000);
    public static final Color COLOR_FOOD           = new Color(0x166236);
    public static final Color COLOR_FOOD_HIGHLIGHT = new Color(0x338F54);
    public static final Color COLOR_FOOD_BORDER    = new Color(0xFFFFFF);
    public static final Color COLOR_SCORE          = new Color(0x507DBE);

    public static final int EYE_RADIUS = CELL_SIZE / 4;
    public static final int EYE_LEFT   = (int) (CELL_SIZE * 0.275f);
    public static final int EYE_RIGHT  = (int) (CELL_SIZE * 0.725f);

    public static int score = 0;

    public static ArrayList<SnakePart> snake = new ArrayList<>();

    public static NGSprite food = new NGSprite(getRandomPos(), new NGVec2i(CELL_SIZE), COLOR_FOOD, COLOR_FOOD_BORDER);

    public enum DIRECTION {UP, RIGHT, DOWN, LEFT}

    public static DIRECTION queuedDirection = DIRECTION.UP;

    public static void main(String[] args) {
        new Snake().main();
    }
}
