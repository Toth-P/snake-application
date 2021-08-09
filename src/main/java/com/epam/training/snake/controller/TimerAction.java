package com.epam.training.snake.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.epam.training.snake.model.SnakeDeadException;

public class TimerAction implements ActionListener {

    private final SnakeController snakeController;
    private boolean stop;

    {
        stop = false;
    }

    public TimerAction(SnakeController snakeController) {
        this.snakeController = snakeController;
    }

    public void actionPerformed(ActionEvent e) {

        try {
            if (!stop) {
                snakeController.step();
            }
        } catch (SnakeDeadException ex) {
            stop = true;
            snakeController.stop();
        }
    }

}
