package UI;

import Main.Mediator;

import javax.swing.*;
import java.awt.*;

public class KenKenMap extends JFrame {
    public Container cp;
    public Mediator med = Mediator.getInstance();

    public static KenKenMap getInstance(){
        return new KenKenMap(5,5);
    }
    public KenKenMap(int x,int y){
        super("KENKEN-MAP");
        Container cp = getContentPane();
        cp.setLayout(new GridLayout(x,y));
        this.setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        med.setFrame(this);
        pack();
        setSize(new Dimension(62*x, 68*y));
        setVisible(true);
    }






}