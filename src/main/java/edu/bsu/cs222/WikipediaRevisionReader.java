package edu.bsu.cs222;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.io.InputStream;

public class WikipediaRevisionReader {

    public static void main(String[] args) throws IOException {
        URLConnection connection = connectToWikipedia();
        String jsonData = readJsonAsStringFrom(connection);
        printRawJson(jsonData);
    }

    private static URLConnection connectToWikipedia() throws IOException {
        String encodedUrlString = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=" +
                URLEncoder.encode("Zappa", Charset.defaultCharset()) +
                "&rvprop=timestamp|user&rvlimit=4&redirects";
        URL url = new URL(encodedUrlString);
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent",
                "CS222FirstProject/0.1 (dllargent@bsu.edu)");
        connection.connect();
        return connection;
    }

    private static String readJsonAsStringFrom(URLConnection connection) throws IOException {
        return new String(connection.getInputStream().readAllBytes(), Charset.defaultCharset());
    }

    private static void printRawJson(String jsonData) {
        System.out.println(jsonData);
    }
//    public static void main(String[] args) {
//        WikipediaRevisionReader revisionReader = new WikipediaRevisionReader();
//        Scanner scanner = new Scanner(System.in);
//        String line = scanner.nextLine();
//        try {
//            String timestamp = revisionReader.getLatestRevisionOf(line);
//            System.out.println(timestamp);
//        } catch (IOException ioException) {
//            System.err.println("Network connection problem: " + ioException.getMessage());
//        }
//    }
//
//    private String getLatestRevisionOf(String articleTitle) throws IOException {
//        String urlString = String.format("https://en.wikipedia.org/wiki/Special:ApiSandbox#action=query&format=json&list=allrevisions%7Csearch&formatversion=2&arvprop=timestamp%7Cuser&srsearch=Socks", articleTitle);
//        String encodeUrlString = URLEncoder.encode(urlString, Charset.defaultCharset());
//        try {
//            URL url = new URL(encodeUrlString);
//            URLConnection connection = url.openConnection();
//            connection.setRequestProperty("User-Agent", "WikipediaRevisionReader/0.1 (https://youtube.com/paulgestwicki; pvgestwicki@bsu.edu)");
//            InputStream inputStream = connection.getInputStream();
//            WikipediaRevisionParser parser = new WikipediaRevisionParser();
//            String timestamp = parser.parse(inputStream);
//            return timestamp;
//        } catch (MalformedURLException malformedURLException) {
//            throw new RuntimeException(malformedURLException);
//        }
//    }
}