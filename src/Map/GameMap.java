package Map;

import Main.Block;
import Main.Mediator;
import UI.Cell;
import UI.KenKenMap;

import java.util.*;

public class GameMap {
    public int mapSide = 5;
    private String[] OPS = {"+", "-", "/", "*"};
    public int[][] blocksMap;
    public int[][] valueMatrix;
    public HashMap<Integer, Block> blocks = new HashMap<Integer, Block>();

    public void loadMap(MapModel map) {
        blocksMap = map.blocksMat;
        valueMatrix = map.valuesMat;
        new KenKenMap(5,5);
        updateBlocks(map.blocksOp, map.results);
        initGmap();
    }

    public MapModel dumpMap(){
        MapModel map = new MapModel();
        map.blocksMat=this.blocksMap;
        map.valuesMat=this.valueMatrix;
        map.blocksOp=this.getOperators();
        map.results=this.getResult();
        return map;
    }


    public void updateMap(){
        for (int i=1;i<blocks.size()+1;i++) {
            for (Cell c:blocks.get(i).cells) {
                c.updateCell();
            }
        }
        Mediator.getInstance().getFrame().repaint();
    }


    public void updateMatrix(int raw, int col, int x) {
        valueMatrix[raw][col] = x;
    }

    //##MAP CONTROL
    public boolean checkMatrix(int row, int col, int x) {
        for (int i = 0; i < mapSide; i++) {
            if (valueMatrix[row][i] == x) {
                return false;//NOT VALID UPDATE COL
            } else if (valueMatrix[i][col] == x) {
                return false;//NOT VALID UPDATE ROW
            }
        }
        return true;
    }


    public void initGmap() {
        for (int x = 0; x < mapSide; x++) {
            for (int y = 0; y < mapSide; y++) {
                int id = blocksMap[x][y];
                Block b = null;
                if (blocks.containsKey(id)) {
                    b = blocks.get(id);
                } else {
                    b = new Block(id);
                }
                b.addCell(x, y);
            }
        }
        updateMap();
    }


    public void resetMap() {
        for (int i = 1; i < blocks.size() + 1; i++) {
            for (Cell c : blocks.get(i).cells) {
                c.reset();
                c.revalidate();
                c.repaint();
            }
        }
    }


    public void updateBlocks(String[] op, String[] res) {
        for (int i = 0; i < op.length; i++) {
            Block b = new Block(i + 1);
            b.setAll(op[i], Integer.parseInt(res[i]));
            blocks.put(i + 1, b);
        }
    }

    public boolean checkRight(int x, int y) {
        if (y < mapSide - 1) {
            int id = blocksMap[x][y];
            if (blocksMap[x][y + 1] == blocksMap[x][y]) {
                return true;
            }
        }
        return false;
    }

    public boolean checkBottom(int x, int y) {
        if (x < mapSide - 1) {
            int id = blocksMap[x][y];
            if (blocksMap[x + 1][y] == blocksMap[x][y]) {
                return true;
            }
        }
        return false;
    }


    public Block findEmptyBlock(){
        Block bl= null;
        for(int i=1;i<=blocks.size();i++){
            bl = new Block(blocks.get(i));
            boolean flag = true;
            for(Cell c: bl.cells){
                if(c.n==0){
                    flag=false;;
                }
            }
            if(!flag)break;
        }
        return bl;
    }

    public String[] getOperators() {
        String[] op = new String[blocks.size()];
        for (int i = 1; i < blocks.size() + 1; i++) {
            op[i - 1] = blocks.get(i).operation;
        }
        return op;
    }

    public String[] getResult() {
        String[] res = new String[blocks.size()];
        for (int i = 1; i < blocks.size() + 1; i++) {
            res[i - 1] = new Integer(blocks.get(i).resultValue).toString();
        }
        return res;
    }
}

