package edu.bsu.cs222.Model;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.IOException;

public class RevisionRedirector {
        public String checkForRedirection(Object parsedDataStream) throws IOException {
            JSONArray redirection = JsonPath.read(parsedDataStream, "$..to");
            if(!redirection.isEmpty()) {
                return redirection.get(0).toString();
            }
            return null;
        }
}
