import Main.Matrix;
import java.util.Random;

public class Generatore {
    Random rand=new Random();
    int side;
    int[][] mat;

    public Generatore(int side) {
        mat=new int[side][side];
        this.side=side;
        Random rand=new Random();
        updateCell(0,0);
    }

    public boolean checkMatrix(int row, int col, int x) {
        for (int i = 0; i < side; i++) {
            if (mat[row][i] == x) {
                return false;//NOT VALID UPDATE COL
            } else if (mat[i][col] == x) {
                return false;//NOT VALID UPDATE ROW
            }
        }
        return true;
    }

    private void updateCell(int x,int y) {
        if (mat[side-1][side-1] == 0) {
            int n = rand.nextInt(side + 1);
            if (checkMatrix(x, y, n)) {
                mat[x][y] = n;
                System.out.println(Matrix.MatrixToString(mat));
                if (y < side - 1) {
                    y++;
                } else if (y == side - 1) {
                    x++;
                    y = 0;
                }
                updateCell(x, y);
            } else {
                updateCell(x, y);
            }
        }
        return;
    }

    public static  void main(String[] args){
        Generatore g=new Generatore(4);
        System.out.println(Matrix.MatrixToString(g.mat));

    }
}
