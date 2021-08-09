package com.epam.training.snake.view;

import java.awt.Color;
import java.awt.Graphics;

import com.epam.training.snake.model.Coordinate;
import com.epam.training.snake.model.Snake;

public class SnakeView {

    private final Snake snake;
    private final Color color;

    public SnakeView(Snake snake, Color color) {
        this.snake = snake;
        this.color = color;
    }

    public Snake getSnake() {
        return snake;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        boolean first = true;
        for (Coordinate bodyItem : snake.getBodyItems()) {
            int x = ArenaView.PADDING + 1 + ArenaView.POINT_SIZE * bodyItem.getX();
            int y = ArenaView.PADDING + 1 + ArenaView.POINT_SIZE * bodyItem.getY();
            if (first) {
                g.setColor(Color.BLACK);
            } else {
                g.setColor(color);
            }
            g.fillRect(x, y, ArenaView.POINT_SIZE, ArenaView.POINT_SIZE);
            first = false;
        }
    }

}
