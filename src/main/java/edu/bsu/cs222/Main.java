package edu.bsu.cs222;

import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        GetUserInput gettingUserInput = new GetUserInput();
        WikipediaConnection connector = new WikipediaConnection(gettingUserInput.getUserInput());
        WikipediaRevisionParser parser = new WikipediaRevisionParser();
            try {
                InputStream wikipediaData = connector.callingConnectToWikipedia();
                StringBuilder finalStringOfRevisions = parser.parse(wikipediaData);
                System.out.println(finalStringOfRevisions);
            } catch (IOException e) {
                System.err.println("Runtime Error: " + e.getMessage());
            }
    }
}
