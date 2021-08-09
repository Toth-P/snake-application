package com.epam.training.snake.strategy;

import com.epam.training.snake.model.Arena;
import com.epam.training.snake.model.Direction;
import com.epam.training.snake.model.Snake;

/**
 * Snake strategy.
 */
public interface SnakeStrategy {

    /**
     * Next move for the Snake.
     * @param snake moving snake
     * @param arena game area
     * @return direction to move
     */
    Direction nextMove(Snake snake, Arena arena);

}
