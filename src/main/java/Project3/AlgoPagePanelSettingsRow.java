package Project3;

import javax.swing.*;
import java.awt.*;

public class AlgoPagePanelSettingsRow extends JPanel {

    public AlgoPagePanelSettingsRow() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBackground(UIConstants.Transparent);
    }
    public AlgoPagePanelSettingsRow(LayoutManager layout){
        this.setLayout(layout);
        this.setBackground(UIConstants.Transparent);
    }
}