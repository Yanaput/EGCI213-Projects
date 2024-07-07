package Project3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PathFindSim extends JFrame implements MouseListener {
    private PathFindSim parentFrame;
    private int frameWidth = 700;
    private int frameHeight = 500;

    private JLabel contentPane;

    public static void main(String[] args) {
        new PathFindSim();
//        new AlgoPage(25, 50);
    }

    public PathFindSim() {
        parentFrame = this;
        setTitle("Find Path Sim");
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

        JLabel label = new JLabel("Find Path Sim");
        label.setFont(new Font("Arial", Font.BOLD, 31));
        Dimension labelSize = label.getPreferredSize();
        label.setBounds((frameWidth - labelSize.width) / 2, 130, labelSize.width, labelSize.height);
        panel.add(label);

        JButton startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(150, 50));
        Dimension startButtonSize = startButton.getPreferredSize();
        startButton.setBounds((frameWidth - startButtonSize.width) / 2, 205, startButtonSize.width, startButtonSize.height);
        startButton.addMouseListener(this);
        panel.add(startButton);

        JButton aboutButton = new JButton("About");
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
        String aboutText = "How to use\nwrite sth blablablaaaaaa........\nblaaaaaa banana\npotato naa";

        if(button.getText().equals("Start")){
            parentFrame.dispose();
            new SetUpMenu();           
        } 
        else if(button.getText().equals("About")){
            JOptionPane.showMessageDialog(this, aboutText, "About", JOptionPane.PLAIN_MESSAGE);
        }
    }

    //--implement nothingg--//
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}