package com.epam.training.snake.strategy;

import java.util.List;

import com.epam.training.snake.model.Coordinate;

public class SnakePositionService {
    public boolean isValidMoveThisSnake(List<Coordinate> snake, Coordinate coordinate) {
        boolean result = true;
        for (int i = 0; i < snake.size(); i++) {
            Coordinate snakeHead = snake.get(0);
            if (isValid(snake, coordinate, i, snakeHead)) {
                result = false;
            }
        }
        return result;
    }

    public boolean isValidMoveOtherSnake(List<Coordinate> snake, Coordinate coordinate, List<Coordinate> otherSnake) {
        boolean result = true;
        for (int i = 0; i < otherSnake.size(); i++) {
            Coordinate snakeHead = snake.get(0);
            if (isValid(otherSnake, coordinate, i, snakeHead)) {
                result = false;
            }
        }
        return result;
    }

    private boolean isValid(List<Coordinate> snake, Coordinate coordinate, int i, Coordinate snakeHead) {
        return snakeHead.getX() + coordinate.getX() == snake.get(i).getX() && snakeHead.getY() + coordinate.getY() == snake.get(i).getY();
    }
}
