package domain;

import config.Defaults;
import config.Settings;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Stream;

import static domain.Direction.*;

public class Snake {

    private Direction direction;
    private final Deque<Coordinates> snakeSegments;

    public Snake(Direction startDirection) {
        direction = startDirection;
        snakeSegments = new ArrayDeque<>();
        var headCoordinates = Settings.getInstance().getStartCoordinates().orElse(Defaults.DEFAULT_STARTING_COORDINATES);
        snakeSegments.add(headCoordinates);
        var secondSegment = new Coordinates(headCoordinates.row() - direction.getRowIndex(), headCoordinates.column() - direction.getColumnIndex());
        var thirdSegment = new Coordinates(headCoordinates.row() - direction.getRowIndex() * 2, headCoordinates.column() - direction.getColumnIndex() * 2);
        snakeSegments.add(secondSegment);
        snakeSegments.add(thirdSegment);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Coordinates getHead() {
        return snakeSegments.getFirst();
    }

    public Stream<Coordinates> getBody() {
        return snakeSegments.stream().skip(1);
    }

    public Coordinates getNextHeadPosition() {
        Coordinates snakeHead = getHead();
        int nextRow = snakeHead.row() + direction.getRowIndex();
        int nextColumn = snakeHead.column() + direction.getColumnIndex();
        return new Coordinates(nextRow, nextColumn);
    }

    public void growTail() {
        Coordinates snakeTail = snakeSegments.getLast();
        Direction tailDirection = getTailDirection();
        int nextTailRow = snakeTail.row() + tailDirection.getRowIndex();
        int nextTailColumn = snakeTail.column() + tailDirection.getColumnIndex();
        var newTail = new Coordinates(nextTailRow, nextTailColumn);
        snakeSegments.add(newTail);
    }

    private Direction getTailDirection() {
        Coordinates currentTail = snakeSegments.removeLast();
        Coordinates previousTail = snakeSegments.getLast();
        snakeSegments.add(currentTail);
        int row1 = previousTail.row();
        int row2 = currentTail.row();
        int column1 = previousTail.column();
        int column2 = currentTail.column();
        if (row1 == row2 && column2 > column1) return RIGHT;
        if (row1 == row2 && column2 < column1) return LEFT;
        if (column1 == column2 && row1 > row2) return UP;
        if (column1 == column2 && row1 < row2) return DOWN;
        throw new RuntimeException("Invalid tail");
    }

    public void move() {
        snakeSegments.addFirst(getNextHeadPosition());
        snakeSegments.removeLast();
    }
}
