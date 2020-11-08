package Map;
import Main.Mediator;

public interface MapAdapter {
    Mediator med = Mediator.getInstance();

    void load();
    void save();

}
