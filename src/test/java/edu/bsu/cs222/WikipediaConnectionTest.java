package edu.bsu.cs222;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
public class WikipediaConnectionTest {
    @Test
    public void testCallingConnectToWikipedia() {
        WikipediaConnection wikiConnection = new WikipediaConnection();
        try {
            InputStream inputStream = wikiConnection.callingConnectToWikipedia();
            assertNotNull(inputStream);
        } catch (IOException e) {
            fail("IOException should not be thrown");
        }
    }

    @Test
    public void testConnectionToWikipedia() {
        WikipediaConnection wikipediaConnection = new WikipediaConnection();
        try {
            URLConnection connection = wikiConnection.connectToWikipedia();
            assertNotNull(connection);
        } catch (IOException e) {
            fail("IOException should not be thrown");
        }
    }

    @Test
    public void testTurningInputToUrl() {
        WikipediaConnection wikiConnection = new WikipediaConneciton();
        wikiConnection.setUserInput("Java Programming");

        String expectedURL = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=Java+Programming&rvprop=timestamp | user&rvlimit=13&redirects";
        String actualURL = wikiConnection.turningInputToURL();

        assertEquals(expectedURL, actualURL);
    }
}
