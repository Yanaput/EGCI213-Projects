package Project3;

import javax.swing.*;
import javax.swing.event.*;
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
            "Wall", "Start", "Goal", "Search", "Path"
    };

    private Color[] pathFindComponentsColors = new Color[pathFindComponents.length];
    private ColorRadioButton[][] toggleButtons = new ColorRadioButton[pathFindComponents.length][colors.length];

    private JButton backButton, nextButton;
    private int rowsCount = 0, colsCount = 0;

    public SetUpMenuPanel(int menuWidth, int menuHeight, SetUpMenu parentFrame){
        this.parentFrame = parentFrame;
        this.setPreferredSize(new Dimension(menuWidth, menuHeight));
        this.setLayout(null);
        this.setBackground(UIConstants.SkyBlue);
        JLabel gridSizeLabel = new JLabel("Grid Size :");
        gridSizeLabel.setFont(new Font(UIConstants.fontFamily, Font.PLAIN, 12));
        gridSizeLabel.setBounds(50, 50, 90, 30);
        this.add(gridSizeLabel);

        gridRows = new JTextArea(1, 8);
        gridRows.setFont(new Font(UIConstants.fontFamily, Font.PLAIN, 18));
        gridRows.setBounds(130, 50, 100, 30);
        gridRows.setMargin(new Insets(5, 5, 5, 5));
        gridRows.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                validateInput(gridRows);
            }
        });
        gridRows.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                if (!gridRows.getText().isEmpty()) {
                    try{
                        rowsCount = Integer.parseInt(gridRows.getText().trim());
                    }catch (Exception err){}
                }
            }
        });
        this.add(gridRows);

        JLabel rowsLabel = new JLabel("Rows");
        rowsLabel.setFont(new Font(UIConstants.fontFamily, Font.PLAIN, 12));
        rowsLabel.setBounds(240, 50, 70, 30);
        this.add(rowsLabel);

        gridCols = new JTextArea(1, 8);
        gridCols.setFont(new Font(UIConstants.fontFamily, Font.PLAIN, 18));
        gridCols.setBounds(350, 50, 100, 30);
        gridCols.setMargin(new Insets(5, 5, 5, 5));
        gridCols.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                validateInput(gridCols);
            }
        });
        gridCols.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                if(!gridCols.getText().isEmpty()){
                    try{
                        colsCount = Integer.parseInt(gridCols.getText().trim());
                    }catch (Exception err){}
                }
            }
        });
        this.add(gridCols);

        JLabel colsLabel = new JLabel("Columns");
        colsLabel.setFont(gridSizeLabel.getFont().deriveFont(12f));
        colsLabel.setBounds(460, 50, 70, 30);
        this.add(colsLabel);

        JLabel colorsLabel = new JLabel("Colors : ");
        colorsLabel.setFont(new Font(UIConstants.fontFamily, Font.PLAIN, 12));
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
            cl.setFont(new Font(UIConstants.fontFamily, Font.PLAIN, 12));
            cl.setBounds(90 + 30 * (i + 1), 100, 30, 30);
            cl.setOpaque(true);
            this.add(colorsLabels.get(i));
        }

        ArrayList<JLabel> pathFindLabels = new ArrayList<>();
        for (String component : pathFindComponents) {
            pathFindLabels.add(new JLabel(component));
        }

        for (int i = 0; i < pathFindLabels.size(); i++) {
            pathFindLabels.get(i).setFont(new Font(UIConstants.fontFamily, Font.PLAIN, 12));
            pathFindLabels.get(i).setBounds(50, 100 + 30 * (i + 1), 70, 30);
            this.add(pathFindLabels.get(i));
        }

        for (int row = 0; row < pathFindComponents.length; row++) {
            for (int col = 0; col < colors.length; col++) {
                toggleButtons[row][col] = new ColorRadioButton();
                toggleButtons[row][col].setName(pathFindComponents[row]);
                toggleButtons[row][col].setColor(colors[col]);
                toggleButtons[row][col].setBounds(90 + 30 * (col + 1), 100 + 30 * (row + 1), 30, 30);
                toggleButtons[row][col].setOpaque(false);

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

        // Default values
        Random random = new Random();
        int r = random.nextInt() % 10 + 15;
        gridRows.setText("" + r);
        gridCols.setText("" + r * 16 / 9);
        toggleButtons[0][1].setSelected(true);
        toggleButtons[1][2].setSelected(true);
        toggleButtons[2][4].setSelected(true);
        toggleButtons[3][5].setSelected(true);
        toggleButtons[4][7].setSelected(true);

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
        backButton.setFont(new Font(UIConstants.fontFamily, Font.PLAIN, 16));
        this.add(backButton);

        nextButton = new JButton("Next");
        nextButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(rowsCount != 0 && colsCount != 0 && validateComponentsColors(pathFindComponentsColors)){
                    new AlgoPage(rowsCount, colsCount, pathFindComponentsColors);
                    parentFrame.dispose();
                    System.out.println(colsCount);
                    System.out.println(rowsCount);
                    for(Color color: pathFindComponentsColors)
                        System.out.println(color);
                }
                else if(rowsCount == 0 || colsCount == 0)
                    JOptionPane.showMessageDialog(parentFrame, "Incorrect grid size",
                            "Error", JOptionPane.INFORMATION_MESSAGE);

                else if(!validateComponentsColors(pathFindComponentsColors))
                    JOptionPane.showMessageDialog(parentFrame, "Please select all color of components",
                            "Error", JOptionPane.INFORMATION_MESSAGE);

                System.out.println("Next");
            }
        });
        nextButton.setBounds(490, menuHeight - 50, 80, 30);
        nextButton.setFont(new Font(UIConstants.fontFamily, Font.PLAIN, 16));
        this.add(nextButton);
    }

    private void validateInput(JTextArea textArea) {
        String text = textArea.getText();
        if (!text.matches("\\d*")) {
            JOptionPane.showMessageDialog(this, "Incorrect Data Type!\nNumbers Only!",
                    "Error", JOptionPane.INFORMATION_MESSAGE);
            textArea.setText(text.replaceAll("\\D", ""));
        }
    }

    private boolean validateComponentsColors(Color[] components){
        for(Color component : components)
            if(component == null)
                return false;
        return true;
    }
}
