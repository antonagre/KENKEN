package UI;

import Core.Cell;
import Core.Mediator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputBoxPanel extends JFrame implements KeyListener{
    private Mediator med = Mediator.getInstance();
    JLabel label;
    JButton setBt;
    JTextField textfield;
    Cell cell;

    public InputBoxPanel(Cell gameCell){
        super("Edit Core.Cell");
        cell = gameCell;
        setLocation(500,500);
        getContentPane().setLayout(new FlowLayout());
        textfield = new JTextField("",10);
        label=new JLabel("INSERT VALUE");
        setBt= new JButton("SET");
        textfield.addKeyListener(this);


        setBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                int val = new Integer(textfield.getText());
                if (!cell.model.checkCellUpdate(val)) {
                    label.setText("INVALID VALUE");
                } else {
                    dispose();
                    cell.model.updateValue(val);
                    med.getFrame().getContentPane().revalidate();
                    med.getFrame().repaint();
                    }
                }
            });
        getContentPane().add(label);
        getContentPane().add(textfield);
        getContentPane().add(setBt);
        pack();
        setVisible(true);

    }

    public void keyReleased(KeyEvent keyEvent) {

    }

    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
            int val = new Integer(textfield.getText());
            if (!cell.model.checkCellUpdate(val)) {
                label.setText("INVALID VALUE");
            } else {
                dispose();
                cell.model.updateValue(val);
                med.getFrame().getContentPane().revalidate();
                med.getFrame().repaint();
            }
        }
    }

    public void keyTyped(KeyEvent keyEvent) {

    }

}
