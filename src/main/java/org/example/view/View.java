package org.example.view;

import javax.swing.*;
import java.awt.*;

public abstract class View extends JFrame {
    protected View(String title, int closeBehavior) {
        this.setTitle(title);
        this.setDefaultCloseOperation(closeBehavior);
        this.setVisible(true);
    }
}
