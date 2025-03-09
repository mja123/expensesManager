package org.example.view.frame;

import javax.swing.*;

// View base class
public abstract class View extends JFrame {
    protected View(String title, int closeBehavior) {
        this.setTitle(title);
        this.setDefaultCloseOperation(closeBehavior);
        this.setVisible(true);
    }
}
