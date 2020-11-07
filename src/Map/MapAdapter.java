package Map;

public class MapAdapter implements MapInterface{
    MapLoader loader = new MapLoader();
    @Override
    public void load() {
        String data = loader.readFile();
        MapModel map = loader.load(data);
        med.getMap().loadMap(map);
    }

    @Override
    public void save() {
        MapModel map = med.getMap().dumpMap();
        String data = loader.save(map);
        loader.writeFile(data);
    }
}
