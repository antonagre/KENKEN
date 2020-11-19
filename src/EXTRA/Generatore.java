import Main.Matrix;
import Map.GameMap;
import java.util.ArrayList;
import java.util.Random;
import Main.Mediator;
import Map.MapModel;
import UI.Cell;


public class Generatore {
    Random r = new Random();
    private GameMap map;
    int side;
    int[][] mat;
    int size=0;
    private final String[] operations = {"+","-","*","/"};


    public Generatore(int side) {
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

    public int[][] creaMatrix(int n)
    {
        int [][] mat = new int[n][n];
        for(int i=0;i<mat.length;i++){
            for(int j=0;j<mat.length;j++){
                mat[i][j]=0;
            }
        }
        return mat;
    }

    public int[] cellaVuota(int[][] m)
    {
        int [] coord = new int[2];
        for(int i=0;i<mat.length;i++){
            for(int j=0;j<mat.length;j++){
                if(m[i][j]==0){
                    coord[0]=i;
                    coord[1]=j;
                    return coord;
                }
            }
        }
        return coord;
    }

    public void matrixBlock(int[][] mat,int nb,int cont, int x,int y) {
        if(isFull(mat))          return;
        int[] cordinate= cellaVuota(mat);
        if(cont==0 || mat[x][y]==nb){
            size+=1;
            matrixBlock(mat,nb+1,r.nextInt(mat.length/2)+2,cordinate[0],cordinate[1]);
        }
        if (mat[x][y] == 0) {
            cont--;
            mat[x][y] = nb;//INSERIMENTO DEL NUMERO DEL BLOCCO (BLOCCO ID)
            System.out.println(Matrix.MatrixToString(mat));
        }
        int direction = r.nextInt(4) + 1; //1 destra 2 sinistra 3 sopra 4 sotto
        if (direction == 1)                    //DESTRA
        {
            if (y < mat.length - 1 && mat[x][y+1]==0) matrixBlock(mat, nb, cont, x, y + 1);
        }
        if (direction == 2)                   //SINISTRA
        {
            if (y > 0 && mat[x][y-1]==0) matrixBlock(mat, nb, cont, x, y - 1);

        }
        if (direction == 3)                  //SOPRA
        {
            if (x > 0 && mat[x-1][y]==0) matrixBlock(mat, nb, cont, x - 1, y);

        }
        if (direction == 4)                   //SOTTO
        {
            if (x < mat.length - 1 && mat[x+1][y]==0) matrixBlock(mat, nb, cont, x + 1, y);
        }

        else matrixBlock(mat,nb,cont,x,y);
    }

    public  void riempiMatrixBlock(int[][] m)
    {
        int ind = 1;
        matrixBlock(m,ind,(r.nextInt(this.mat.length/3))+3,1,1);
        map.blocksMap=m;
    }



    public void creaMatrixB(int[][] mat,int nb,int cont, int x,int y) {
        if(isFull(mat) )return ;
        else if(cont>4) creaMatrixB(mat,nb+1,0,x,y);
        else {
            if (mat[x][y] == 0) {
                mat[x][y] = nb;                   //INSERIMENTO DEL NUMERO DEL BLOCCO (BLOCCO ID)
                System.out.println(Matrix.MatrixToString(mat));
            }
            if(mat[x][y]!=nb) cont=0;
            int direction = r.nextInt(4) + 1; //1 destra 2 sinistra 3 sopra 4 sotto
            if (direction == 1)                    //DESTRA
            {
                if (y < mat.length - 1) creaMatrixB(mat, nb, cont, x, y + 1);
                else creaMatrixB(mat, nb, cont + 1, x, y);
            }
            if (direction == 2)                   //SINISTRA
            {
                if (y > 0) creaMatrixB(mat, nb, cont, x, y - 1);
                else  creaMatrixB(mat, nb, cont + 1, x, y);

            }
            if (direction == 3)                  //SOPRA
            {
                if (x > 0) creaMatrixB(mat, nb, cont, x - 1, y);
                else creaMatrixB(mat, nb, cont + 1, x, y);

            }
            if (direction == 4)                   //SOTTO
            {
                if (x < mat.length - 1) creaMatrixB(mat, nb, cont, x + 1, y);
                else creaMatrixB(mat, nb, cont + 1, x, y);
            }
        }

    }



    public boolean isFull(int[][]mat)
    {
        for(int[]a:mat){
            for(int b:a){
                if(b==0)return false;
            }
        }
        return true;
    }

    public String[] creaOperations()
    {
        String[] s= new String[this.size];

        for(int i=0;i<this.size;i++){
            int n = this.r.nextInt(4);
            s[i]=this.operations[n];
        }
        System.out.println(Matrix.ListToString(s));
        return s;
    }

    public ArrayList<Integer> creaRisultati(String[] op, int[][] mat, int[][]block) {
        ArrayList<Integer> s = new ArrayList<Integer>();
        for (int i = 0; i < this.size; i++) {
            ArrayList<Integer> blocco = new ArrayList<>();
            for (int j = 0; j < mat.length; j++)
            {
                for (int h = 0; h < mat.length; h++) {
                    if (block[j][h] == i+1) blocco.add(mat[j][h]);
                }
            }
            int result=0;
            switch (op[i]) {
                case "+":
                    for(int n : blocco)result +=n;
                    s.add(result);
                    break;

                case "*":
                    for(int n : blocco){
                        if (result == 0) result = n ;
                        else result *=n;
                    }
                    s.add(result);
                    break;
                case "-":
                    for(int n : blocco) {
                        if (result == 0) result = n;
                        else result -= n;
                    }
                    s.add(result);
                    break;

                case "/":
                    for(int n : blocco) {
                        if (result == 0) result = n;
                        else result /= n;
                    }
                    s.add(result);
                    break;
            }
        }
        return s;
    }


    public static  void main(String[] args){
        Generatore g = new Generatore(4);
        int[][] prova = g.creaMatrix(4);
        g.riempiMatrixBlock(prova);
        System.out.println(Matrix.MatrixToString(g.mat));
        //MapModel m = new MapModel();
        //m.blocksMat=matrice;
        //m.valuesMat=g.mat;
        String[] op = g.creaOperations();
        //System.out.println(Matrix.ListToString(op));
        ArrayList<Integer> test = g.creaRisultati(op,g.mat,prova);
        for (int a:test)System.out.print(a + " ");


    }
}
