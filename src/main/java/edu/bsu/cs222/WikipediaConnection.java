package edu.bsu.cs222;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class WikipediaConnection {
    public static void main(String[] args) throws IOException {
        URLConnection connection = connectToWikipedia();
        String jsonData = readJsonAsStringFrom(connection);
        printRawJson(jsonData);
    }

    private static URLConnection connectToWikipedia() throws IOException {
        String encodedUrlString = URLEncoder.encode("Socks", Charset.defaultCharset()) ;
        URL url = new URL(encodedUrlString);
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent", "CS222FirstProject/0.1 (dllargent@bsu.edu)");
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
