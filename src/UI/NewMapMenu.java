package UI;

import Core.Mediator;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class NewMapMenu extends JFrame {
    private static String current;

    static {
        try {
            current = new File( "." ).getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Mediator med = Mediator.getInstance();

    public NewMapMenu() {
        super("NEW MAP");
        Container cp = getContentPane();
        cp.setLayout(new GridLayout(1,4));
        this.setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setSize(new Dimension(490, 150));
        setVisible(true);
        JButton map3=getButton(3,this);
        JButton map4=getButton(4,this);
        JButton map5=getButton(5,this);
        JButton map6=getButton(6,this);
        cp.add(map3);
        cp.add(map4);
        cp.add(map5);
        cp.add(map6);
    }



    private JButton getButton(int n,JFrame frame) {
        String s = new Integer(n).toString();
        JButton button;
        try {
            File f = new File(current+"/src/images/map"+s+".png");
            System.out.println(f.toPath());
            BufferedImage buttonIcon = ImageIO.read(f);
            button = new JButton(new ImageIcon(buttonIcon));
        } catch (Exception e) {
            e.printStackTrace();
            button = new JButton(s+"X"+s);
        }
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                med.loadNewMap(n);
                frame.dispose();
            }
        });
         return button;
    }


}
