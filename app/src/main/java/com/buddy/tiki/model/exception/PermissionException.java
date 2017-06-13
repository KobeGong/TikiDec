package com.buddy.tiki.model.exception;

public class PermissionException extends Exception {
    private int type;

    public PermissionException(int type, String message) {
        super(message);
        this.type = type;
    }

    public int getType() {
        return this.type;
    }
}
