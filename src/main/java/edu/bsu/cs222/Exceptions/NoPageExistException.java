package edu.bsu.cs222.Exceptions;

public class NoPageExistException extends Exception {
    public NoPageExistException() {
        super("Error, No Page Found!");
    }
}

