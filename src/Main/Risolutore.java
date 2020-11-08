package Main;

import Map.GameMap;
import UI.Cell;
import UI.RisolutoreGui;

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

    public void solveBlock(Block bl) {
        Cell empty = bl.cellaVuota();
        if (empty != null) {
            for (int i = 1; i < map.mapSide + 1; i++) {
                if (empty.checkCellUpdate(i)) {
                    empty.updateValue(i);
                    solveBlock(bl);
                    if(bl.verificaBlocco())
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
        Block bloc = new Block(map.trovaBloccoVuoto());
        if (bloc != null) {
            solveBlock(bloc);
            if(bloc.verificaBlocco() && bloc.getId()== map.blocks.size()){
                int[][] m = Matrix.cloneMatrix(map.valueMatrix);
                solutionMap.add(m);
            }
        }
        return;
    }

}
