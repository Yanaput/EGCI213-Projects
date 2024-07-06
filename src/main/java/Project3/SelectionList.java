package Project3;

import javax.swing.*;

public class SelectionList<T> extends JList<T> {
    public SelectionList(T [] t) {
        super(t);
        this.setBackground(UIConstants.DarkBlueBackground.brighter());
        this.setForeground(UIConstants.White);
    }
}
