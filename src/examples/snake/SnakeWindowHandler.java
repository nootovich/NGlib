package examples.snake;

import nootovich.nglib.NGWindowHandler;

public class SnakeWindowHandler extends NGWindowHandler {

    @Override
    public void onResize(int nw, int nh) {
        Snake.resize(nw, nh);
    }
}
