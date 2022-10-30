package ru.croc.task7;

public class IllegalMoveException extends Exception {

    final String message;
    public IllegalMoveException(String message) {
        this.message = message;
    }
}
