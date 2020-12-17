package Core;

import EXTRA.BlocksBuilder;
import Map.GameMap;
import Map.JsonMapAdapter;
import Map.MapAdapter;
import Map.MapModel;
import UI.NewMapMenu;
import UI.UiManager;

import javax.swing.*;

public class Mediator {
    private static Mediator ISTANCE;
    private MapAdapter loader = new JsonMapAdapter();
    private UiManager uiMgr = new UiManager();
    private GameMap map;

    private Mediator() {
        map=new GameMap();
    }

    public static Mediator getInstance() {
        if (ISTANCE == null) {
            ISTANCE = new Mediator();
        }
        return ISTANCE;
    }

    public void resetMap(){
        Mediator.getInstance().getMap().resetMap();
    }

    public void loadMenu(){
        new NewMapMenu();
    }
    public void loadMap(){
        MapModel map = loader.load();
        this.map.loadMap(map);
    }

    public void saveMap(){
        MapModel map = this.map.dumpMap();
        loader.save(map);
    }

    public void loadNewMap(int side){
        BlocksBuilder bb = new BlocksBuilder(side);
        MapModel map = bb.build();
        this.map.loadMap(map);
    }

    public void resolveMap() {
        new Risolutore().solve();
    }

    public void exit(){
        System.exit(0);
    }

    public void setFrame(JFrame f) {
        uiMgr.setFrame(f);
    }

    public JFrame getFrame() {
        return uiMgr.getFrame();
    }

    public void voidUpdateUIMap() { }

    public GameMap getMap(){
        return this.map;
    }

    public void updateGuiFrame(){
        this.getFrame().revalidate();
        this.getFrame().repaint();
    }
}
