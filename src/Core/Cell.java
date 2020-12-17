package Core;

import UI.CellButton;

import javax.swing.*;

public class Cell {
    private Mediator med= Mediator.getInstance();
    public CellModel model;
    public CellButton bt;

    public Cell(int x, int y, Block block) {
        this.model = new CellModel(x,y,block);
        this.bt = new CellButton(this);
    }

    public CellModel getModel(){
        return this.model;
    }

    public CellButton getButton(){
        return this.bt;
    }

}