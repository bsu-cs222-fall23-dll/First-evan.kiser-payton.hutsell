package edu.bsu.cs222;

import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class WikipediaRevisionParserTest {

    @Test
    public void testParseWithValidData() throws IOException {
        String jsonData = "{\"query\":{\"pages\":{\"123\":{\"revisions\":[{\"id\":1,\"content\":\"Revision 1\"},{\"id\":2,\"content\":\"Revision 2\"}]}}}}";
        InputStream dataStream = new ByteArrayInputStream(jsonData.getBytes());
        WikipediaRevisionParser parser = new WikipediaRevisionParser();

        JSONArray revisions = parser.parse(dataStream);

        Assertions.assertNotNull(revisions);
        Assertions.assertEquals(2, revisions.size(), "Expected 2 revisions to be parsed.");
        Assertions.assertEquals("Revision 1", revisions.get(0), "First revision content should match.");
        Assertions.assertEquals("Revision 2", revisions.get(1), "Second revision content should match.");

    }

    @Test
    public void testParseWithNoRevisions() throws IOException {
        String jsonData = "{\"query\":{\"pages\":{\"123\":{\"revisions\":[]}}}}";
        InputStream dataStream = new ByteArrayInputStream(jsonData.getBytes());
        WikipediaRevisionParser parser = new WikipediaRevisionParser();

        JSONArray revisions = parser.parse(dataStream);

        Assertions.assertNotNull(revisions);
        Assertions.assertEquals(0, revisions.size());
    }

    @Test
    public void testParseWithInvalidData() throws IOException {
        String jsonData = "{\"query\":{\"pages\":{\"123\":{\"revisions\":null}}}}";
        InputStream dataStream = new ByteArrayInputStream(jsonData.getBytes());
        WikipediaRevisionParser parser = new WikipediaRevisionParser();

        JSONArray revisions = parser.parse(dataStream);

        Assertions.assertNull(revisions);
    }
}