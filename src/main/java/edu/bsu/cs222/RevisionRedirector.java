package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.IOException;

public class RevisionRedirector {
        public void checkForRedirection(Object parsedDataStream) throws IOException {
            JSONArray redirection = JsonPath.read(parsedDataStream, "$..to");
            if(!redirection.isEmpty()) {
                System.out.println("\nRedirected to " + redirection.get(0).toString());
            }
        }
}
