package Project3;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class SetUpMenuPanel extends JPanel {
    SetUpMenu parentFrame;
    private JTextArea gridRows, gridCols;

    private Color[] colors = {
            UIConstants.Black,
            UIConstants.White,
            UIConstants.LightRed,
            UIConstants.DarkRed,
            UIConstants.Green,
            UIConstants.LightYellow,
            UIConstants.DarkYellow,
            UIConstants.Blue,
            UIConstants.Magenta,
            UIConstants.Cyan,
            UIConstants.GutterGrey,
            UIConstants.CommentGrey
    };

    private String[] pathFindComponents = {
            "Wall", "Search", "Path", "Start", "Goal"
    };

    private Color[] pathFindComponentsColors = new Color[pathFindComponents.length];
    private ColorRadioButton[][] toggleButtons = new ColorRadioButton[pathFindComponents.length][colors.length];

    private JButton backButton, nextButton;
    private int rowsCount = 0, colsCount = 0;

    public SetUpMenuPanel(int menuWidth, int menuHeight, SetUpMenu parentFrame){
        this.parentFrame = parentFrame;
        this.setPreferredSize(new Dimension(menuWidth, menuHeight));
        this.setLayout(null);

        JLabel gridSizeLabel = new JLabel("Grid Size :");
        gridSizeLabel.setFont(gridSizeLabel.getFont().deriveFont(12f));
        gridSizeLabel.setBounds(50, 50, 70, 30);
        this.add(gridSizeLabel);

        gridRows = new JTextArea(1, 8);
        gridRows.setFont(gridRows.getFont().deriveFont(18f));
        gridRows.setBounds(130, 50, 100, 30);
        gridRows.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                if(!gridRows.getText().isEmpty())
                    rowsCount = Integer.parseInt(gridRows.getText());
            }
        });
        this.add(gridRows);

        JLabel rowsLabel = new JLabel("Rows");
        rowsLabel.setFont(gridSizeLabel.getFont().deriveFont(12f));
        rowsLabel.setBounds(240, 50, 70, 30);
        this.add(rowsLabel);

        gridCols = new JTextArea(1, 8);
        gridCols.setFont(gridCols.getFont().deriveFont(18f));
        gridCols.setBounds(350, 50, 100, 30);
        gridCols.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                if(!gridCols.getText().isEmpty())
                    colsCount = Integer.parseInt(gridCols.getText());
            }
        });
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
        for (Color color : colors) {
            JLabel colorLabel = new JLabel("");
            colorLabel.setBackground(color);
            colorsLabels.add(colorLabel);
        }

        for (int i = 0; i < colorsLabels.size(); i++) {
            JLabel cl = colorsLabels.get(i);
            cl.setFont(colorsLabels.get(i).getFont().deriveFont(12f));
            cl.setBounds(90 + 30 * (i + 1), 100, 30, 30);
            cl.setOpaque(true);
            this.add(colorsLabels.get(i));
        }

        ArrayList<JLabel> pathFindLabels = new ArrayList<>();
        for (String component : pathFindComponents) {
            pathFindLabels.add(new JLabel(component));
        }

        for (int i = 0; i < pathFindLabels.size(); i++) {
            pathFindLabels.get(i).setFont(pathFindLabels.get(i).getFont().deriveFont(12f));
            pathFindLabels.get(i).setBounds(50, 100 + 30 * (i + 1), 70, 30);
            this.add(pathFindLabels.get(i));
        }

        for (int row = 0; row < pathFindComponents.length; row++) {
            for (int col = 0; col < colors.length; col++) {
                toggleButtons[row][col] = new ColorRadioButton();
                toggleButtons[row][col].setName(pathFindComponents[row]);
                toggleButtons[row][col].setColor(colors[col]);
                toggleButtons[row][col].setBounds(90 + 30 * (col + 1), 100 + 30 * (row + 1), 30, 30);

                int tempRow = row;
                int tempCol = col;
                toggleButtons[row][col].addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            System.out.println(toggleButtons[tempRow][tempCol].getName() + ": " + toggleButtons[tempRow][tempCol].getColor());
                            for (int i = 0; i < pathFindComponents.length; i++) {
                                toggleButtons[i][tempCol].setSelected(i == tempRow);
                            }
                            for (int j = 0; j < colors.length; j++) {
                                toggleButtons[tempRow][j].setSelected(j == tempCol);
                            }
                            pathFindComponentsColors[tempRow] = toggleButtons[tempRow][tempCol].getColor();
                        }
                    }
                });
                this.add(toggleButtons[row][col]);
            }
        }

        backButton = new JButton("Back");
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Back");
                parentFrame.dispose();
                new PathFindSim();
            }
        });
        backButton.setBounds(400, menuHeight - 50, 80, 30);
        backButton.setFont(backButton.getFont().deriveFont(12f));
        this.add(backButton);

        nextButton = new JButton("Next");
        nextButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new AlgoPage(rowsCount, colsCount, pathFindComponentsColors);
                System.out.println(colsCount);
                System.out.println(rowsCount);
                for(Color color: pathFindComponentsColors)
                    System.out.println(color);
                System.out.println("Next");
            }
        });
        nextButton.setBounds(490, menuHeight - 50, 80, 30);
        nextButton.setFont(nextButton.getFont().deriveFont(12f));
        this.add(nextButton);
    }
}
