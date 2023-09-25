package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class WikipediaRevisionParser  {
    public List<Revision> parse(InputStream testDataStream) throws IOException {
        try {
            JSONArray allRevisions = JsonPath.read(testDataStream, "$..allrevisions.*");
            if(allRevisions != null ) {
                int revisionsLimit = Math.min(allRevisions.size(), 13); // Sets a limit to the smaller number between size() and 13
                List<Revision> revisionList = new ArrayList<>(revisionsLimit);

                List<String> revisionUser = JsonPath.read(allRevisions, "$..user");
                List<String> revisionTimestamp = JsonPath.read(allRevisions, "$..timestamp");
                for (int i=0; i<revisionsLimit; i++) {
                    Revision revisionHolder = new Revision(revisionUser.get(i), revisionTimestamp.get(i));
                    revisionList.add(revisionHolder);
                }
                return revisionList;
            }
            else {
                System.err.println("There is No article with that name!");
            }
        }
        catch (IOException e){
            System.err.println("Error! " + e.getMessage());
        }

    }
}
