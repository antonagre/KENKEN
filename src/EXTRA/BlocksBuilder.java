package EXTRA;
import Core.Util;
import Map.MapModel;

import javax.swing.text.FlowView;
import java.util.Random;

public class BlocksBuilder {
    final String[] OP = {"+","-","*","/"};
    Random rand=new Random();
    int side;
    String[] operations;
    String[] results;
    int[][] mat;
    int[][] mapBlocks;
    
    public BlocksBuilder(int side) {
        GeneratoreVal gv = new GeneratoreVal(side);
        GeneratoreBlocks gb = new GeneratoreBlocks(side);
        this.mapBlocks= gb.mapBlocks;
        this.side=side;
        this.mat=gv.mat;
        System.out.println(Util.MatrixToString(this.mat));
        System.out.println(Util.MatrixToString(this.mapBlocks));
    }

    public int getMaxBlock() {
        int max = 0;
        for (int x = 0; x < side; x++) {
            for (int y = 0; y < side; y++) {
                if (mapBlocks[x][y] > max) max = mapBlocks[x][y];
            }
        }
        return max;
    }

    public MapModel build(){
        int max = getMaxBlock();
        operations=new String[max];
        results=new String[max];
        for(int i=1;i<max+1;i++){
            generateBlock(i);
        }
        MapModel model = new MapModel();
        model.blocksMat=this.mapBlocks;
        model.valuesMat=Util.createZeroMatrix(this.side);
        model.blocksOp=operations;
        model.results=results;
        return model;
    }

    public String generateOP(){
        int i = rand.nextInt(4);
        return this.OP[i];
    }

    public void generateBlock(int id){
        while(true){
            String op = generateOP();
            float res = calcOpResult(id,op);
            if(res>0 && res%1==0){
                System.out.println("id: " + id + " op: " + op + " res:" + res);
                operations[id - 1] = op;
                results[id - 1] = Integer.toString(Math.round(res));
                break;
            }
        }
    }

    public float calcOpResult(int id,String op){
        float result = 0;
        switch (op) {
            case "+":
                for (int x=0;x<side;x++) {
                    for(int y=0;y<side;y++){
                        if(mapBlocks[x][y]==id){
                            result = result + mat[x][y];
                        }
                    }
                }
                break;
            case "*":
                for (int x=0;x<side;x++) {
                    for(int y=0;y<side;y++){
                        if(mapBlocks[x][y]==id){
                            if (result == 0) result = mat[x][y];
                            else result = result * mat[x][y];
                        }
                    }
                }
                break;
            case "-":
                for (int x=0;x<side;x++) {
                    for(int y=0;y<side;y++){
                        if(mapBlocks[x][y]==id){
                            if (result == 0) result = mat[x][y];
                            else result = result - mat[x][y];
                        }
                    }
                }
                break;
            case "/":
                for (int x=0;x<side;x++) {
                    for(int y=0;y<side;y++){
                        if(mapBlocks[x][y]==id){
                            if (result == 0) result = mat[x][y];
                            else result /= mat[x][y];
                        }
                    }
                }
                break;
        }
        return result;
    }

}
