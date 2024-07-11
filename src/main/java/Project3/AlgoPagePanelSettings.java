package Project3;

import org.w3c.dom.Text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlgoPagePanelSettings extends JPanel implements Runnable {
    private static final String [] algorithms = {"BFS", "DFS", "Dijkstra", "A*", "MST"};
    private static final String [] themes = {"No sound", "Theme 1", "Theme 2", "Theme 3", "Theme 4"};
    private Graph graph;
    private IAlgorithm algorithm;
    private boolean isPlaying;

    public AlgoPagePanelSettings(AlgoPagePanelSimulation simulationPanel, Dimension dimension){
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
            algoRow.add(new SelectionComboBox<String> (algorithms));
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
                    simulationPanel.play();
                    graph = simulationPanel.getGraph();
                    isPlaying = !isPlaying;
                }
            });
            controlRow.add(playButton);
            ControlButton stepButton = new ControlButton("Step");
            stepButton.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Step");
                    simulationPanel.step();
                }
            });
            controlRow.add(stepButton);
            ControlButton restartButton = new ControlButton("Restart");
            restartButton.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Restart");
                    simulationPanel.restart();
                }
            });
            controlRow.add(restartButton);
        this.add(controlRow);
    }

    public boolean doNothing(boolean test) {
        return test;
    }

    @Override
    public void run() {
        while (true) {

//            System.out.println(this.isPlaying);
            doNothing(isPlaying);
            if (!isPlaying) continue;

            try {
                Thread.sleep(100);
                System.out.println("Test");
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}