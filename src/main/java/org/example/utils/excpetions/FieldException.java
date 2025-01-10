package org.example.utils.excpetions;

import java.awt.*;

public class FieldException extends ExpensesManagerException {
    public FieldException() {
        super();
    }

    @Override
    public String getMessage() {
        return "Empty required field.";
    }
}
