package org.bluk.avmeister.exceptions.groups;

public class GroupNotFoundException extends RuntimeException {
    public GroupNotFoundException() {
        super("Group not found");
    }
}
