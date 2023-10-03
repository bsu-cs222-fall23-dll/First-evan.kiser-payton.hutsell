package edu.bsu.cs222.Model;

import edu.bsu.cs222.Exceptions.NoPageExistException;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import java.io.InputStream;

public class WikipediaRevisionParser  {
    RevisionPrinter printer = new RevisionPrinter(); // Used to Call RevisionPrinter
    public String parse(InputStream dataStream) throws NoPageExistException {
    try {

        // Parsing the Data Stream to be able to JsonPath.read the data stream multiple times
        Object parsedDataStream = Configuration.defaultConfiguration().jsonProvider().parse(dataStream.readAllBytes());

        JSONArray allRevisions = JsonPath.read(parsedDataStream, "$.query.pages.*.revisions[*]");

        RevisionRedirector redirector = new RevisionRedirector();
        String redirection = redirector.checkForRedirection(parsedDataStream);

        JSONArray pageMissingCheck = JsonPath.read(parsedDataStream, "$..missing");
        if (!pageMissingCheck.isEmpty()) {
            throw new NoPageExistException();
        }

        if (allRevisions != null) {
            int revisionsLimit = Math.min(allRevisions.size(), 13); // Sets a limit to the smaller number between size() and 13
            JSONArray revisionList = new JSONArray();
            for (int i = 0; i < revisionsLimit; i++) {
                revisionList.add(allRevisions.get(i));
            }
            return printer.printListAllRevisions(revisionList, redirection);
        }
    } catch (Exception e) {
        throw new NoPageExistException();
    }
        return null;
    }

}
