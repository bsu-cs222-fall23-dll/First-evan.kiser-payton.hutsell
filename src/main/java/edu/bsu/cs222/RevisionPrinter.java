package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

public class RevisionPrinter {
    public void printListAllRevisions(JSONArray arrayWithRevisions) {
        StringBuilder printedRevisions = new StringBuilder();
        printedRevisions.append("\nList of All Revisions: Timestamp - User");
        for (Object revision : arrayWithRevisions) {
            String revisionUserName = JsonPath.read(revision, "$.user");
            String revisionTimestamp = JsonPath.read(revision, "$.timestamp");
            printedRevisions.append(revisionTimestamp + " " + revisionUserName + "\n");
        }
        System.out.println(printedRevisions);
    }
}
