import java.util.*;

public class Prova {

    int[][] matrix ;

    public static int[][] creaMatrix(int n)
    {
        int [][] mat = new int[n][n];
        for(int i=0;i<mat.length;i++){
            for(int j=0;j<mat.length;j++){
                mat[i][j]=0;
            }
        }
        return mat;
    }

    public static void matrix(int[][] mat,int nb,int cont ,int x,int y) {
        if (mat == null || cont == 0) return;
        else {
            Random r = new Random();
            int direction = r.nextInt(4) + 1; //1 destra 2 sinistra 3 sopra 4 sotto
            if (mat[x][y] == 0 && cont > 0) {
                cont -= 1;           //DECREMENTO IL CONTATORE DEI BLOCCHI ID ANCORA DA POTER INSERIRE
                mat[x][y] = nb;                   //INSERIMENTO DEL NUMERO DEL BLOCCO (BLOCCO ID)
                System.out.println(Matrix.MatrixToString(mat));
            }
            if (direction == 1)                    //DESTRA
            {
                if (y < mat.length - 1 ) matrix(mat, nb, cont, x, y + 1);
                else matrix(mat, nb, cont, x, y);;

            }
            if (direction == 2)                   //SINISTRA
            {
                if (y > 0) matrix(mat, nb, cont, x, y - 1 );
                else matrix(mat, nb, cont, x, y);;

            }
            if (direction == 3 )                  //SOPRA
            {
                if (x > 0 ) matrix(mat, nb, cont, x - 1, y);
                else matrix(mat, nb, cont, x, y);;
            }
            if (direction == 4 )                   //SOTTO
            {
                if (x < mat.length - 1) matrix(mat, nb, cont, x + 1, y);
                else matrix(mat, nb, cont, x, y);;
            }

        }
    }

    public static void creaMatrix(int[][] base,int nBlocchi,int dimBlocco)
    {
        for (int i=1;i<=nBlocchi;i++)   matrix(base,i,dimBlocco,2,2);
    }

    public static void main (String [] args)
    {
        int[][] prova = creaMatrix(5);
        creaMatrix(prova,5,5);
        System.out.println(Matrix.MatrixToString(prova));
    }

}
