package edu.bsu.cs222;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;

public class WikipediaRevisionParser  {
    public JSONArray parse(InputStream dataStream) throws IOException {
        try {
        // Parsing the Data Stream to be able to JsonPath.read the data stream multiple times
            Object parsedDataStream = Configuration.defaultConfiguration().jsonProvider().parse(dataStream.readAllBytes());

            JSONArray allRevisions = JsonPath.read(parsedDataStream, "$.query.pages.*.revisions[*]");

            RevisionRedirector redirector = new RevisionRedirector();
            redirector.checkForRedirection(parsedDataStream);
            ifPageMissing(parsedDataStream);

            if(allRevisions != null ) {
                int revisionsLimit = Math.min(allRevisions.size(), 13); // Sets a limit to the smaller number between size() and 13
                JSONArray revisionList = new JSONArray();
                for (int i=0; i<revisionsLimit; i++) {
                    revisionList.add(allRevisions.get(i));
                }
                return revisionList;
            }
            else {
                System.err.println("There is no revision of that article with that name!");
            }
        }
        catch (IOException e){
            System.err.println("Error parsing data! " + e.getMessage());
        }
        return null; // returns null if the programs doesn't run try method
    }
    public void ifPageMissing(Object parsedDataStream) {
        JSONArray pageMissingCheck  = JsonPath.read(parsedDataStream, "$..missing");
        if( !pageMissingCheck.isEmpty()) {
            System.err.println("Error, No Page Found!");
        }
    }


}
