package EXTRA;

import java.util.*;
import  Main.Matrix;
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

    public static void creaMatrixB(int[][] mat,int nb,int cont, int x,int y) {
        if(mat==null || isFull(mat) )return ;
        else if(cont>3) creaMatrixB(mat,nb+1,0,x,y);
        else {
            if (mat[x][y] == 0) {
                mat[x][y] = nb;                   //INSERIMENTO DEL NUMERO DEL BLOCCO (BLOCCO ID)
                System.out.println(Matrix.MatrixToString(mat));
            }
            if(mat[x][y]!=nb) cont=4;
            Random r = new Random();
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

    public static int[][] fondi(int[][]mat) {
        ArrayList<Integer> valori = new ArrayList<>();
        int[][] matFinal = new int[mat.length][mat.length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                int val = mat[i][j];
                if (!valori.contains(val)) valori.add(val);
            }
        }
        int c = 1;
        for (int n : valori) {
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat.length; j++) {
                    int val = mat[i][j];
                    if (val == n) matFinal[i][j] = c;
                }
            }
            c += 1;
        }
        return matFinal;
    }







    public static void creaBlocchi(int[][] mat,int nb,boolean flag ,int x,int y) {
        if (mat == null) return;
        else {
            Random r = new Random();
            int direction = r.nextInt(4) + 1; //1 destra 2 sinistra 3 sopra 4 sotto
            if (mat[x][y] == 0) {
                mat[x][y] = nb;                   //INSERIMENTO DEL NUMERO DEL BLOCCO (BLOCCO ID)
                System.out.println(Matrix.MatrixToString(mat));
                flag=true;
            }
            if (direction == 1)                    //DESTRA
            {
                if (y < mat.length - 1 ) {
                    if(flag){
                        creaBlocchi(mat, nb, flag, x, y + 1);
                    }
                    flag=false;
                }
                else creaBlocchi(mat, nb+1, flag, x, y);;

            }
            if (direction == 2)                   //SINISTRA
            {
                if (y > 0) {
                    if (flag) {
                        creaBlocchi(mat, nb, flag, x, y - 1);
                    }
                    flag = false;
                }
                else creaBlocchi(mat, nb+1, flag, x, y);;

            }
            if (direction == 3 )                  //SOPRA
            {
                if (x > 0 ) {
                    if (flag) {
                        creaBlocchi(mat, nb, flag, x - 1, y);
                    }
                    flag = false;
                }
                else creaBlocchi(mat, nb+1, flag, x, y);;
            }
            if (direction == 4 )                   //SOTTO
            {
                if (x < mat.length - 1) {
                    if(flag){
                        creaBlocchi(mat, nb, flag, x + 1, y);
                    }
                    flag=false;

                }
                else creaBlocchi(mat, nb+1, flag, x, y);;
            }

        }
    }

    public static void creaBlocchiR(int[][] mat,int nb,boolean flag ,int x,int y) {
       if(mat==null)return;

    }

    public static void generaBlocchi(int[][] mat,ArrayList<Integer> blocco,ArrayList<ArrayList<Integer>> arrayblocchi,int n)
    {
        if(n>5)return;

            for(int j=0;j<mat.length;j++) {
                for(int h=0;h<mat.length;h++){
                   if(mat[j][h]==n){

                        blocco.add(n);
                    }
                }
            }
            arrayblocchi.add(blocco);
            generaBlocchi(mat,new ArrayList<Integer>(),arrayblocchi,n+1);

        }

        public static boolean isFull(int[][]mat)
        {
            for(int[]a:mat){
                for(int b:a){
                    if(b==0)return false;
                }
            }
            return true;
        }

    public static void main (String [] args)
    {
        int[][] prova = creaMatrix(5);
        System.out.println(Matrix.MatrixToString(prova));
        int idBlock=1;
        creaMatrixB(prova,idBlock,0,0,0);
        int[][] matrice = fondi(prova);
        System.out.println(Matrix.MatrixToString(matrice));


    }


}
