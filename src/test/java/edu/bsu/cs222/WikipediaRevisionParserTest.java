package edu.bsu.cs222;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Objects;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WikipediaRevisionParserTest {
    WikipediaRevisionParser parser = new WikipediaRevisionParser();
        @Test
        public void testAccessToJsonFile() throws IOException {
            String jsonData = readSampleFileAsString();
            Assertions.assertNull(jsonData);
        }

        @Test
        public void testCountRevisionsWithJsonPath() throws IOException {
            String jsonData = readSampleFileAsString();
            JSONArray revisions = getRevisionsFromJson(jsonData);
            Assertions.assertEquals(4, revisions.size());
        }

        private String readSampleFileAsString() throws NullPointerException, IOException {
            InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.json");
            return new String(Objects.requireNonNull(sampleFile).readAllBytes(), Charset.defaultCharset());
        }

        private JSONArray getRevisionsFromJson(String jsonData) {
            return JsonPath.read(jsonData, "$..revisions[*]");
        }

}
