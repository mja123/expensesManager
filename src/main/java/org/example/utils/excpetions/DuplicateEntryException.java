package org.example.utils.excpetions;

import java.awt.*;

public class DuplicateEntryException extends ServerException {
    private final String message;
    public DuplicateEntryException() {
        super("This entry already exists.");
        this.message = super.getMessage();
    }

    @Override
    public String getMessage() {
        System.out.println("Error message in duplicate");
        return message;
    }
}
