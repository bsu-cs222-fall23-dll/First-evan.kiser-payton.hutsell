package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        GetUserInput gettingUserInput = new GetUserInput();
        WikipediaConnection connector = new WikipediaConnection(gettingUserInput.getUserInput());
        WikipediaRevisionParser parser = new WikipediaRevisionParser();
        RevisionPrinter printer = new RevisionPrinter();
            try {
                InputStream wikipediaData = connector.callingConnectToWikipedia();
                JSONArray listAllRevisions = parser.parse(wikipediaData);
                System.out.println("\nList of All Revisions: Timestamp - User");
                printer.printListAllRevisions(listAllRevisions);
            } catch (IOException e) {
                System.err.println("Runtime Error: " + e.getMessage());
            }
    }
}
