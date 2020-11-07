package Map;
import Main.Mediator;

public interface MapInterface {
    public Mediator med = Mediator.getInstance();

    public void load();
    public void save();

}
