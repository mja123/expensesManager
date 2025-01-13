package org.example.utils.excpetions;

import java.awt.*;

public class ServerException extends ExpensesManagerException {
    private String message;

    public ServerException() {
        super();
    }

    public ServerException(String message) {
        super(message);
        this.message = message;
        System.out.println("Error message: " + message);
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
