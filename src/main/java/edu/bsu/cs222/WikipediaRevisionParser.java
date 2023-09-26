package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;

public class WikipediaRevisionParser  {
    public JSONArray parse(InputStream dataStream) throws IOException {
        try {
//            checkForRedirection(dataStream);
            JSONArray allRevisions = JsonPath.read(dataStream, "$.query.pages.*.revisions[*]");
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

//    public void checkForRedirection(InputStream inputStream) throws IOException {
//        try{
//
//            JSONArray redirection = JsonPath.read(inputStream, "$.query.redirects[*].to");
//            if(!redirection.isEmpty()) {
//                System.out.println("Redirected to " + redirection.get(0));
//            }
//        }
//        catch (IOException e){
//            System.err.println("Error when checking for redirection! " + e.getMessage());
//        }
//    }
}
