package Project3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class SetUpMenuPanel extends JPanel {
    private JTextArea gridRows, gridCols;
    private JLabel img;

    private String[] colors = {
            "Red", "Green", "Blue", "Black", "Yellow"
    };

    private String[] pathFindComponents = {
            "Wall", "Search", "Path", "Start", "Goal"
    };


    private JToggleButton[][] toggleButtons = new JToggleButton[pathFindComponents.length][colors.length];

    private JButton backButton, nextButton;

    public SetUpMenuPanel(int menuWidth, int menuHeight){
        this.setPreferredSize(new Dimension(menuWidth, menuHeight));
        this.setLayout(null);


        JLabel gridSizeLabel = new JLabel("Grid Size :");
        gridSizeLabel.setFont(gridSizeLabel.getFont().deriveFont(12f));
        gridSizeLabel.setBounds(50, 50, 70, 30);
        this.add(gridSizeLabel);

        gridRows = new JTextArea(1,8);
        gridRows.setFont(gridRows.getFont().deriveFont(18f));
        gridRows.setBounds(130,50, 100, 30);
        this.add(gridRows);

        JLabel rowsLabel = new JLabel("Rows");
        rowsLabel.setFont(gridSizeLabel.getFont().deriveFont(12f));
        rowsLabel.setBounds(240, 50, 70, 30);
        this.add(rowsLabel);

        gridCols = new JTextArea(1,8);
        gridCols.setFont(gridCols.getFont().deriveFont(18f));
        gridCols.setBounds(350,50, 100, 30);
        this.add(gridCols);

        JLabel colsLabel = new JLabel("Columns");
        colsLabel.setFont(gridSizeLabel.getFont().deriveFont(12f));
        colsLabel.setBounds(460, 50, 70, 30);
        this.add(colsLabel);

        JLabel colorsLabel = new JLabel("Colors : ");
        colorsLabel.setFont(gridSizeLabel.getFont().deriveFont(12f));
        colorsLabel.setBounds(50, 100, 70, 30);
        this.add(colorsLabel);

        ArrayList<JLabel> colorsLabels = new ArrayList<>();
        for (String color : colors) {
            colorsLabels.add(new JLabel(color));
        }

        for (int i = 0; i < colorsLabels.size(); i++) {
            colorsLabels.get(i).setFont(colorsLabels.get(i).getFont().deriveFont(12f));
            colorsLabels.get(i).setBounds(50 + 70*(i+1), 100, 70, 30);
            this.add(colorsLabels.get(i));
        }
        ArrayList<JLabel>pathFindLabels = new ArrayList<>();
        for (String component : pathFindComponents)
            pathFindLabels.add(new JLabel(component));

        for (int i = 0; i < pathFindLabels.size(); i++) {
            pathFindLabels.get(i).setFont(pathFindLabels.get(i).getFont().deriveFont(12f));
            pathFindLabels.get(i).setBounds(50, 100 + 30*(i+1), 70, 30);
            this.add(pathFindLabels.get(i));
        }

        for (int row = 0; row < pathFindComponents.length; row++) {
            for (int col = 0; col < colors.length; col++) {
                toggleButtons[row][col] = new JRadioButton();
                toggleButtons[row][col].setName(colors[col]+","+pathFindComponents[row]);
                toggleButtons[row][col].setBounds(50 + 70*(col+1), 100 + 30*(row+1), 30, 30);

                int tempRow = row;
                int tempCol = col;
                toggleButtons[row][col].addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED){
                            System.out.println(toggleButtons[tempRow][tempCol].getName());
                            for(int i = 0; i < pathFindComponents.length; i++)
                                toggleButtons[tempRow][i].setSelected(i == tempCol);
                            for (int j = 0; j < colors.length; j++)
                                toggleButtons[j][tempCol].setSelected(j == tempRow);
                        }
                    }
                });
                this.add(toggleButtons[row][col]);
            }
        }
        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Back");
            }
        });
        backButton.setBounds(400, 400, 80, 30);
        backButton.setFont(backButton.getFont().deriveFont(12f));
        this.add(backButton);

        nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Next");
            }
        });
        nextButton.setBounds(490, 400, 80, 30);
        nextButton.setFont(nextButton.getFont().deriveFont(12f));
        this.add(nextButton);
    }
}

