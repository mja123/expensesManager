package org.example.utils.excpetions;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public abstract class ExpensesManagerException extends Exception {

    public ExpensesManagerException() {
    }

    public ExpensesManagerException(String message) {
        super(message);
    }

    public void showErrorDialog(Frame parentFrame, String message) {
        Dialog dialog = new Dialog(parentFrame, "Error", true);
        dialog.setLayout(new FlowLayout());

        Label label = new Label(message);
        dialog.add(label);

        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dialog.dispose();
            }
        });

        dialog.setSize(300, 150);
        dialog.setVisible(true);
    }

    public abstract String getMessage();
}
