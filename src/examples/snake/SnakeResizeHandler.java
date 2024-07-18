package examples.snake;

import nootovich.nglib.NGResizeHandler;

public class SnakeResizeHandler extends NGResizeHandler {

    @Override
    public void onResize(int nw, int nh) {
        Snake.w = nw;
        Snake.h = nh;
        window.g.resize(Snake.w, Snake.h);
    }
}