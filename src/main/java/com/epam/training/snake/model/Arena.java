package com.epam.training.snake.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Arena {

    private static final Coordinate MAX_COORDINATE = new Coordinate(50, 50);
    private static final int MAX_ROUND = 5000;

    protected List<ModifiableSnake> snakes = new ArrayList<>();
    private final List<Food> food = new ArrayList<>();
    private final Coordinate maxCoordinate = MAX_COORDINATE;
    protected int round = 0;

    public Arena() {
        generateNewFood();
    }

    protected void printResultsIfNeeded() {
        if (round == MAX_ROUND) {
            for (Snake snake : snakes) {
                System.out.println(snake.getName() + ": " + snake.length());
            }
        }
    }

    public Coordinate nextCoordinate(Coordinate coordinate, Direction direction) {
        return coordinate.nextCoordinate(direction).truncLimits(maxCoordinate);
    }

    public boolean isOccupied(Coordinate nextCoordinate) {
        return occupies(nextCoordinate, snakes);
    }

    public boolean isFood(Coordinate nextCoordinate) {
        return occupies(nextCoordinate, food);
    }

    private boolean occupies(Coordinate nextCoordinate, List<? extends Member> members) {
        boolean isOccupied = false;
        for (int i = 0; i < members.size() && !isOccupied; i++) {
            if (members.get(i).occupies(nextCoordinate)) {
                isOccupied = true;
            }
        }
        return isOccupied;
    }

    public Coordinate getMaxCoordinate() {
        return maxCoordinate;
    }

    public List<Snake> getSnakes() {
        return new ArrayList<>(snakes);
    }

    public List<Food> getFood() {
        return new ArrayList<>(food);
    }

    protected void generateNewFood() {
        Coordinate coordinate = generateRandomFreeCoordinate();
        food.add(new Food(coordinate));
    }

    protected void removeFoodFromCollection(Coordinate nextCoordinate) {
        Iterator<Food> foodIterator = food.iterator();
        while (foodIterator.hasNext()) {
            Food food = foodIterator.next();
            Coordinate foodCoordinate = food.getCoordinate();
            if (foodCoordinate.equals(nextCoordinate)) {
                foodIterator.remove();
            }
        }
    }

    public Coordinate generateRandomFreeCoordinate() {
        Random random = new Random();
        Coordinate coordinate;
        do {
            int x = random.nextInt(MAX_COORDINATE.getX());
            int y = random.nextInt(MAX_COORDINATE.getY());
            coordinate = new Coordinate(x, y);
        } while (isOccupied(coordinate) && !isFood(coordinate));
        return coordinate;
    }

    @Override
    public String toString() {
        return "Arena [snakes=" + snakes + ", food=" + food + ", maxCoordinate=" + maxCoordinate + "]";
    }

}
