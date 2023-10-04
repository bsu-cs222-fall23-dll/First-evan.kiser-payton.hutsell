package edu.bsu.cs222;

import edu.bsu.cs222.Exceptions.NoPageExistException;
import edu.bsu.cs222.Model.WikipediaRevisionParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class WikipediaRevisionParserTest {

    @Test
    public void testParseWithValidData() throws NoPageExistException {
        String jsonData = "{\"query\":{\"pages\":{\"123\":{\"revisions\":[{\"id\":1,\"content\":\"Revision 1\"},{\"id\":2,\"content\":\"Revision 2\"}]}}}}";
        InputStream dataStream = new ByteArrayInputStream(jsonData.getBytes());
        WikipediaRevisionParser parser = new WikipediaRevisionParser();

        String revisions = parser.parse(dataStream);

        Assertions.assertNotNull(revisions);

    }

    @Test
    public void testParseWithNoRevisions()  {
        String jsonData = "{\"query\":{\"pages\":{\"123\":{\"revisions\":[]}}}}";
        InputStream dataStream = new ByteArrayInputStream(jsonData.getBytes());
        WikipediaRevisionParser parser = new WikipediaRevisionParser();

        String revisions;
        try {
            revisions = parser.parse(dataStream);
        } catch (NoPageExistException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertNotNull(revisions);
    }

    @Test
    public void testParseWithInvalidData() throws NoPageExistException {
        String jsonData = "{\"query\":{\"pages\":{\"123\":{\"revisions\":null}}}}";
        InputStream dataStream = new ByteArrayInputStream(jsonData.getBytes());
        WikipediaRevisionParser parser = new WikipediaRevisionParser();

        String revisions = parser.parse(dataStream);

        Assertions.assertNull(revisions);
    }
}