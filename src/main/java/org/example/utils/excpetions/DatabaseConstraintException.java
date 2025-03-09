package org.example.utils.excpetions;

public class DatabaseConstraintException extends ServerException {
    // Exception class to use when a column constraint is not follow
    private String message = "This entry already exists.";
    public DatabaseConstraintException() {
        super();
    }

    public DatabaseConstraintException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
