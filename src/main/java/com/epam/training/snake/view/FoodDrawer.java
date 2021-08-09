package com.epam.training.snake.view;

import java.awt.Color;
import java.awt.Graphics;

import com.epam.training.snake.model.Coordinate;
import com.epam.training.snake.model.Food;

public class FoodDrawer {

    public void draw(Food food, Graphics graphics) {
        Coordinate coordinate = food.getCoordinate();
        graphics.setColor(Color.RED);
        int x = ArenaView.PADDING + 1 + ArenaView.POINT_SIZE * coordinate.getX();
        int y = ArenaView.PADDING + 1 + ArenaView.POINT_SIZE * coordinate.getY();
        graphics.fillRect(x, y, ArenaView.POINT_SIZE, ArenaView.POINT_SIZE);
    }

}
