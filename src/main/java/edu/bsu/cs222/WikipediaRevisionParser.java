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
            JSONArray allRevisions = JsonPath.read(testDataStream, "$..revisions");
            if(allRevisions !=null ) {
                int revisionsLimit = Math.min(allRevisions.size(), 13); // Sets a limit to the smaller number between size() and 13
                List<Revision> revisionList = new ArrayList<>(revisionsLimit);

                for (int i=0; i<revisionsLimit; i++) {
                    JSONArray revision = (JSONArray) allRevisions.get(i);
                    String revisionUser = JsonPath.read(revision, "$.user");
                    String revisionTimestamp = JsonPath.read(revision, "$.timestamp");

                    Revision revision1 = new Revision(revisionUser, revisionTimestamp);
                    //creates ArticleEd.. object with user & timestamp vars and stores in articleEd.. var
                    revisionList.add(revision1);
                    //adds the object to the list
                }
                return revisionList;
            }
            else {
                throw new IOException("No Revisions to this Article!");
            }
        }
        catch (Exception e){
            throw new IOException("Error! " + e.getMessage());
        }

    }
}
