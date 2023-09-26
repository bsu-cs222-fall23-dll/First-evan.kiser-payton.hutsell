package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

public class RevisionPrinter {
    public void printListAllRevisions(JSONArray arrayWithRevisions) {
        for (Object revision : arrayWithRevisions) {
            String revisionUserName = JsonPath.read(revision, "$.user");
            String revisionTimestamp = JsonPath.read(revision, "$.timestamp");
            System.out.println(revisionTimestamp + " " + revisionUserName);
        }
    }
}
