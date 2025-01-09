package org.example.view;

import javax.swing.*;
import java.awt.*;

public abstract class View extends JFrame {
    protected View(String title, int closeBehavior) {
        this.setTitle(title);
        this.setDefaultCloseOperation(closeBehavior);
        this.setVisible(true);
    }

    // Method to display an error dialog
    protected void showErrorDialog(Frame parent, String message) {
        Dialog dialog = new Dialog(parent, "Error", true);
        dialog.setLayout(new FlowLayout());

        Label label = new Label(message);
        dialog.add(label);

        Button closeButton = new Button("Close");
        closeButton.addActionListener(e -> dialog.setVisible(false));
        dialog.add(closeButton);
        dialog.setSize(300, 150);
        dialog.setVisible(true);
    }
}
