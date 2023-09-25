package edu.bsu.cs222;

public class Revision {
    private String revisionUser;
    private String revisionTimestamp;

    public Revision(String user, String timestamp) {
        this.revisionUser = user;
        this.revisionTimestamp = timestamp;
    }

    @Override
    public String toString() {
        return "\n" + this.revisionTimestamp + " " + this.revisionUser;
    }
}
