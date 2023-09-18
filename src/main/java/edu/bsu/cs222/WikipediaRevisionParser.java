package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;

import java.io.IOException;
import java.io.InputStream;
public class WikipediaRevisionParser {
    public String parse(InputStream testDataStream) throws IOException {
        Object result = JsonPath.read(testDataStream, "$..timestamp"); // Should be a JSONArray object
        return null;
    }
}
