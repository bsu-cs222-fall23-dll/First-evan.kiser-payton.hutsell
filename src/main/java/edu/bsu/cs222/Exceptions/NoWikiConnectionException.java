package edu.bsu.cs222.Exceptions;

public class NoWikiConnectionException extends Exception{
    public NoWikiConnectionException() {
        super("Error, Network Connectivity Problem!");
    }
}
