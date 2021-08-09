package com.epam.training.snake.strategy;

import java.util.List;

import com.epam.training.snake.model.Coordinate;
import com.epam.training.snake.model.Direction;
import com.epam.training.snake.model.Snake;

public class DirectionService {

    private final SnakePositionService snakePositionService = new SnakePositionService();

    public Direction getSnakeNextDirection(Snake otherSnake, Coordinate foodCoordinate, List<Coordinate> bodyItems, Coordinate snakeCoordinate) {
        Direction direction = null;
        Direction xAxisWhenSnakeAndFoodHaveNotEqualXCoordinates = getDirectionWithDifferentXCoordinates(otherSnake, foodCoordinate, bodyItems, snakeCoordinate);
        if (xAxisWhenSnakeAndFoodHaveNotEqualXCoordinates != null) {
            direction = xAxisWhenSnakeAndFoodHaveNotEqualXCoordinates;
        }
        direction = getDirectionIfNull(direction, getDirectionNorthOrSouth(otherSnake, foodCoordinate, bodyItems, snakeCoordinate));
        direction = getDirectionIfNull(direction, getDirectionEastOrWest(otherSnake, foodCoordinate, bodyItems, snakeCoordinate));
        direction = getDirectionIfNull(direction, avoidCollusion(bodyItems));
        if (direction == null) {
            direction = Direction.EAST;
        }
        return direction;
    }

    private Direction getDirectionIfNull(Direction resultDirection, Direction direction) {
        Direction result = resultDirection;
        if (resultDirection == null) {
            if (direction != null) {
                result = direction;
            }
        }
        return result;
    }

    private Direction getDirectionWithDifferentXCoordinates(Snake otherSnake, Coordinate foodCoordinate,
                                                            List<Coordinate> bodyItems, Coordinate snakeCoordinate) {
        Direction result = null;
        if (foodCoordinate.getX() != snakeCoordinate.getX()) {
            result = getDirectionEastOrWest(otherSnake, foodCoordinate, bodyItems, snakeCoordinate);
        }
        return result;
    }

    private Direction getDirectionNorthOrSouth(Snake otherSnake, Coordinate foodCoordinate, List<Coordinate> bodyItems, Coordinate snakeCoordinate) {
        Direction direction = null;
        if (foodCoordinate.getY() < snakeCoordinate.getY()) {
            if (isDirectionValid(otherSnake, bodyItems, new Coordinate(0, -1))) {
                direction = Direction.NORTH;
            }
        }
        if (direction == null) {
            if (isDirectionValid(otherSnake, bodyItems, new Coordinate(0, 1))) {
                direction = Direction.SOUTH;
            }
        }
        return direction;
    }

    private Direction getDirectionEastOrWest(Snake otherSnake, Coordinate foodCoordinate, List<Coordinate> bodyItems, Coordinate snakeCoordinate) {
        Direction direction = null;
        if (foodCoordinate.getX() > snakeCoordinate.getX()) {
            if (isDirectionValid(otherSnake, bodyItems, new Coordinate(1, 0))) {
                direction = Direction.EAST;
            }
        }
        if (direction == null) {
            if (isDirectionValid(otherSnake, bodyItems, new Coordinate(-1, 0))) {
                direction = Direction.WEST;
            }
        }
        return direction;
    }

    private boolean isDirectionValid(Snake otherSnake, List<Coordinate> bodyItems, Coordinate coordinate) {
        boolean result = false;
        if (snakePositionService.isValidMoveThisSnake(bodyItems, coordinate)) {
            result = snakePositionService.isValidMoveOtherSnake(bodyItems, coordinate, otherSnake.getBodyItems());
        }
        return result;
    }

    private Direction avoidCollusion(List<Coordinate> snake) {
        boolean east = isDirectionValid(snake, new Coordinate(1, 0));
        boolean west = isDirectionValid(snake, new Coordinate(-1, 0));
        boolean south = isDirectionValid(snake, new Coordinate(0, 1));
        boolean north = isDirectionValid(snake, new Coordinate(0, -1));

        return getDirection(east, west, south, north);
    }

    private boolean isDirectionValid(List<Coordinate> snake, Coordinate coordinate) {
        boolean result = true;
        for (int i = 0; i < snake.size(); i++) {
            Coordinate snakeHead = snake.get(0);
            if (result) {
                result = isInvalid(snake, coordinate, i, snakeHead);
            }
        }
        return result;
    }

    private Direction getDirection(boolean east, boolean west, boolean south, boolean north) {
        Direction direction = null;
        if (north) {
            direction = Direction.NORTH;
        } else if (east) {
            direction = Direction.EAST;
        } else if (south) {
            direction = Direction.SOUTH;
        } else if (west) {
            direction = Direction.WEST;
        }
        return direction;
    }

    private boolean isInvalid(List<Coordinate> snake, Coordinate coordinate, int i, Coordinate snakeHead) {
        return snakeHead.getX() + coordinate.getX() != snake.get(i).getX() || snakeHead.getY() + coordinate.getY() != snake.get(i).getY();
    }


}
