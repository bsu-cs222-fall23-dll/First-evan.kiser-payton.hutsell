package edu.bsu.cs222;


import java.io.IOException;
import java.util.Scanner;

public class WikipediaRevisionParser {

    public static void main(String[] args) {
        WikipediaRevisionReader revisionReader = new WikipediaRevisionReader();
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        try {
            String timestamp = revisionReader.getLatestRevisionOf(line);
            System.out.println(timestamp);
        catch (IOException ioException) {
            System.err.println("Network connection problem: " + ioException.getMessage());
            }
        }
//        Private String getLatestRevisionOf(String articleTitle) throws IOException {
//            String urlString = String.format("https://en.wikipedia.org")
        }
    }


}
