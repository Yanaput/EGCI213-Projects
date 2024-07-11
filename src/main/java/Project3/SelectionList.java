package Project3;

import javax.swing.*;
import java.awt.*;

public class SelectionList<T> extends JList<T> {
    public SelectionList(T [] t) {
        super(t);
        this.setBackground(UIConstants.DarkBlueBackground.brighter());
        this.setForeground(UIConstants.White);
        this.setFont(new Font(UIConstants.fontFamily, Font.PLAIN, 16));
    }
}
