import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {

    public  MainMenu(){
        super("MENU");
        Container cp = getContentPane();
        cp.setLayout(new GridLayout(1,4));
        this.setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setSize(new Dimension(720, 70));
        setVisible(true);
        JButton newBt=new JButton("NEW");
        JButton loadBt=new JButton("LOAD");
        JButton saveBt=new JButton("SAVE");
        JButton exitBt=new JButton("EXIT");
        JButton resetBt=new JButton("RESET MAP");
        JButton resolveBt= new JButton("RESOLVE");
        resolveBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                new Risolutore();
            }
        });
        newBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        loadBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MapUtil.load();
            }
        });
        saveBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MapUtil.save();
            }
        });
        exitBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        resetBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                GameMap.getInstance().resetMap();
            }
        });
        cp.add(newBt);
        cp.add(loadBt);
        cp.add(saveBt);
        cp.add(exitBt);
        cp.add(resolveBt);
        cp.add(resetBt);

    }
}
