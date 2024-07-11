package Project3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AlgoPagePanelSettings extends JPanel implements Runnable {
    private static final IAlgorithm [] algorithms = {new BFS(), new DFS(), new Dijkstra(), new AStar(), new BellmansFord()};
    private static final String [] themes = {"No sound", "Theme 1", "Theme 2", "Theme 3", "Theme 4"};
    private volatile Graph graph;
    private volatile IAlgorithm algorithm;

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

        AlgoPagePanelSettingsRow soundRow = new AlgoPagePanelSettingsRow(new GridLayout(1, 2));
            // Theme sound selection text
            soundRow.add(new TextLabel("Sound: "));
            // Theme sound selection combo box
            soundRow.add(new SelectionList<String>(themes));
        this.add(soundRow);
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
                this.algorithm.step();
                Thread.sleep(1);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}