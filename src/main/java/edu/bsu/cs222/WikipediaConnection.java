package edu.bsu.cs222;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class WikipediaConnection extends GetUserInput {

    public InputStream callingConnectToWikipedia() throws IOException {
        URLConnection connection = connectToWikipedia();
        return connection.getInputStream();
    }

    private URLConnection connectToWikipedia() throws IOException {
        try{
            URL url = new URL(turningInputToURL());
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("Evan-Kiser", "CS222FirstProject/Group G v0.1.0 (evan.kiser@bsu.edu)");
            connection.connect();
            return connection;
        } catch (IOException e) {
            System.err.println("Network Connectivity Problem: " +  e.getMessage());
        }
        return null; // returns null if the programs doesn't run try method
    }

    public String turningInputToURL() {
        String userSearch = getUserInput();   // Returns the article name the user want to look up
        String articleTitle = URLEncoder.encode(userSearch, Charset.defaultCharset());
        return String.format("https://en.wikipedia.org/w/api.php?action=query" +
                "&format=json" +
                "&prop=revisions" +
                "&titles=" + articleTitle +
                "&rvprop=timestamp|user" +
                "&rvlimit=13" +
                "&redirects");
    }

}
