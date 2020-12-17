package EXTRA;
import Core.Util;
import java.util.Random;

public class GeneratoreVal {
    Random rand=new Random();
    int side;
    int[][] mat;

    public GeneratoreVal(int side) {
       generateValues(side);
    }



    public void generateValues(int side) {
        this.side=side;
        mat=new int[side][side];
        genValues(mat,new Point(0,0));
        if(Util.matrixContains(mat,0)){
            regenerate();
            return;
        }
    }

    public void regenerate() {
        System.out.println("Reg");
        this.mat=new int[side][side];
        this.generateValues(this.side);
    }

    public boolean checkMatrix(Point cell, int x) {
        for (int i = 0; i < side; i++) {
            if (mat[cell.x][i] == x) {
                return false;//NOT VALID UPDATE COL
            } else if (mat[i][cell.y] == x) {
                return false;//NOT VALID UPDATE ROW
            }
        }
        return true;
    }

    private Point findEmpty(Point last){
        int count = 0;
        while (count<side*side){
            int x = rand.nextInt(side);
            int y = rand.nextInt(side);
            if(mat[x][y]==0){
                Point p = new Point(x,y);
                if(p!=last){
                    return p;
                }
                else {
                    this.findEmpty(last);
                }
            }
            else{
                count++;
            }
        }
        return null;
    }


    private void genValues(int[][] m,Point last) {
        int[][] old=Util.cloneMatrix(m);
        Point empty = findEmpty(last);
        if(empty==null) return;
        if (empty != null) {
            for (int i = 1; i < side + 1; i++) {
                if (checkMatrix(empty,i)) {
                    mat[empty.x][empty.y]=i;
                    genValues(mat,empty);
                }
            }
            genValues(old,empty);

        }
        return;
    }

    public static  void main(String args[]){
        GeneratoreVal g=new GeneratoreVal(9);
        System.out.println(Util.MatrixToString(g.mat));
    }
}
