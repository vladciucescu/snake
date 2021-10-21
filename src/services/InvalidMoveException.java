package services;

public class InvalidMoveException extends RuntimeException {
    public InvalidMoveException(String s) {
        super(s);
    }
}
