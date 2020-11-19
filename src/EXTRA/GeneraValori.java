package EXTRA;
import Main.Matrix;
import Map.GameMap;
import java.util.ArrayList;
import java.util.Random;
import Main.Mediator;

public class GeneraValori {
    Random r = new Random();
    private GameMap map;
    int side;       //DIMENSIONE MATRICE DI GIOCO
    int[][] mat;
    int size=0;     //DIMENSIONE SOLUZIONI RISULTATI
    private final String[] operations = {"+","-","*","/"};

    public GeneraValori(int side) {
        mat=new int[side][side];
        map= Mediator.getInstance().getMap();
        map.resetMap();
        this.side=side;
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
            int n = r.nextInt(side + 1);
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

}
