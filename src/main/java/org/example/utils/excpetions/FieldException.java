package org.example.utils.excpetions;

public class FieldException extends ExpensesManagerException {
    // Required field exception class
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
