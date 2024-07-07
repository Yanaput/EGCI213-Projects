package Project3;

import javax.swing.*;

public class SetUpMenu extends JFrame {
    private static final int menuWidth = 600, menuHeight = 350;

    public SetUpMenu(){
        super("Setup menu");
        this.setBounds(0, 0, menuWidth, menuHeight);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setContentPane(new SetUpMenuPanel(menuWidth, menuHeight, this));
        this.pack();
        this.setVisible(true);
    }
}