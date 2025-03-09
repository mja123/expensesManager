package org.example.utils.excpetions;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public abstract class ExpensesManagerException extends Exception {
    // Main excpetion class

    public ExpensesManagerException() {
    }

    public ExpensesManagerException(String message) {
        super(message);
    }

    public void showErrorDialog(Frame parentFrame, String message) {
        // Dialog error with custom message
        Dialog dialog = new Dialog(parentFrame, "Error", true);
        dialog.setLayout(new FlowLayout());

        // Add message to dialog
        Label label = new Label(message);
        dialog.add(label);

        // Remove dialog when closed
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dialog.dispose();
            }
        });

        dialog.setSize(350, 150);
        dialog.setVisible(true);
    }

    public abstract String getMessage();
}
