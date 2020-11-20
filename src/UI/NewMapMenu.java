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
        JButton map3=getButton(3);
        JButton map4=getButton(4);
        JButton map5=getButton(5);
        JButton map6=getButton(6);
        map3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                med.loadNewMap(3);
            }
        });
        map4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                med.loadNewMap(4);
            }
        });
        map5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                med.loadNewMap(5);
            }
        });
        map6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                med.loadNewMap(6);
            }
        });
        cp.add(map3);
        cp.add(map4);
        cp.add(map5);
        cp.add(map6);
    }

    private JButton getButton(int n) {
        String s = new Integer(n).toString();
        JButton button;
        try {
            File f = new File(current+"/src/UI/images/map"+s+".png");
            System.out.println(f.toPath());
            BufferedImage buttonIcon = ImageIO.read(f);
            button = new JButton(new ImageIcon(buttonIcon));
        } catch (Exception e) {
            e.printStackTrace();
            button = new JButton(s+"X"+s);
        }
         return button;
    }


}
