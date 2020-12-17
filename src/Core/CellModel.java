package Core;

public class CellModel {
    private Mediator med = Mediator.getInstance();
    public int x, y;
    public int n;
    public Block block;
    private Cell c;

    public CellModel(int x, int y, Block block,Cell c) {
        this.x = x;
        this.y = y;
        this.block = block;
        this.c=c;
    }

    public void reset(){
        updateValue(0);
    }

    public boolean checkCellUpdate(int val){
        boolean flag=true;
        if(val>med.getMap().mapSide) return false;
        for (Cell c:this.block.cells) {
            if(this.x==this.x && this.n==val) flag=false;
            else if(this.y==this.y && this.n==val) flag=false;
        }
        return flag && med.getMap().checkMatrix(this.x,this.y,val);
    }

    public void updateValue(int val){
        this.n=val;
        Mediator.getInstance().getMap().updateMatrix(this.x,this.y,val);
        Mediator.getInstance().updateGuiFrame();
    }


}