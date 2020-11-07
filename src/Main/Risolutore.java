package Main;

import Map.GameMap;
import UI.Cell;
import UI.RisolutoreGui;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Risolutore {
    public ArrayList<Cell> sol = new ArrayList<Cell>();
    private GameMap map;
    ArrayList<int[][]> solutionMap = new ArrayList<>();
    ArrayList<int[][]> soluzioni = new ArrayList<>();



    public Risolutore() {
        map= Mediator.getInstance().getMap();
        map.resetMap();
        sol.clear();
        solveMap();
        for(int[][] stampaSol : solutionMap) System.out.println(Matrix.MatrixToString(stampaSol));
        new RisolutoreGui(solutionMap);
    }

    public void solveBlock(@NotNull Block bl) {
        Cell empty = bl.findEmpty();
        if (empty != null) {
            for (int i = 1; i < map.mapSide + 1; i++) {
                if (empty.checkCellUpdate(i)) {
                    empty.updateValue(i);
                    solveBlock(bl);
                    if(bl.checkBlock())
                    {
                        soluzioni.add(map.valueMatrix);
                        solveMap();
                        soluzioni.clear();
                    }
                    empty.updateValue(0);
                }
            }

        }
        return;
    }

    public void solveMap() {
        Block bloc = new Block(map.findEmptyBlock());
        if (bloc != null) {
            solveBlock(bloc);
            if(bloc.checkBlock() && bloc.getId()== map.blocks.size()){
                int[][] m = Matrix.cloneMatrix(map.valueMatrix);
                solutionMap.add(m);
            }
        }
        return;
    }

}
