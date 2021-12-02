package touro.subwaymap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.*;


public class JSONReader {
    private final Gson gson = new Gson();
    private InputStreamReader reader;

    public SubwayStations readSubwayStationJSON() throws IOException {
        reader = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("SubwayStations.json"));
        SubwayStations subwayStations = gson.fromJson(reader, SubwayStations.class);
        reader.close();
        return subwayStations;
    }

    public Map<String,ArrayList<Integer>> readSubwayLinesJSON() throws IOException {
        reader = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("SubwayLines.json"));
        Type type = new TypeToken<HashMap<String,ArrayList<Integer>>>(){}.getType();
        Map<String,ArrayList<Integer>> subwayLines = gson.fromJson(reader, type);
        reader.close();
        return subwayLines;
    }
}
