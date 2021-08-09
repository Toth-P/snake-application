package com.epam.training.snake.controller;

import com.epam.training.snake.model.ModifiableArena;
import com.epam.training.snake.model.ModifiableSnake;
import com.epam.training.snake.model.SnakeDeadException;
import com.epam.training.snake.strategy.SampleStrategy;

import java.awt.event.ActionEvent;

public final class Application {

    private Application() {
    }

    public static void main(String[] args) {
        System.setSecurityManager(new SecurityManager());

        ModifiableArena arena = new ModifiableArena();
        ModifiableSnake snake1 = new ModifiableSnake(arena, new SampleStrategy(), "Player #1");
        ModifiableSnake snake2 = new ModifiableSnake(arena, new SampleStrategy(), "Player #2");
        arena.addSnake(snake2);
        arena.addSnake(snake1);
        new SnakeController(arena).start();
    }


}
