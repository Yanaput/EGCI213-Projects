package Project3;

import javax.swing.*;

public class SelectionComboBox <T> extends JComboBox<T> {
    public SelectionComboBox(T [] t) {
        super(t);
        this.setBackground(UIConstants.DarkBlueBackground.brighter());
        this.setForeground(UIConstants.DarkBlueBackground);
    }
}
