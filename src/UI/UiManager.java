package UI;

import javax.swing.*;
import java.util.ArrayList;

public class UiManager {
    private JFrame frame;
    private ArrayList<CellButton> cellsBt = new ArrayList<>();

    public void setFrame(JFrame f) {
        frame = f;
    }
    public JFrame getFrame() {
        return frame;
    }
}
