import java.util.ArrayList;
import java.util.Random;
import Main.Matrix;

public class Generatore2 {
    Random rand=new Random();
    int side;
    public enum Direction {BOTTOM,RIGHT};
    int[][] valueMatrix;
    int[][] map;
    ArrayList<Integer> excluded = new ArrayList<>();


    public Generatore2(int side) {
        valueMatrix =new int[side][side];
        map=new int[side][side];
        this.side=side;
        //updateCell(0,0);
        genBlocks(0,0,1,8);
        System.out.println(Matrix.MatrixToString(map));
    }

    public void genBlocks(int x,int y,int id, int nCells){
        int randDirection= rand.nextInt(4);
        switch (randDirection){
        case 0://GO RIGHT
            if(y+1<side && map[x][y+1]==0){
                y++;
                nCells--;
            }
            break;
        case 1://GO LEFT
            if(y-1>0 && map[x][y-1]==0){
                y--;
                nCells--;
            }
            break;
        case 2://GO BOTTOM
            if(x+1<side && map[x+1][y]==0){
                x++;
                nCells--;
            }
            break;
        }
        map[x][y]=id;
        genBlocks(x,y,id,nCells);

    }

    public static  void main(String[] args){
        Generatore2 g=new Generatore2(6);

    }
}
