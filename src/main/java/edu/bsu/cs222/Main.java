package edu.bsu.cs222;

import edu.bsu.cs222.Model.*;

import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws Exception {
        GetUserInput gettingUserInput = new GetUserInput();
        WikipediaConnection connector = new WikipediaConnection(gettingUserInput.getUserInput());
        WikipediaRevisionParser parser = new WikipediaRevisionParser();
            try {
                InputStream wikipediaData = connector.callingConnectToWikipedia();
                String finalStringOfRevisions = parser.parse(wikipediaData);
                System.out.println(finalStringOfRevisions);
            } catch (Exception e) {
                System.err.println("Runtime Error: " + e.getMessage());
                System.exit(0);
            }
    }
}
