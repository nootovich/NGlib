import nootovich.nglib.*;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class Snake {

    private static final int cellAmount = 20;
    private static final int cellSize   = 40;
    private static       int w          = cellSize * cellAmount;
    private static       int h          = cellSize * cellAmount;

    private static final Stack<Integer[]> snakePositions = new Stack<>();
    private static       int              snakeDirection = 0;

    private static Integer[] foodPosition = new Integer[2];

    private static final NGWindow window = new NGWindow(w, h);

    private static final class Renderer implements NGRenderer {
        @Override
        public void render(NGGraphics g) {
            g.drawRect(0, 0, w, h, new Color(0x656795));

            Integer[] head = snakePositions.getFirst();
            if (head[0].equals(foodPosition[0]) && head[1].equals(foodPosition[1]))
                foodPosition = new Integer[]{getRandomPos(), getRandomPos()};
            else snakePositions.pop();

            g.drawRect(foodPosition[0] * cellSize, foodPosition[1] * cellSize, cellSize, cellSize, Color.RED);
            g.drawRectBorder(foodPosition[0] * cellSize, foodPosition[1] * cellSize, cellSize, cellSize, Color.WHITE);

            for (int i = 0; i < snakePositions.size(); i++) {
                Integer[] cords = snakePositions.get(i);
                for (int j = i + 1; j < snakePositions.size(); j++) {
                    Integer[] otherCords = snakePositions.get(j);
                    if (cords[0].equals(otherCords[0]) && cords[1].equals(otherCords[1])) {
                        System.out.println("dead");
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        // TODO: window.shouldClose = true;
                        System.exit(0);
                    }
                }
                g.drawRect(cords[0] * cellSize, cords[1] * cellSize, cellSize, cellSize, Color.WHITE);
                g.drawRectBorder(cords[0] * cellSize, cords[1] * cellSize, cellSize, cellSize, Color.BLACK);
            }

            int radius = (int) (cellSize * 0.25f);
            switch (snakeDirection) {
                case 0 -> {
                    int x = (int) (head[0] * cellSize + cellSize * 0.15f);
                    int z = (int) (head[0] * cellSize + cellSize * 0.60f);
                    int y = (int) (head[1] * cellSize + cellSize * 0.15f);
                    g.drawCircle(x, y, radius, Color.BLACK);
                    g.drawCircle(z, y, radius, Color.BLACK);
                    snakeAddPos(head[0], mod(head[1] - 1, h / cellSize));
                }
                case 1 -> {
                    int x = (int) (head[0] * cellSize + cellSize * 0.60f);
                    int y = (int) (head[1] * cellSize + cellSize * 0.15f);
                    int z = (int) (head[1] * cellSize + cellSize * 0.60f);
                    g.drawCircle(x, y, radius, Color.BLACK);
                    g.drawCircle(x, z, radius, Color.BLACK);
                    snakeAddPos(mod(head[0] + 1, w / cellSize), head[1]);
                }
                case 2 -> {
                    int x = (int) (head[0] * cellSize + cellSize * 0.15f);
                    int z = (int) (head[0] * cellSize + cellSize * 0.60f);
                    int y = (int) (head[1] * cellSize + cellSize * 0.60f);
                    g.drawCircle(x, y, radius, Color.BLACK);
                    g.drawCircle(z, y, radius, Color.BLACK);
                    snakeAddPos(head[0], mod(head[1] + 1, h / cellSize));
                }
                case 3 -> {
                    int x = (int) (head[0] * cellSize + cellSize * 0.15f);
                    int y = (int) (head[1] * cellSize + cellSize * 0.15f);
                    int z = (int) (head[1] * cellSize + cellSize * 0.60f);
                    g.drawCircle(x, y, radius, Color.BLACK);
                    g.drawCircle(x, z, radius, Color.BLACK);
                    snakeAddPos(mod(head[0] - 1, w / cellSize), head[1]);
                }
            }
        }
    }

    private static final class KeyboardHandler extends NGKeyboardHandler {

        @Override
        public void onKeyDn(int key, char chr) {
            switch (key) {
                case NGKeys.W -> {
                    if (snakeDirection == 2) return;
                    snakeDirection = 0;
                }
                case NGKeys.A -> {
                    if (snakeDirection == 1) return;
                    snakeDirection = 3;
                }
                case NGKeys.S -> {
                    if (snakeDirection == 0) return;
                    snakeDirection = 2;
                }
                case NGKeys.D -> {
                    if (snakeDirection == 3) return;
                    snakeDirection = 1;
                }
            }
        }
    }

    private static final class ResizeHandler extends NGResizeHandler {

        @Override
        public void onResize(int nw, int nh) {
            w = nw;
            h = nh;
            window.g.resize(w, h);
        }
    }

    public static void main(String[] args) {
        snakeAddPos(cellAmount / 2, cellAmount / 2 + 6);
        snakeAddPos(cellAmount / 2, cellAmount / 2 + 5);
        snakeAddPos(cellAmount / 2, cellAmount / 2 + 4);
        foodPosition = new Integer[]{getRandomPos(), getRandomPos()};

        // TODO: Parity
        window.renderer = new Renderer();
        window.setKeyboardHandler(new KeyboardHandler());
        window.setResizeHandler(new ResizeHandler());

        Timer timer = new Timer(100, _ -> window.redraw());
        timer.start();
    }

    public static void snakeAddPos(int x, int y) {
        snakePositions.addFirst(new Integer[]{x, y});
    }

    private static int getRandomPos() {
        return (int) (Math.random() * (cellAmount));
    }

    public static int mod(int n, int m) {
        return ((n % m) + m) % m;
    }

}