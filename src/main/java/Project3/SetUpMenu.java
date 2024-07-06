package Project3;

import javax.swing.*;

public class SetUpMenu extends JFrame {
    private static final int menuWidth = 800, menuHeight = 600;
    public SetUpMenu(){
        super("Setup menu");
        this.setBounds(0, 0, menuWidth, menuHeight);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.add(new SetUpMenuPanel(menuWidth, menuHeight));
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("Main menu");

        window.setBounds(0, 0, menuWidth, menuHeight);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.add(new SetUpMenuPanel(menuWidth, menuHeight));

        window.pack();
        window.setVisible(true);
    }
}

