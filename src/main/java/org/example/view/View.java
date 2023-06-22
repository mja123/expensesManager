package org.example.view;

import javax.swing.*;
import java.awt.*;

public abstract class View extends JFrame {
    protected View(String title, int closeBehavior) {
        this.setSize();
        this.setTitle(title);
        this.setDefaultCloseOperation(closeBehavior);
        this.setVisible(true);
    }

    private void setSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize((int) (screenSize.width / 1.3), (int) (screenSize.height / 1.3));
    }
}
