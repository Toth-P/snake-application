package com.epam.training.snake.controller;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.Timer;

import com.epam.training.snake.model.Arena;
import com.epam.training.snake.model.ModifiableArena;
import com.epam.training.snake.view.ArenaView;

public class SnakeController {

    private static final String TITLE = "Snake";
    private static final int DELAY_BETWEEN_STEPS = 10;
    private static final Dimension FRAME_SIZE = new Dimension(550, 570);

    private final JFrame frame = new JFrame(TITLE);
    private final ModifiableArena arena;
    private ArenaView arenaView;
    private final Timer timer = new Timer(DELAY_BETWEEN_STEPS, new TimerAction(this));

    public SnakeController(ModifiableArena arena) {
        this.arena = arena;
        initView(arena);
        frame.setVisible(true);
    }

    private void initView(Arena arena) {
        this.arenaView = new ArenaView(arena);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(arenaView);
        frame.setSize(FRAME_SIZE);
        frame.setLocationRelativeTo(null);
    }

    public void start() {
        timer.start();
    }

    public void step() {
        arena.move();
        arenaView.repaint();
    }

    public void stop() {
        timer.stop();
        arenaView.repaint();
    }

}
