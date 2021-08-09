package com.epam.training.snake.model;

import com.epam.training.snake.strategy.SnakeStrategy;

public class ModifiableSnake extends Snake {

    public ModifiableSnake(ModifiableArena arena, SnakeStrategy strategy, String name) {
        super(arena, strategy, name);
    }

    public void move() {
        Coordinate nextCoordinate = decideNextCoordinate();
        moveTo(nextCoordinate);
    }

}
