package com.epam.training.snake.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.epam.training.snake.model.Arena;
import com.epam.training.snake.model.Coordinate;
import com.epam.training.snake.model.Food;
import com.epam.training.snake.model.Snake;

public class ArenaView extends JPanel {

    public static final int PADDING = 10;
    public static final int POINT_SIZE = 10;
    private static final long serialVersionUID = 1L;
    private static final Color[] AVAILABLE_COLORS = {Color.BLUE, Color.MAGENTA, Color.YELLOW};
    private static final int FONT_SIZE = 20;
    private static final int LINE_X_POSITION = 30;
    private static final int LINE1_Y_POSITION = 30;
    private static final int LINE2_Y_POSITION = 20;

    private final Arena arena;
    private final List<SnakeView> snakeViews = new ArrayList<>();
    private final FoodDrawer foodDrawer = new FoodDrawer();

    public ArenaView(Arena arena) {
        this.arena = arena;
        generateSnakeViews();
    }

    private void generateSnakeViews() {
        int colorIndex = 0;
        for (Snake snake : arena.getSnakes()) {
            snakeViews.add(new SnakeView(snake, AVAILABLE_COLORS[colorIndex]));
            colorIndex = (colorIndex + 1) % AVAILABLE_COLORS.length;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setFont(new Font("TimesRoman", Font.PLAIN, FONT_SIZE));
        Coordinate maxCoordinate = arena.getMaxCoordinate();
        g.setColor(Color.BLACK);
        g.drawRect(PADDING, PADDING, POINT_SIZE * (maxCoordinate.getX() + 1), POINT_SIZE * (maxCoordinate.getY() + 1));
        int i = 0;
        for (SnakeView snake : snakeViews) {
            snake.draw(g);
            g.drawString(snake.getSnake().getName() + ": "
                    + snake.getSnake().getBodyItems().size(), LINE_X_POSITION, LINE1_Y_POSITION + LINE2_Y_POSITION * i++);
        }
        for (Food food : arena.getFood()) {
            foodDrawer.draw(food, g);
        }
        repaint();
    }

}
