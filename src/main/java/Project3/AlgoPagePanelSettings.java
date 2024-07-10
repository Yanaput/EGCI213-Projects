package Project3;

import org.w3c.dom.Text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlgoPagePanelSettings extends JPanel {
    private static final String [] algorithms = {"BFS", "DFS", "Dijkstra", "A*", "MST"};
    private static final String [] themes = {"No sound", "Theme 1", "Theme 2", "Theme 3", "Theme 4"};
    private JPanel simulationPanel;

    public AlgoPagePanelSettings(JPanel simulationPanel, Dimension dimension){
        this.simulationPanel = simulationPanel;
        this.setPreferredSize(dimension);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(UIConstants.DarkBlueBackground.darker());

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
                }
            });
            controlRow.add(playButton);
            ControlButton stepButton = new ControlButton("Step");
            stepButton.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Step");
                }
            });
            controlRow.add(stepButton);
            ControlButton restartButton = new ControlButton("Restart");
            restartButton.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Restart");
                }
            });
            controlRow.add(restartButton);
        this.add(controlRow);
    }
}