package Project3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PathFindSim extends JFrame implements MouseListener {
    private PathFindSim parentFrame;
    private int frameWidth = 700;
    private int frameHeight = 500;

    private JLabel contentPane;

    public PathFindSim() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {}
        parentFrame = this;
        this.setResizable(false);
        setTitle("PathFindSim");
        setSize(frameWidth, frameHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        contentPane = new JLabel();
        MyImageIcon background = new MyImageIcon("src/main/java/Project3/assets/Let's Play.jpg").resize(frameWidth, frameHeight);
        contentPane.setIcon(background);
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JPanel panel = new JPanel(null);
        panel.setOpaque(false);
        panel.setBounds(0, 0, frameWidth, frameHeight);
        contentPane.add(panel);

        JLabel label = new JLabel("PathFindSim");
        label.setForeground(UIConstants.DarkBlueBackground);
        label.setFont(new Font(UIConstants.fontFamily, Font.BOLD, 31));
        Dimension labelSize = label.getPreferredSize();
        label.setBounds((frameWidth - labelSize.width) / 2, 130, labelSize.width, labelSize.height);
        panel.add(label);

        CircularButton helpButton = new CircularButton("?");
        helpButton.setToolTipText("Help");
        helpButton.setMargin(new Insets(0, 0, 0, 0));
        helpButton.setBounds(frameWidth - 100, 30, 20, 20);
        helpButton.setFont(new Font(UIConstants.fontFamily, Font.BOLD, 12));
        helpButton.addMouseListener(this);
        panel.add(helpButton);

        JButton startButton = new JButton("Start");
        startButton.setFont(new Font(UIConstants.fontFamily, Font.PLAIN, 16));
        startButton.setPreferredSize(new Dimension(150, 50));
        Dimension startButtonSize = startButton.getPreferredSize();
        startButton.setBounds((frameWidth - startButtonSize.width) / 2, 205, startButtonSize.width, startButtonSize.height);
        startButton.addMouseListener(this);
        panel.add(startButton);

        JButton aboutButton = new JButton("Credits");
        aboutButton.setFont(new Font(UIConstants.fontFamily, Font.PLAIN, 16));
        aboutButton.setPreferredSize(new Dimension(150, 50));
        Dimension aboutButtonSize = aboutButton.getPreferredSize();
        aboutButton.setBounds((frameWidth - aboutButtonSize.width) / 2, 270, aboutButtonSize.width, aboutButtonSize.height);
        aboutButton.addMouseListener(this);
        panel.add(aboutButton);

        setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e){
        JButton button = (JButton) e.getSource();

        if(button.getText().equals("Start")){
            parentFrame.dispose();
            new SetUpMenu();
        }
        else if(button.getText().equals("Credits")){
            JOptionPane.showMessageDialog(this, UIConstants.creditsText, "Credits", JOptionPane.INFORMATION_MESSAGE);
        }
        else if(button.getText().equals("?")){
            JOptionPane.showMessageDialog(this, UIConstants.howToPlayText, "How to play", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

}