package edu.bsu.cs222;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class WikipediaConnection extends GetUserInput {
    // Getting User Input
    static String userSearch = getUserInput();   // Returns the article name the user want to look up
    public static void main(String[] args) throws IOException {
        URLConnection connection = connectToWikipedia();
        String jsonData = readJsonAsStringFrom(connection);
        printRawJson(jsonData);
    }

    public String turningInputToURL() {
        String articleTitle = URLEncoder.encode(userSearch, Charset.defaultCharset());
        return String.format("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=" + articleTitle
                + "&rvprop=timestamp|user&rvlimit=13&redirects");
    }

    private static URLConnection connectToWikipedia() throws IOException {
        String encodedUrlString = URLEncoder.encode(userSearch, Charset.defaultCharset()) ;
        URL url = new URL(encodedUrlString);
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent", "CS222FirstProject/Group G (dllargent@bsu.edu)");
        connection.connect();
        return connection;
    }

    private static String readJsonAsStringFrom(URLConnection connection) throws IOException {
        return new String(connection.getInputStream().readAllBytes(), Charset.defaultCharset());
    }

    private static void printRawJson(String jsonData) {
        System.out.println(jsonData);
    }
}
