package Map;
import Core.Util;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import javax.swing.*;
import java.io.*;

public class JsonMapAdapter extends MapAdapter {
    @Override
    public MapModel load() {
        String data = readFile();
        MapModel map = loadFile(data);
        return map;
    }

    @Override
    public void save(MapModel map) {
        String data = saveFile(map);
        writeFile(data);
    }

    public MapModel loadFile(String jsonString) {
        MapModel map = new MapModel();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(jsonString);
            JSONObject data = (JSONObject) obj;
            map.blocksMat = Util.StringtoMatrix((String) data.get("matrix"), 5);
            map.valuesMat = Util.StringtoMatrix((String) data.get("values"), 5);
            map.results= Util.StringToList((String) data.get("results"));
            map.blocksOp = Util.StringToList((String) data.get("operators"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    public String saveFile(MapModel map) {
        JSONObject obj = new JSONObject();
        obj.put("matrix", Util.MatrixToString(map.blocksMat));
        obj.put("values", Util.MatrixToString(map.valuesMat));
        obj.put("operators", Util.ListToString(map.blocksOp));
        obj.put("results", Util.ListToString(map.results));
        StringWriter out = new StringWriter();
        try {
            obj.writeJSONString(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String jsonString = obj.toJSONString();
        return jsonString;
    }

    public String readFile(){
        String s="";
        JFileChooser fileChooser = new JFileChooser();
        int n = fileChooser.showOpenDialog(new JPanel());
        File f = fileChooser.getSelectedFile();
        try {
            FileReader fInput = new FileReader(f);
            BufferedReader inStream = new BufferedReader(fInput);
            String text;
            while((text=inStream.readLine()) != null){
                s+=text;
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return s;
    }

    public void writeFile(String jsonString){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(new JPanel());
        File f = fileChooser.getSelectedFile();
        try {
            FileWriter fInput = new FileWriter(f);
            BufferedWriter outStream = new BufferedWriter(fInput);
            outStream.write(jsonString);
            outStream.flush();
            outStream.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}
