package org.example.utils.excpetions;

import java.awt.*;

public class ServerException extends ExpensesManagerException {
    private final String message;

    public ServerException() {
        super();
        this.message = "Internal error";
    }

    public ServerException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
