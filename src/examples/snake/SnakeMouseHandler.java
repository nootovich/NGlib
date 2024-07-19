package examples.snake;

import nootovich.nglib.NGMouseHandler;
import nootovich.nglib.NGVec2i;

import static examples.snake.Snake.*;

public class SnakeMouseHandler extends NGMouseHandler {

    @Override
    public void onLMBPressed(NGVec2i pos) {
        if (pos.divide(cellSize).equals(foodPosition)) eat();
        SnakeRenderer.highlightFood = false;
    }

    @Override
    public void onMoved(NGVec2i pos) {
        SnakeRenderer.highlightFood = (pos.divide(cellSize).equals(foodPosition));
    }
}
