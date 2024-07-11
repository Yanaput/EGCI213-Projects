package Project3;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AlgoPagePanelSettings extends JPanel implements Runnable {
    private static final IAlgorithm [] algorithms = {new BFS(), new DFS(), new Dijkstra(), new AStar(), new BellmansFord()};
    private static final String [] simSpeed = {"100%", "80%", "60%", "40%", "20%" ,"10%"};
    private volatile Graph graph;
    private volatile IAlgorithm algorithm;
    private int speedDelay = 1;

    private AlgoPagePanel parentPanel;

    private boolean isReset = false;
    // Volatile to prevent compiler from removing this variable
    private volatile boolean isPlaying;

    public AlgoPagePanelSettings(AlgoPagePanelSimulation simulationPanel, Dimension dimension, AlgoPagePanel parentPanel){
        this.parentPanel = parentPanel;
        this.setPreferredSize(dimension);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(UIConstants.DarkBlueBackground.darker());
        this.algorithm = new NullAlgorithm();
        this.isPlaying = false;
        Thread runThread = new Thread(this);
        runThread.start();

        AlgoPagePanelSettingsRow algoRow = new AlgoPagePanelSettingsRow(new FlowLayout(FlowLayout.LEFT));
            // Algorithm selection text
            algoRow.add(new TextLabel("Algorithm: "));
            // Algorithm selection combo box
            SelectionComboBox<IAlgorithm> comboBox = new SelectionComboBox<IAlgorithm>(algorithms);
            comboBox.setFont(new Font(UIConstants.fontFamily, Font.PLAIN, 16));
            algoRow.add(comboBox);
        this.add(algoRow);

        AlgoPagePanelSettingsRow speedRow = new AlgoPagePanelSettingsRow(new GridLayout(1, 2));
            // Speed selection text
            speedRow.add(new TextLabel("Speed: "));
            // Speed selection combo box
            SelectionList<String> speedList = new SelectionList<String>(simSpeed);
            speedList.setSelectedValue("100%", false);
            speedList.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if(!e.getValueIsAdjusting()) {
                        speedDelay = switch (speedList.getSelectedValue()) {
                            case "80%"->15;
                            case "60%"->25;
                            case "40%"->40;
                            case "20%"->80;
                            case "10%"->100;
                            default -> 10;
                        };
                    }
                }
            });
            speedRow.add(speedList);

        this.add(speedRow);
        AlgoPagePanelSettingsRow blankRow = new AlgoPagePanelSettingsRow(new GridLayout(1, 1));
        this.add(blankRow);

        AlgoPagePanelSettingsRow controlRow = new AlgoPagePanelSettingsRow(new FlowLayout(FlowLayout.CENTER));
            // Control algorithm buttons
            ControlButton playButton = new ControlButton("Play / Pause");
            playButton.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Play / Pause");
                    if (algorithm.isFinished() || comboBox.getSelectedItem() != algorithm || graph == null) {
                        graph = simulationPanel.getGraph();

                        System.out.println("Here");
                        if(graph != null) {
                            algorithm = (IAlgorithm) comboBox.getSelectedItem();
                            algorithm.setGraph(graph);
                            algorithm.reset();
                            isPlaying = false;
                        }
                        else
                            JOptionPane.showMessageDialog(parentPanel.getParentFrame(), "Incorrect Start and Destination",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    if(graph != null){
                        isPlaying = !isPlaying;
                    }
                    System.out.println(graph);
                    System.out.println(algorithm);
                }
            });
            controlRow.add(playButton);
            ControlButton stepButton = new ControlButton("Step");
            stepButton.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Step");
                    if (algorithm.isFinished() || comboBox.getSelectedItem() != algorithm || graph == null) {
                        graph = simulationPanel.getGraph();
                        if(graph != null) {
                            algorithm = (IAlgorithm) comboBox.getSelectedItem();
                            algorithm.setGraph(graph);
                            algorithm.reset();
                            isPlaying = false;
                        }
                        else
                            JOptionPane.showMessageDialog(parentPanel.getParentFrame(), "Incorrect Start and Destination",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    if (!isPlaying && graph != null)
                        algorithm.step();
                }
            });
            controlRow.add(stepButton);
            ControlButton restartButton = new ControlButton("Restart");
            restartButton.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Restart");
                    isPlaying = false;
                    graph = simulationPanel.getGraph();
                    algorithm = (IAlgorithm) comboBox.getSelectedItem();
                    algorithm.setGraph(graph);
                    algorithm.reset();
                    graph = null;
                }
            });
            controlRow.add(restartButton);
        this.add(controlRow);
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font(UIConstants.fontFamily, Font.PLAIN, 16));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Back");
                parentPanel.getParentFrame().dispose();
                new SetUpMenu();
            }
        });
        this.add(backButton);
    }

    @Override
    public void run() {
        while (true) {

            // If paused, ignore the rest
            if (!isPlaying) continue;

            try {
                Thread.sleep(speedDelay);
                this.algorithm.step();
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}