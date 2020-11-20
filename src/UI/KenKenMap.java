package UI;

import Core.Mediator;

import javax.swing.*;
import java.awt.*;

public class KenKenMap extends JFrame {
    public Mediator med = Mediator.getInstance();

    public KenKenMap(int side){
        super("KENKEN-MAP");
        Container cp = getContentPane();
        cp.setLayout(new GridLayout(side,side));
        this.setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        med.setFrame(this);
        pack();
        switch (side){
            case 3:
                setSize(new Dimension(62*side, 68*side+10));
                break;
            case 4:
                setSize(new Dimension(62*side, 68*side+5));
                break;
            case 5:
                setSize(new Dimension(62*side, 68*side-5));
                break;
            case 6:
                setSize(new Dimension(62*side, 68*side-10));
                break;
        }
        setVisible(true);
    }

}