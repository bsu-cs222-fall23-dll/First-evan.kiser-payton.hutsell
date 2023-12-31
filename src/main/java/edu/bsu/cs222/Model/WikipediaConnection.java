package edu.bsu.cs222.Model;

import edu.bsu.cs222.Exceptions.NoWikiConnectionException;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class WikipediaConnection {
    private String userInput; // Holds the value of the UserInput
    public WikipediaConnection(String gettingUserInput) { // Constructor
        this.userInput = gettingUserInput;
    }

    public InputStream callingConnectToWikipedia() throws Exception {
        URLConnection connection = connectToWikipedia();
        return connection.getInputStream();
    }

    public URLConnection connectToWikipedia() throws NoWikiConnectionException {
        try {
            URL url = new URL(turningInputToURL());
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("Evan-Kiser", "CS222FirstProject/Group G v0.1.0 (evan.kiser@bsu.edu)");
            connection.connect();
            return connection;
        }
        catch (Exception e) {
            throw new NoWikiConnectionException();
        }

    }

    public String turningInputToURL() {
        String userSearch = userInput;
        String articleTitle = URLEncoder.encode(userSearch, Charset.defaultCharset());
        return String.format("https://en.wikipedia.org/w/api.php?action=query" +
                "&format=json" +
                "&prop=revisions" +
                "&titles=" + articleTitle +
                "&rvprop=timestamp|user" +
                "&rvlimit=13" +
                "&redirects");
    }

    // This method is used in a test
    public void setUserInput(String newUserInput) {
        this.userInput=newUserInput;
    }
}
