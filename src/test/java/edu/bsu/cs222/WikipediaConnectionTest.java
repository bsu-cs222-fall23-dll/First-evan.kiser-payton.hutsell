package edu.bsu.cs222;

import edu.bsu.cs222.Model.WikipediaConnection;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
public class WikipediaConnectionTest {
    WikipediaConnection wikiConnection = new WikipediaConnection("Socks");

    @Test
    public void testCallingConnectToWikipedia() throws Exception {
        try {
            InputStream inputStream = wikiConnection.callingConnectToWikipedia();
            assertNotNull(inputStream);
        } catch (IOException e) {
            System.err.println("IOException should not be thrown");
        }
    }

    @Test
    public void testConnectionToWikipedia() throws Exception {
        try {
            URLConnection connection = wikiConnection.connectToWikipedia();
            assertNotNull(connection);
        } catch (IOException e) {
            System.err.println("IOException should not be thrown");
        }
    }

    @Test
    public void testTurningInputToUrl() {
        wikiConnection.setUserInput("Java Programming");

        String expectedURL = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=Java+Programming&rvprop=timestamp|user&rvlimit=13&redirects";
        String actualURL = wikiConnection.turningInputToURL();

        assertEquals(expectedURL, actualURL);
    }
}
