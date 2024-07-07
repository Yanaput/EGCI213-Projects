package Project3;

import javax.swing.*;

public class MainMenu extends JFrame {

   private static final int menuWidth = 600, menuHeight = 600;

   public MainMenu() {
       super("Main menu");
       this.setBounds(0, 0, menuWidth, menuHeight);
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setResizable(false);
       this.add(new MainMenuPanel(menuWidth, menuHeight));
       this.pack();
       this.setVisible(true);
   }

   public static void main(String[] args) {

       JFrame window = new JFrame("Main menu");

       window.setBounds(0, 0, menuWidth, menuHeight);
       window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       window.setResizable(false);
       window.add(new MainMenuPanel(menuWidth, menuHeight));

       window.pack();
       window.setVisible(true);
   }
}
