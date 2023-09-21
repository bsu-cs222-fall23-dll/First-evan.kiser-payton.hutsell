package edu.bsu.cs222;

import java.io.IOException;
import java.nio.charset.Charset;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.io.InputStream;

public class WikipediaRevisionReader extends GetUserInput {

    public static void main(String[] args) {
        WikipediaRevisionReader revisionReader = new WikipediaRevisionReader();
        // String userSearch = getUserInput();
        try {
            String timestamp = revisionReader.getLatestRevisionOf(userSearch);
            System.out.println(timestamp);
        } catch (IOException ioException) {
            System.err.println("Network connection problem: " + ioException.getMessage());
        }
    }

    private String getLatestRevisionOf(String articleTitle) throws IOException {
        String urlString = String.format("https://en.wikipedia.org/wiki/Special:ApiSandbox#action=query&format=json&list=allrevisions%7Csearch&formatversion=2&arvprop=timestamp%7Cuser&srsearch=Socks", articleTitle);
        String encodeUrlString = URLEncoder.encode(urlString, Charset.defaultCharset());
        try {
            URL url = new URL(encodeUrlString);
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent", "WikipediaRevisionReader/0.1 (https://youtube.com/paulgestwicki; pvgestwicki@bsu.edu)");
            InputStream inputStream = connection.getInputStream();
            WikipediaRevisionParser parser = new WikipediaRevisionParser();
            String timestamp = parser.parse(inputStream);
            return timestamp;
        } catch (MalformedURLException malformedURLException) {
            throw new RuntimeException(malformedURLException);
        }
    }
}