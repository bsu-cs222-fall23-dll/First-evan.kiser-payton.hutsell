package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException{
        GetUserInput gettingUserInput = new GetUserInput();
        WikipediaConnection connector = new WikipediaConnection(gettingUserInput.getUserInput());
        WikipediaRevisionParser parser = new WikipediaRevisionParser();
            try {
                InputStream wikipediaData = connector.callingConnectToWikipedia();
                JSONArray listAllRevisions = parser.parse(wikipediaData);
                for(int i=0; i< listAllRevisions.size(); i++) {
                    System.out.println(listAllRevisions.get(i));
                }

                // Attempting to Format the Data correctly
//                for(int i=0; i< listAllRevisions.size(); i++) {
//                    String revisionUserName = JsonPath.read(listAllRevisions, "$.user");
//                    String revisionTimestamp = JsonPath.read(listAllRevisions, "$.timestamp");
//                    System.out.println(revisionTimestamp + " " + revisionUserName);
//                }
            } catch (IOException e) {
                System.err.println("Runtime Error: " + e.getMessage());
            }

    }

}
