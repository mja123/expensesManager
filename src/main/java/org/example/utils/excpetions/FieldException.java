package org.example.utils.excpetions;

import java.awt.*;

public class FieldException extends ExpensesManagerException {
    private String errorMessage = "Empty required field.";
    public FieldException() {
        super();
    }

    public FieldException(String message) {
        super(message);
        this.errorMessage = message;
    }
    @Override
    public String getMessage() {
        return errorMessage;
    }
}
