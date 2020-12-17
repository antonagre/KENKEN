package Core;

import Map.GameMap;
import UI.RisolutoreGui;

import java.util.ArrayList;

public class Risolutore {
    public ArrayList<Cell> sol = new ArrayList<Cell>();
    private GameMap map;
    ArrayList<int[][]> solutionMap = new ArrayList<>();
    ArrayList<int[][]> soluzioni = new ArrayList<>();

    public void solve() {
        map=Mediator.getInstance().getMap();
        map.resetMap();
        sol.clear();
        solveMap();
        for(int[][] stampaSol : solutionMap) {
            System.out.println(Util.MatrixToString(stampaSol));
        }
        new RisolutoreGui(solutionMap);
    }

    private void solveBlock(Block bl) {
        Cell empty = bl.findEmpty();
        if (empty != null) {
            for (int i = 1; i < map.mapSide + 1; i++) {
                if (empty.model.checkCellUpdate(i)) {
                    empty.model.updateValue(i);
                    solveBlock(bl);
                    if(bl.checkBlock()){
                        soluzioni.add(map.valueMatrix);
                        solveMap();
                        soluzioni.clear();
                    }
                    empty.model.reset();
                }
            }

        }
        return;
    }

    private void solveMap() {
        Block bloc = new Block(map.findEmptyBlock());
        if (bloc != null) {
            solveBlock(bloc);
            if(bloc.checkBlock() && bloc.getId()== map.blocks.size()){
                int[][] m = Util.cloneMatrix(map.valueMatrix);
                solutionMap.add(m);
            }
        }
        return;
    }

}
