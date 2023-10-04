package edu.bsu.cs222.Exceptions;

public class NoInputException extends Exception {
    public NoInputException() {
            super("Error, Input is invalid, you did not input anything!");
    }

    @Override
    public  String toString() {
        return ("Error, Input is invalid, you did not input anything!");
    }
}
