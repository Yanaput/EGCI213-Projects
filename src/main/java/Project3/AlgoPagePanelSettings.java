package Project3;

import org.w3c.dom.Text;

import javax.swing.*;
import java.awt.*;

public class AlgoPagePanelSettings extends JPanel {
    private static final String [] algorithms = {"BFS", "DFS", "Dijkstra", "A*", "MST"};
    private static final String [] themes = {"No sound", "Theme 1", "Theme 2", "Theme 3", "Theme 4"};

    public AlgoPagePanelSettings(Dimension dimension){
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
            controlRow.add(new ControlButton("Play / Pause"));
            controlRow.add(new ControlButton("Step"));
            controlRow.add(new ControlButton("Restart"));
        this.add(controlRow);
    }
}