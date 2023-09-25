package edu.bsu.cs222;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        WikipediaConnection connector = new WikipediaConnection();
        WikipediaRevisionParser parser = new WikipediaRevisionParser();
            try {
                InputStream wikipediaData = connector.callingConnectToWikipedia();
                List<Revision> listAllRevisions = parser.parse(wikipediaData);
                for(Revision revision : listAllRevisions) {
                    System.out.println(revision);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

    }

}
