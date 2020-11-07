package UI;

import Main.Mediator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RisolutoreGui extends JFrame {
    int counter=1;
    JTextField counterDisplay;
    ArrayList<int[][]> soluzioni;

    public RisolutoreGui(ArrayList<int[][]> s){
        super("SOLUZIONI");
        soluzioni=s;
        Container cp = getContentPane();
        cp.setLayout(new GridLayout(1,3));
        this.setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setSize(new Dimension(400, 70));
        setVisible(true);
        JButton next=new JButton("NEXT");
        JButton previous=new JButton("PREVIOUS");
        counterDisplay = new JTextField("",2);
        counterDisplay.setEditable(false);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(counter!=soluzioni.size()){
                    counter++;
                    showMap();
                }
            }
        });
        previous.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(counter!=1) {
                    counter--;
                    showMap();
                }
            }
        });
        cp.add(previous);
        cp.add(counterDisplay);
        cp.add(next);
        showMap();
    }

    public void showMap() {
        counterDisplay.setText("Soluzione n: "+new Integer(counter).toString());
        int[][] currentSol = soluzioni.get(counter-1);
        Mediator.getInstance().getMap().valueMatrix=currentSol;
        Mediator.getInstance().getMap().updateMap();
    }

}
