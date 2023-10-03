package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

public class RevisionPrinter {
    public String printListAllRevisions(JSONArray arrayWithRevisions, String redirection) {
        StringBuilder printedRevisions = new StringBuilder();
        if(redirection != null) {
            printedRevisions.append("\nRedirected to ").append(redirection).append("\n");
        }
        printedRevisions.append("\nList of All Revisions: Timestamp - User\n\n");
        for (Object revision : arrayWithRevisions) {
            String revisionUserName = JsonPath.read(revision, "$.user");
            String revisionTimestamp = JsonPath.read(revision, "$.timestamp");
            printedRevisions.append(revisionTimestamp).append(" ").append(revisionUserName).append("\n");
        }
    return String.valueOf(printedRevisions);
    }
}
