package Map;

import Main.Matrix;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.io.*;

public class JsonMapAdapter implements MapAdapter {
    @Override
    public void load() {
        String data = readFile();
        MapModel map = load(data);
        med.getMap().loadMap(map);
    }

    @Override
    public void save() {
        MapModel map = med.getMap().dumpMap();
        String data = save(map);
        writeFile(data);
    }


    public MapModel load(String jsonString) {
        MapModel map = new MapModel();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(jsonString);
            JSONObject data = (JSONObject) obj;
            map.blocksMat = Matrix.StringtoMatrix((String) data.get("matrix"), 5);
            map.valuesMat = Matrix.StringtoMatrix((String) data.get("values"), 5);
            map.results= Matrix.StringToList((String) data.get("results"));
            map.blocksOp = Matrix.StringToList((String) data.get("operators"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }



    public String save(MapModel map) {
        JSONObject obj = new JSONObject();
        obj.put("matrix", Matrix.MatrixToString(map.blocksMat));
        obj.put("values", Matrix.MatrixToString(map.valuesMat));
        obj.put("operators", Matrix.ListToString(map.blocksOp));
        obj.put("results", Matrix.ListToString(map.results));
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
