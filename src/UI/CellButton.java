package UI;
import Core.Cell;
import Core.CellModel;
import Core.Mediator;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CellButton extends JButton {
    private Mediator med = Mediator.getInstance();
    Cell c;
    CellModel model;
    Graphics g;
    String description="";

    public CellButton(Cell cell) {
        this.c=cell;
        this.model =cell.getModel();
        if(model.block.cells.isEmpty()) {
            this.description = new Integer(model.block.resultValue).toString() + " " + model.block.operation ;
        }
        Border emptyBorder = BorderFactory.createEmptyBorder();
        setBorder(emptyBorder);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editCellDialog();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        this.g=g;
        Graphics2D g2 = (Graphics2D) g;
        if(model.block.checkBlock()){
            g.setColor(Color.cyan);
            g.fillRect(0, 0,getWidth(),getHeight());
        }else{
            g.setColor(Color.white);
            g.fillRect(0, 0,getWidth(),getHeight());
        }
        g.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2));
        g2.drawString(this.description, 5, 10);
        if(model.n!=0) {
            g2.drawString(new Integer(model.n).toString(),30,30);
        }
        drawBorder(g2);
    }

    public void editCellDialog(){
        new InputBoxPanel(this.c);
    }

    private void drawBorder(Graphics2D g2) {
        if (model.x == 0) {//TOP
            drawLine(0, 0, 60, 0,4,g2);
        }
        //BOTTOM
        if (med.getMap().checkBottom(c) && model.y != med.getMap().mapSide) {
            drawLine(0, 60, 60, 60,1,g2);
        } else {
            drawLine(0, 60, 60, 60,4,g2);
        }
        //RIGHT
        if (med.getMap().checkRight(c) && model.x != med.getMap().mapSide && model.y != med.getMap().mapSide) {
            drawLine(60, 60, 60, 0,1,g2);
        } else {
            drawLine(60, 60, 60, 0,4,g2);
        }
        //LEFT
        if (model.y == 0) {
            drawLine(0, 60, 0, 0,4,g2);
        }
    }

    public void drawLine(int x1, int y1, int x2, int y2, int thickness, Graphics2D g2){
        g2.setStroke(new BasicStroke(thickness));
        g2.drawLine(x1,y1,x2,y2);
    }

}
