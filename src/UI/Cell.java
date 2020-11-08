package UI;

import Main.Block;
import Main.Mediator;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cell extends JButton {
    private Mediator med= Mediator.getInstance();
    int x, y;
    public int n;
    String description="";
    Graphics g;
    Block block;
    public boolean completed;

    public Cell(int x, int y, Block block) {
        this.x = x;
        this.y = y;
        if(block.cells.isEmpty()) {
            this.description = block.operation + " " + new Integer(block.risultatoValore).toString() ;
        }
        this.block = block;
        Border emptyBorder = BorderFactory.createEmptyBorder();
        setBorder(emptyBorder);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editCellValue();
            }
        });
    }


    public void reset(){
        updateValue(0);
    }

    public boolean checkCellUpdate(int val){
        boolean flag=true;
        if(val>med.getMap().mapSide+1) return false;
        for (Cell c:block.cells) {
            if(c.x==this.x && c.n==val) flag=false;
            else if(c.y==this.y && c.n==val) flag=false;
        }
        return flag && med.getMap().checkMatrix(this.x,this.y,val);
    }

    public void updateValue(int val){
        n=val;
        med.getMap().updateMatrix(x,y,val);
        block.bloccoCompletato(block.verificaBlocco());
        repaint();
    }

    public void updateCell(){
        n=med.getMap().valueMatrix[x][y];
    }

    @Override
    protected void paintComponent(Graphics g) {
        this.g=g;
        Rectangle bounds=getBounds();
        Graphics2D g2 = (Graphics2D) g;
        if(block.verificaBlocco()){
            g.setColor(Color.GREEN);
            g.fillRect(0, 0,getWidth(),getHeight());
        }else{
            g.setColor(Color.white);
            g.fillRect(0, 0,getWidth(),getHeight());
        }
        g.setColor(Color.DARK_GRAY);
        g2.setStroke(new BasicStroke(2));
        g2.drawString(this.description, 5, 10);
        if(n!=0) {
            g2.drawString(new Integer(this.n).toString(),60,60);
        }

        drawBorder(g2);
    }

    public void editCellValue(){
        new InputBoxPanel(this);
    }

    private void drawBorder(Graphics2D g2) {
        if (x == 0) {//TOP
            drawLine(0, 0, 120, 0,8,g2);
        }
        //BOTTOM
        if (med.getMap().checkBottom(x, y) && y != med.getMap().mapSide) {
            drawLine(0, 120, 120, 120,2,g2);
        } else {
            drawLine(0, 120, 120, 120,8,g2);
        }
        //RIGHT
        if (med.getMap().checkRight(x, y) && x != med.getMap().mapSide && y != med.getMap().mapSide) {
            drawLine(120, 120, 120, 0,2,g2);
        } else {
            drawLine(120, 120, 120, 0,8,g2);
        }
        //LEFT
        if (y == 0) {
            drawLine(0, 120, 0, 0,8,g2);
        }
    }

    public void drawLine(int x1, int y1, int x2, int y2, int thickness, Graphics2D g2){
        g2.setStroke(new BasicStroke(thickness));
        g2.drawLine(x1,y1,x2,y2);
    }

}