package com.epam.training.snake.strategy;

import java.util.List;

import com.epam.training.snake.model.Arena;
import com.epam.training.snake.model.Coordinate;
import com.epam.training.snake.model.Direction;
import com.epam.training.snake.model.Snake;


public class SampleStrategy implements SnakeStrategy {

    @Override
    public Direction nextMove(Snake snake, Arena arena) {
        DirectionService directionService = new DirectionService();
        List<Snake> snakes = arena.getSnakes();
        Snake otherSnake = getOtherSnake(snake, snakes);
        Coordinate foodCoordinate = arena.getFood().get(0).getCoordinate();
        List<Coordinate> bodyItems = snake.getBodyItems();
        Coordinate snakeCoordinate = bodyItems.get(0);

        return directionService.getSnakeNextDirection(otherSnake, foodCoordinate, bodyItems, snakeCoordinate);

    }


    private Snake getOtherSnake(Snake snake, List<Snake> snakes) {
        Snake otherSnake = snake;
        for (Snake value : snakes) {
            if (snake != value) {
                otherSnake = value;
            }
        }
        return otherSnake;
    }


}

