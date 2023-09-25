package edu.bsu.cs222;

import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        WikipediaConnection connector = new WikipediaConnection();
        WikipediaRevisionParser parser = new WikipediaRevisionParser();
            try {
                InputStream wikipediaData = connector.callingConnectToWikipedia();
                parser.parse(wikipediaData);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }



    }

}
